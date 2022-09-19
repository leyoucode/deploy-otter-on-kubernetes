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

package com.alibaba.otter.shared.communication.model.canal;

import com.alibaba.otter.shared.communication.core.model.Event;

/**
 * 查询对应的过滤条件
 * 
 * @author jianghang 2012-7-23 下午02:41:52
 */
public class FindFilterEvent extends Event {

    private static final long serialVersionUID = 476657754177940448L;

    private String            destination;

    public FindFilterEvent(){
        super(CanalEventType.findFilter);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

}
