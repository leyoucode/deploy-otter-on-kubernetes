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

package com.alibaba.otter.manager.web.home.module.screen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.otter.manager.biz.config.datamatrix.DataMatrixService;
import com.alibaba.otter.manager.biz.config.datamediasource.DataMediaSourceService;
import com.alibaba.otter.shared.common.model.config.data.DataMatrix;
import com.alibaba.otter.shared.common.model.config.data.DataMediaSource;

public class DataMatrixInfo {

    @Resource(name = "dataMatrixService")
    private DataMatrixService      dataMatrixService;

    @Resource(name = "dataMediaSourceService")
    private DataMediaSourceService dataMediaSourceService;

    public void execute(@Param("matrixId") Long matrixId, Context context) throws Exception {
        DataMatrix matrix = dataMatrixService.findById(matrixId);

        Map condition = new HashMap();
        condition.put("searchKey", "jdbc:mysql://groupKey=" + matrix.getGroupKey());
        List<DataMediaSource> dataSources = dataMediaSourceService.listByCondition(condition);

        context.put("dataMatrix", matrix);
        context.put("dataSources", dataSources);
    }
}
