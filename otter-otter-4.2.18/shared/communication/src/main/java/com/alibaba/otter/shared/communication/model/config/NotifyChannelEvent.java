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

package com.alibaba.otter.shared.communication.model.config;

import com.alibaba.otter.shared.communication.core.model.Event;
import com.alibaba.otter.shared.common.model.config.channel.Channel;

/**
 * config变更通知的事件
 * 
 * @author jianghang
 */
public class NotifyChannelEvent extends Event {

    private static final long serialVersionUID = -8472088519060045661L;

    public NotifyChannelEvent(){
        super(ConfigEventType.notifyChannel);
    }

    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
