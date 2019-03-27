/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.codegen.service.impl;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkcoding.scaffold.codegen.mapper.ScaffoldCodegenMapper;
import com.xkcoding.scaffold.codegen.model.ColumnInfo;
import com.xkcoding.scaffold.codegen.model.TableInfo;
import com.xkcoding.scaffold.codegen.model.payload.GenConfigRequest;
import com.xkcoding.scaffold.codegen.model.payload.TablePageRequest;
import com.xkcoding.scaffold.codegen.props.ScaffoldCodegenProperties;
import com.xkcoding.scaffold.codegen.service.ScaffoldCodegenService;
import com.xkcoding.scaffold.codegen.utils.ScaffoldCodegenUtil;
import com.xkcoding.scaffold.launcher.constants.AppConstant;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 代码生成接口实现
 * </p>
 *
 * @package: com.xkcoding.scaffold.codegen.service.impl
 * @description: 代码生成接口实现
 * @author: yangkai.shen
 * @date: Created in 2019-03-27 11:34
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@Profile({AppConstant.DEV_CODE, AppConstant.TEST_CODE})
@AllArgsConstructor
public class ScaffoldCodegenServiceImpl implements ScaffoldCodegenService {
    private final ScaffoldCodegenMapper scaffoldCodegenMapper;
    private final ScaffoldCodegenProperties scaffoldCodegenProperties;

    /**
     * 生成代码
     *
     * @param genConfig 生成配置
     * @return 代码压缩文件
     */
    @Override
    public byte[] generatorCode(GenConfigRequest genConfig) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        //查询表信息
        TableInfo table = queryTable(genConfig.getTableName());
        //查询列信息
        List<ColumnInfo> columns = queryColumns(genConfig.getTableName());
        //生成代码
        ScaffoldCodegenUtil.generatorCode(scaffoldCodegenProperties, genConfig, table, columns, zip);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    /**
     * 分页查询表信息
     *
     * @param request 请求参数
     * @return 表名分页信息
     */
    @Override
    public IPage<TableInfo> listTables(TablePageRequest request) {
        Page<TableInfo> page = new Page<>();
        page.setCurrent(request.getCurrentPage());
        page.setSize(request.getPageSize());
        List<TableInfo> tableInfos = scaffoldCodegenMapper.queryList(page, request.getTableName());
        page.setRecords(tableInfos);
        return page;
    }

    /**
     * 查询表字段信息列表
     *
     * @param tableName 表名
     * @return 表字段信息列表
     */
    private List<ColumnInfo> queryColumns(String tableName) {
        return scaffoldCodegenMapper.queryColumns(tableName);
    }

    /**
     * 查询表信息
     *
     * @param tableName 表名
     * @return 表信息
     */
    private TableInfo queryTable(String tableName) {
        return scaffoldCodegenMapper.queryTable(tableName);
    }
}
