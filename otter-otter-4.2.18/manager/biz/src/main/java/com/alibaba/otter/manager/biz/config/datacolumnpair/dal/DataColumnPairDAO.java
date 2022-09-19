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

package com.alibaba.otter.manager.biz.config.datacolumnpair.dal;

import java.util.List;

import com.alibaba.otter.manager.biz.common.basedao.GenericDAO;
import com.alibaba.otter.manager.biz.config.datacolumnpair.dal.dataobject.DataColumnPairDO;

/**
 * 类DataColumnPairDAO.java的实现描述：TODO 类实现描述
 * 
 * @author simon 2012-4-20 下午4:08:55
 */
public interface DataColumnPairDAO extends GenericDAO<DataColumnPairDO> {

    public List<DataColumnPairDO> listByDataMediaPairId(Long dataMediaPairId);

    public List<DataColumnPairDO> listByDataMediaPairIds(Long... dataMediaPairIds);

    public void insertBatch(List<DataColumnPairDO> dataColumnPairDos);

    public void deleteByDataMediaPairId(Long dataMediaPairId);
}
