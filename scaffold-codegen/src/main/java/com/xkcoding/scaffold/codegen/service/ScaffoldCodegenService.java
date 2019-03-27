/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.codegen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xkcoding.scaffold.codegen.model.TableInfo;
import com.xkcoding.scaffold.codegen.model.payload.GenConfigRequest;
import com.xkcoding.scaffold.codegen.model.payload.TablePageRequest;

/**
 * <p>
 * 代码生成接口
 * </p>
 *
 * @package: com.xkcoding.scaffold.codegen.service
 * @description: 代码生成接口
 * @author: yangkai.shen
 * @date: Created in 2019-03-27 11:28
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ScaffoldCodegenService {
    /**
     * 生成代码
     *
     * @param genConfig 生成配置
     * @return 代码压缩文件
     */
    byte[] generatorCode(GenConfigRequest genConfig);

    /**
     * 分页查询表信息
     *
     * @param request 请求参数
     * @return 表名分页信息
     */
    IPage<TableInfo> listTables(TablePageRequest request);
}
