/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.codegen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkcoding.scaffold.codegen.model.ColumnInfo;
import com.xkcoding.scaffold.codegen.model.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 代码生成器 Mapper
 * </p>
 *
 * @package: com.xkcoding.scaffold.codegen.mapper
 * @description: 代码生成器 Mapper
 * @author: yangkai.shen
 * @date: Created in 2019-03-27 11:36
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ScaffoldCodegenMapper {
    // @formatter:off

    /**
     * 分页查询表格
     *
     * @param page      分页信息
     * @param tableName 表名
     * @return 分页查询表格
     */
    @Select("<script>"
            + " select `table_name`, `engine`, `table_comment`, `create_time`"
            + " from `information_schema`.`tables`"
            + " where `table_schema` = (select database())"
            + "<if test=\"tableName != null and tableName.trim() != ''\">"
            + " and `table_name` like concat('%', #{tableName}, '%')"
            + "</if>"
            + " order by `create_time` desc"
            + "</script>")
    List<TableInfo> queryList(Page page, @Param("tableName") String tableName);

    /**
     * 查询表信息
     *
     * @param tableName 表名称
     * @return 查询表信息
     */
    @Select(" select `table_name`, `engine`, `table_comment`, `create_time`"
            + " from `information_schema`.`tables`"
            + " where `table_schema` = (select database())"
            + " and `table_name` = #{tableName}")
    TableInfo queryTable(String tableName);

    /**
     * 查询表列信息
     *
     * @param tableName 表名称
     * @return 查询表列信息
     */
    @Select(" select `column_name`, `data_type`, `column_comment`, `column_key`, `extra`"
            + " from `information_schema`.`columns`"
            + " where `table_name` = #{tableName}"
            + " and `table_schema` = (select database())"
            + " order by `ordinal_position`")
    List<ColumnInfo> queryColumns(String tableName);

    // @formatter:on
}
