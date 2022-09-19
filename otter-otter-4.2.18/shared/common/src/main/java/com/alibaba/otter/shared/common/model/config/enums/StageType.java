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

package com.alibaba.otter.shared.common.model.config.enums;

/**
 * S.E.T.L的阶段类型
 * 
 * @author jianghang 2011-9-8 下午12:40:29
 */
public enum StageType {

    SELECT, EXTRACT, TRANSFORM, LOAD;

    public boolean isSelect() {
        return this.equals(StageType.SELECT);
    }

    public boolean isExtract() {
        return this.equals(StageType.EXTRACT);
    }

    /**
     * transform和load一定会同时出现
     */
    public boolean isTransform() {
        return this.equals(StageType.TRANSFORM);
    }

    /**
     * transform和load一定会同时出现
     */
    public boolean isLoad() {
        return this.equals(StageType.LOAD);
    }
}
