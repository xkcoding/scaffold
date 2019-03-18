/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.model.dingtalk;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 提醒@人
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.model.dingtalk
 * @description: 提醒
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 13:47
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attention {
    /**
     * 被@人的手机号(在content里添加@人的手机号)
     */
    private List<String> atMobiles = Lists.newArrayList();
    /**
     * 当@所有人时：true，否则为：false
     */
    private boolean isAtAll;
}