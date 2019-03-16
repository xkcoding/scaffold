/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * <p>
 * 基于 Spring Retry 重试机制
 * </p>
 *
 * @package: com.xkcoding.scaffold.web.config
 * @description: 基于 Spring Retry 重试机制
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 18:18
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@Configuration
public class RetryConfiguration {

    /**
     * 默认重试机制
     */
    @Bean
    @ConditionalOnMissingBean(name = "defaultRetryInterceptor")
    public RetryOperationsInterceptor defaultRetryInterceptor() {
        log.info(String.format("触发重试机制，当前重试策略: 起始时间间隔 %s, 间隔时间倍数: %s, 最大时间间隔: %s", 1000, 1.2, 5000));
        return RetryInterceptorBuilder.stateless().backOffOptions(1000, 1.2, 5000).maxAttempts(10).build();
    }

}
