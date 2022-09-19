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

package com.alibaba.otter.manager.biz.config.datamediapair;

import java.util.List;

import com.alibaba.otter.manager.biz.common.baseservice.GenericService;
import com.alibaba.otter.shared.common.model.config.data.DataMediaPair;

/**
 * @author simon
 */
public interface DataMediaPairService extends GenericService<DataMediaPair> {

    public List<DataMediaPair> listByPipelineId(Long pipelineId);

    public List<DataMediaPair> listByPipelineIdWithoutColumn(Long pipelineId);

    public List<DataMediaPair> listByDataMediaId(Long dataMediaId);

    public Long createAndReturnId(DataMediaPair dataMediaPair);

    public boolean createIfNotExist(DataMediaPair dataMediaPair);
}
