/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.code.config;

import com.xkcoding.code.ScaffoldCode;
import com.xkcoding.code.props.ScaffoldCodeProperties;
import com.xkcoding.code.utils.CodeUtil;
import com.xkcoding.launcher.props.ScaffoldProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 验证码自动装配
 * </p>
 *
 * @package: com.xkcoding.code.config
 * @description: 验证码自动装配
 * @author: yangkai.shen
 * @date: Created in 2019-03-14 13:42
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(value = "scaffold.code.enabled", havingValue = "true")
@EnableConfigurationProperties(ScaffoldCodeProperties.class)
public class ScaffoldCodeAutoConfiguration {
    private final ScaffoldCodeProperties scaffoldCodeProperties;
    private final ScaffoldProperties scaffoldProperties;

    @Bean
    public ScaffoldCode scaffoldCode(CacheManager cacheManager) {
        String cacheName = scaffoldCodeProperties.getCacheName();
        Cache cache = cacheManager.getCache(cacheName);
        CodeUtil codeUtil = new CodeUtil(scaffoldCodeProperties);
        return new ScaffoldCode(scaffoldProperties, scaffoldCodeProperties, scaffoldCodeProperties.getCookieName(), cache, codeUtil);
    }
}
