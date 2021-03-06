/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.log.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 日志配置
 * </p>
 *
 * @package: com.xkcoding.scaffold.log.props
 * @description: 日志配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-14 18:15
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@ConfigurationProperties("scaffold.log")
public class ScaffoldLogProperties {
    /**
     * 是否启用操作日志，默认值：false
     */
    private boolean enabled = false;
}
