/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.test;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * <p>
 * 简化 测试
 * </p>
 *
 * @package: com.xkcoding.scaffold.test
 * @description: 简化 测试
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 09:40
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ScaffoldTest {
    /**
     * 服务名：appName
     *
     * @return appName
     */
    @AliasFor("appName") String value() default "scaffold-test";

    /**
     * 服务名：appName
     *
     * @return appName
     */
    @AliasFor("value") String appName() default "scaffold-test";

    /**
     * profile
     *
     * @return profile
     */
    String profile() default "dev";

    /**
     * 启用 ServiceLoader 加载 launcherService
     *
     * @return 是否启用
     */
    boolean enableLoader() default false;
}
