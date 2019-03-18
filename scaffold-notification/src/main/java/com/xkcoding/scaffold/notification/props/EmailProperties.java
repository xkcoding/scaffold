/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 邮箱配置
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.props
 * @description: 邮箱配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 14:52
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@ConfigurationProperties(prefix = "scaffold.notification.email")
public class EmailProperties {
    /**
     * 是否启用
     */
    private boolean enabled = false;
    /**
     * HTML模板文件的目录，默认位置为classpath:/email/
     */
    private String prefix = "classpath:/email/";

    /**
     * 邮箱模板文件后缀，默认：.html
     */
    private String suffix = ".html";
}
