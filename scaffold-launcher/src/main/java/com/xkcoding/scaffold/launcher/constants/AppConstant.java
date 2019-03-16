/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.launcher.constants;

/**
 * <p>
 * 系统常量
 * </p>
 *
 * @package: com.xkcoding.scaffold.launcher.constants
 * @description: 系统常量
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 11:30
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface AppConstant {
    /**
     * 开发环境
     */
    String DEV_CODE = "dev";
    /**
     * 生产环境
     */
    String PROD_CODE = "prod";
    /**
     * 测试环境
     */
    String TEST_CODE = "test";
    /**
     * 代码部署于 linux 上，工作默认为 Mac 和 Windows
     */
    String OS_NAME_LINUX = "LINUX";
    /**
     * 脚手架版本
     */
    String SCAFFOLD_VERSION = "0.0.1";
}
