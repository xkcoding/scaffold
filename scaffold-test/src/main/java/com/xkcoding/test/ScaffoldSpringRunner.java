/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.test;

import com.xkcoding.scaffold.launcher.ScaffoldApplication;
import com.xkcoding.scaffold.launcher.service.LauncherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runners.model.InitializationError;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * <p>
 * 设置启动参数
 * </p>
 *
 * @package: com.xkcoding.scaffold.test
 * @description: 设置启动参数
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 09:50
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class ScaffoldSpringRunner extends SpringJUnit4ClassRunner {

    public ScaffoldSpringRunner(Class<?> clazz) throws InitializationError, NoSuchFieldException, IllegalAccessException {
        super(clazz);
        setUpTestClass(clazz);
    }

    private void setUpTestClass(Class<?> clazz) throws NoSuchFieldException, IllegalAccessException {
        ScaffoldTest scaffoldTest = AnnotationUtils.getAnnotation(clazz, ScaffoldTest.class);
        if (scaffoldTest == null) {
            throw new ScaffoldTestException(String.format("%s 必须包含 @ScaffoldTest 注解.", clazz));
        }
        String appName = scaffoldTest.appName();
        String profile = scaffoldTest.profile();
        boolean isLocalDev = ScaffoldApplication.isLocalDev();
        Properties props = System.getProperties();
        props.setProperty("spring.application.name", appName);
        props.setProperty("spring.profiles.active", profile);
        props.setProperty("scaffold.env", profile);
        props.setProperty("scaffold.is-local", String.valueOf(isLocalDev));
        props.setProperty("spring.banner.location", "classpath:scaffold_banner.txt");
        log.error("---[junit.test]:[{}]---启动中，读取到的环境变量:[{}]", appName, profile);

        // 是否加载自定义组件
        if (!scaffoldTest.enableLoader()) {
            return;
        }
        ServiceLoader<LauncherService> loader = ServiceLoader.load(LauncherService.class);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(clazz);
        // 启动组件
        loader.forEach(launcherService -> launcherService.launcher(builder, appName, profile, isLocalDev));
        // 反射出 builder 中的 props，兼容用户扩展
        Field field = SpringApplicationBuilder.class.getDeclaredField("defaultProperties");
        field.setAccessible(true);
        @SuppressWarnings("unchecked") Map<String, Object> defaultProperties = (Map<String, Object>) field.get(builder);
        if (!ObjectUtils.isEmpty(defaultProperties)) {
            props.putAll(defaultProperties);
        }
    }

}
