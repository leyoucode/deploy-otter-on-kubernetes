/*
 * Copyright (C) 2010-2101 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.otter.node.etl.common.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.ddlutils.model.Table;
import org.jtester.annotations.SpringBeanByName;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.testng.annotations.Test;

import com.alibaba.otter.node.etl.BaseDbTest;
import com.alibaba.otter.node.etl.common.db.dialect.DbDialect;
import com.alibaba.otter.node.etl.common.db.dialect.DbDialectFactory;
import com.alibaba.otter.node.etl.common.db.dialect.SqlTemplate;
import com.alibaba.otter.node.etl.common.db.dialect.mysql.MysqlDialect;
import com.alibaba.otter.node.etl.common.db.utils.SqlUtils;
import com.alibaba.otter.shared.common.model.config.data.DataMediaType;
import com.alibaba.otter.shared.common.model.config.data.db.DbMediaSource;

public class DbDialectIntegration extends BaseDbTest {

    private static final String SCHEMA_NAME    = "test";
    private static final String TABLE_NAME     = "ljh_demo";
    @SpringBeanByName
    private DbDialectFactory    dbDialectFactory;

    private String[]            pkColumns      = { "id" };
    private String[]            columns        = { "name", "enum_value", "bigint_value", "int_value" };

    private String[]            pkColumnValues = { "10" };

    private String[]            columnValues   = { "hello", "1", "9223372036854775808", "2147483648" };

    @Test(expectedExceptions = RuntimeException.class)
    public void test_mysql() {
        DbMediaSource dbMediaSource = new DbMediaSource();
        dbMediaSource.setId(10L);
        dbMediaSource.setDriver("com.mysql.jdbc.Driver");
        dbMediaSource.setUsername("xxxxx");
        dbMediaSource.setPassword("xxxxx");
        dbMediaSource.setUrl("jdbc:mysql://127.0.0.1:3306");
        dbMediaSource.setEncode("UTF-8");
        dbMediaSource.setType(DataMediaType.MYSQL);

        final DbDialect dbDialect = dbDialectFactory.getDbDialect(2L, dbMediaSource);
        want.object(dbDialect).clazIs(MysqlDialect.class);

        Table table = dbDialect.findTable("test", "ljh_demo");
        System.out.println(table);

        final SqlTemplate sqlTemplate = dbDialect.getSqlTemplate();
        final JdbcTemplate jdbcTemplate = dbDialect.getJdbcTemplate();
        final TransactionTemplate transactionTemplate = dbDialect.getTransactionTemplate();
        final int[] pkColumnTypes = { Types.INTEGER };
        final int[] columnTypes = { Types.VARCHAR, Types.INTEGER, Types.DECIMAL, Types.BIGINT };
        transactionTemplate.execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                int affect = 0;
                String sql = null;
                // 执行insert
                sql = sqlTemplate.getInsertSql(SCHEMA_NAME, TABLE_NAME, pkColumns, columns);
                System.out.println(sql);
                affect = (Integer) jdbcTemplate.execute(sql, new PreparedStatementCallback() {

                    public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                        doPreparedStatement(ps, dbDialect, toTypes(columnTypes, pkColumnTypes),
                                            toValues(columnValues, pkColumnValues));
                        return ps.executeUpdate();
                    }

                });
                want.number(affect).isEqualTo(1);
                throw new RuntimeException("rollback");
            }
        });

    }

    private Integer[] toTypes(int[]... types) {
        List<Integer> result = new ArrayList<Integer>();
        for (int[] type : types) {
            for (int t : type) {
                result.add(t);
            }
        }

        return result.toArray(new Integer[result.size()]);
    }

    private String[] toValues(String[]... values) {
        List<String> result = new ArrayList<String>();
        for (String[] value : values) {
            for (String v : value) {
                result.add(v);
            }
        }

        return result.toArray(new String[result.size()]);
    }

    private void doPreparedStatement(PreparedStatement ps, final DbDialect dbDialect, final Integer[] columnTypes,
                                     final String[] columnValues) throws SQLException {
        LobCreator lobCreator = null;
        for (int i = 0; i < columnTypes.length; i++) {
            int paramIndex = i + 1;
            String sqlValue = columnValues[i];
            int sqlType = columnTypes[i];
            Object param = SqlUtils.stringToSqlValue(sqlValue, sqlType, SqlUtils.isTextType(sqlType),
                                                     dbDialect.isEmptyStringNulled());
            switch (sqlType) {
                case Types.CLOB:
                    if (lobCreator == null) {
                        lobCreator = dbDialect.getLobHandler().getLobCreator();
                    }

                    lobCreator.setClobAsString(ps, paramIndex, (String) param);
                    break;

                case Types.BLOB:
                    if (lobCreator == null) {
                        lobCreator = dbDialect.getLobHandler().getLobCreator();
                    }

                    lobCreator.setBlobAsBytes(ps, paramIndex, (byte[]) param);
                    break;

                default:
                    StatementCreatorUtils.setParameterValue(ps, paramIndex, sqlType, null, param);
                    break;
            }
        }
    }
}
