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
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * <p>
 * 阿里大鱼短信配置
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.props
 * @description: 阿里大鱼短信配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 10:35
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@Configuration
@ConditionalOnExpression("!'${scaffold.notification.aliyun}'.isEmpty()")
@ConfigurationProperties(prefix = "scaffold.notification.aliyun")
public class SmsAliyunProperties {
    /**
     * 应用ID
     */
    private String accessKey;
    /**
     * 应用秘钥
     */
    private String secretKey;
    /**
     * 短信模板配置
     */
    private Map<String, String> channels;
}
