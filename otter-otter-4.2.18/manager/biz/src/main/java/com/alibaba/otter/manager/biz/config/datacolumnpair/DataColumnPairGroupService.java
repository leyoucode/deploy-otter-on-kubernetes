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

package com.alibaba.otter.manager.biz.config.datacolumnpair;

import java.util.List;
import java.util.Map;

import com.alibaba.otter.manager.biz.common.baseservice.GenericService;
import com.alibaba.otter.shared.common.model.config.data.ColumnGroup;

/**
 * 类DataColumnPairGroupService.java的实现描述：TODO 类实现描述
 * 
 * @author simon 2012-4-20 下午4:08:24
 */
public interface DataColumnPairGroupService extends GenericService<ColumnGroup> {

    public void removeByDataMediaPairId(Long dataMediaPairId);

    public List<ColumnGroup> listByDataMediaPairId(Long dataMediaPairId);

    public Map<Long, List<ColumnGroup>> listByDataMediaPairIds(Long... dataMediaPairId);
}
