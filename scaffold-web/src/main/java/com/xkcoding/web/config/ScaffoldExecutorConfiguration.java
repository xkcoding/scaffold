/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.web.config;

import com.xkcoding.web.props.ScaffoldAsyncProperties;
import lombok.AllArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 异步处理线程池配置
 * </p>
 *
 * @package: com.xkcoding.scaffold.web.config
 * @description: 异步处理线程池配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 18:17
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@EnableAsync
@EnableScheduling
@AllArgsConstructor
@EnableConfigurationProperties({ScaffoldAsyncProperties.class})
public class ScaffoldExecutorConfiguration extends AsyncConfigurerSupport {

    private final ScaffoldAsyncProperties scaffoldAsyncProperties;

    @Override
    @Bean(name = "taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(scaffoldAsyncProperties.getCorePoolSize());
        executor.setMaxPoolSize(scaffoldAsyncProperties.getMaxPoolSize());
        executor.setQueueCapacity(scaffoldAsyncProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(scaffoldAsyncProperties.getKeepAliveSeconds());
        executor.setThreadNamePrefix("async-executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
