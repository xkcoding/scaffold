/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.codegen.model;

import lombok.Data;

/**
 * <p>
 * table类型信息
 * </p>
 *
 * @package: com.xkcoding.scaffold.codegen.model
 * @description: table类型信息
 * @author: yangkai.shen
 * @date: Created in 2019-03-27 10:49
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class TableInfo {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 表引擎
     */
    private String engine;
    /**
     * 表创建时间
     */
    private String createTime;
}
