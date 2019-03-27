/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.codegen.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 代码生成器配置
 * </p>
 *
 * @package: com.xkcoding.scaffold.codegen.props
 * @description: 代码生成器配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-27 10:23
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@ConfigurationProperties(prefix = "scaffold.codegen")
public class ScaffoldCodegenProperties {
    /**
     * 基础包名
     */
    private String basePackageName = "com.xkcoding";
    /**
     * 模块名
     */
    private String moduleName = "codegen";
    /**
     * 作者
     */
    private String author = "Yangkai.Shen";
    /**
     * 表前缀(类名不会包含表前缀)
     */
    private String tablePrefix = "tb_";
}
