/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.web.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 异步线程池配置
 * </p>
 *
 * @package: com.xkcoding.web.props
 * @description: 异步线程池配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 18:14
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
@Setter
@ConfigurationProperties("scaffold.async")
public class ScaffoldAsyncProperties {
    /**
     * 异步核心线程数，默认：2
     */
    private int corePoolSize = 2;
    /**
     * 异步最大线程数，默认：50
     */
    private int maxPoolSize = 50;
    /**
     * 队列容量，默认：10000
     */
    private int queueCapacity = 10000;
    /**
     * 线程存活时间，默认：300
     */
    private int keepAliveSeconds = 300;
}
