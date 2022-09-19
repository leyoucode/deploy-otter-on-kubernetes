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

package com.alibaba.otter.manager.biz.remote;

import com.alibaba.otter.shared.communication.model.statistics.DelayCountEvent;
import com.alibaba.otter.shared.communication.model.statistics.TableStatEvent;
import com.alibaba.otter.shared.communication.model.statistics.ThroughputStatEvent;

/**
 * 统计相关远程接口定义
 * 
 * @author jianghang
 */
public interface StatsRemoteService {

    /**
     * 接收inc delay统计信息
     */
    public void onDelayCount(DelayCountEvent event);

    /**
     * 接收table load相关数据信息
     */
    public void onTableStat(TableStatEvent event);

    /**
     * 接收吞吐量相关统计信息
     */
    public void onThroughputStat(ThroughputStatEvent event);

}
