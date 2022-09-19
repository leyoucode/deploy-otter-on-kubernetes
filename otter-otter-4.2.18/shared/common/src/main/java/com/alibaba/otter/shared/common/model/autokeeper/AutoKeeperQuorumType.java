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

package com.alibaba.otter.shared.common.model.autokeeper;

/**
 * 当前Quorum类型
 * 
 * @author jianghang 2012-9-21 下午02:03:44
 * @version 4.1.0
 */
public enum AutoKeeperQuorumType {

    LEADER, FOLLOWER, OBSERVER, STANDALONE;

    public boolean isLeader() {
        return this.equals(AutoKeeperQuorumType.LEADER);
    }

    public boolean isFollower() {
        return this.equals(AutoKeeperQuorumType.FOLLOWER);
    }

    public boolean isObserver() {
        return this.equals(AutoKeeperQuorumType.OBSERVER);
    }

    public boolean isStandalone() {
        return this.equals(AutoKeeperQuorumType.STANDALONE);
    }
}
