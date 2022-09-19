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

package com.alibaba.otter.manager.biz.common.basedao;

import java.util.List;
import java.util.Map;

/**
 * @author simon 2011-10-31 上午09:40:47
 */
public interface GenericDAO<T> {

    public T insert(T entityObj);

    public void delete(Long identity);

    public void update(T entityObj);

    public List<T> listAll();

    public List<T> listByCondition(Map condition);

    public List<T> listByMultiId(Long... identities);

    public T findById(Long identity);

    public int getCount();

    public int getCount(Map condition);

    public boolean checkUnique(T entityObj);

}
