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

package com.alibaba.otter.shared.arbitrate.impl.setl.helper;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.alibaba.otter.shared.arbitrate.model.EtlEventData;
import com.alibaba.otter.shared.common.model.config.enums.StageType;
import com.alibaba.otter.shared.common.utils.OtterToStringStyle;

/**
 * stage进度数据
 * 
 * @author jianghang 2012-9-29 上午10:34:05
 * @version 4.1.0
 */
public class StageProgress {

    private StageType    stage;
    private EtlEventData data;

    public StageProgress(){
    }

    public StageProgress(StageType stage, EtlEventData data){
        this.stage = stage;
        this.data = data;
    }

    public StageType getStage() {
        return stage;
    }

    public void setStage(StageType stage) {
        this.stage = stage;
    }

    public EtlEventData getData() {
        return data;
    }

    public void setData(EtlEventData data) {
        this.data = data;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, OtterToStringStyle.DEFAULT_STYLE);
    }
}
