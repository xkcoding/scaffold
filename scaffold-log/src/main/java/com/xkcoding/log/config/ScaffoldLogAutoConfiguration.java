/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.log.config;

import com.xkcoding.launcher.props.ScaffoldProperties;
import com.xkcoding.launcher.server.ServerInfo;
import com.xkcoding.log.aspectj.ApiLogAspect;
import com.xkcoding.log.event.ApiLogListener;
import com.xkcoding.log.event.CustomLogListener;
import com.xkcoding.log.event.ErrorLogListener;
import com.xkcoding.log.logger.ScaffoldLogger;
import com.xkcoding.log.props.ScaffoldLogProperties;
import com.xkcoding.log.service.LogService;
import com.xkcoding.log.service.SecurityService;
import com.xkcoding.log.service.impl.DefaultScaffoldLogServiceImpl;
import com.xkcoding.log.service.impl.DefaultScaffoldSecurityServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 日志工具自动配置
 * </p>
 *
 * @package: com.xkcoding.log.config
 * @description: 日志工具自动配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:56
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@EnableConfigurationProperties(ScaffoldLogProperties.class)
public class ScaffoldLogAutoConfiguration {

    private final ServerInfo serverInfo;
    private final ScaffoldProperties scaffoldProperties;

    @Bean
    @ConditionalOnMissingBean
    public LogService logService() {
        return new DefaultScaffoldLogServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityService securityService() {
        return new DefaultScaffoldSecurityServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(value = "scaffold.log.enabled", havingValue = "true")
    public ApiLogAspect apiLogAspect() {
        return new ApiLogAspect();
    }

    @Bean
    public ScaffoldLogger scaffoldLogger() {
        return new ScaffoldLogger();
    }

    @Bean
    public ApiLogListener apiLogListener() {
        return new ApiLogListener(logService(), securityService(), serverInfo, scaffoldProperties);
    }

    @Bean
    public ErrorLogListener errorEventListener() {
        return new ErrorLogListener(logService(), securityService(), serverInfo, scaffoldProperties);
    }

    @Bean
    public CustomLogListener customLogListener() {
        return new CustomLogListener(logService(), securityService(), serverInfo, scaffoldProperties);
    }

}
