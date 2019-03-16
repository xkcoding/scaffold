/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.scaffold.launcher.service;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * <p>
 * launcher 扩展 用于一些组件发现
 * </p>
 *
 * @package: com.xkcoding.scaffold.launcher.service
 * @description: launcher 扩展 用于一些组件发现
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 11:23
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface LauncherService {

    /**
     * 启动时 处理 SpringApplicationBuilder
     *
     * @param builder SpringApplicationBuilder
     * @param appName 服务名
     * @param profile 配置名
     * @param isLocalDev 是否本地开发
     */
    void launcher(SpringApplicationBuilder builder, String appName, String profile, boolean isLocalDev);

}
