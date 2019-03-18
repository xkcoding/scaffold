/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 钉钉消息类型枚举
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.constants
 * @description: 钉钉消息类型枚举
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 11:24
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
@AllArgsConstructor
public enum DingTalkType {
    /**
     * text类型
     */
    TEXT("text"),
    /**
     * link类型
     */
    LINK("link"),
    /**
     * markdown类型
     */
    MARKDOWN("markdown"),
    /**
     * 整体跳转ActionCard类型
     */
    WHOLE_ACTIONCARD("actionCard"),
    /**
     * 独立跳转ActionCard类型
     */
    SINGLE_ACTIONCARD("actionCard"),
    /**
     * FeedCard类型
     */
    FEEDCARD("feedCard");

    /**
     * 类型
     */
    private String type;


}
