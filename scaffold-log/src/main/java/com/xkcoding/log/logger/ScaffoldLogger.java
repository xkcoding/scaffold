/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.log.logger;

import com.xkcoding.log.publisher.CustomLogPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * 自定义日志工具类
 * </p>
 *
 * @package: com.xkcoding.log.logger
 * @description: 自定义日志工具类
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 15:30
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class ScaffoldLogger implements InitializingBean {

    @Value("${spring.application.name}")
    private String serviceId;

    public void info(String id, String data) {
        CustomLogPublisher.publishEvent("info", id, data);
    }

    public void debug(String id, String data) {
        CustomLogPublisher.publishEvent("debug", id, data);
    }

    public void warn(String id, String data) {
        CustomLogPublisher.publishEvent("warn", id, data);
    }

    public void error(String id, String data) {
        CustomLogPublisher.publishEvent("error", id, data);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(serviceId + ": ScaffoldLogger 初始化成功!");
    }

}
