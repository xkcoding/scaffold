/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.codegen.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.google.common.collect.Lists;
import com.xkcoding.scaffold.codegen.constants.ScaffoldCodegenConstants;
import com.xkcoding.scaffold.codegen.model.ColumnInfo;
import com.xkcoding.scaffold.codegen.model.TableInfo;
import com.xkcoding.scaffold.codegen.model.payload.GenConfigRequest;
import com.xkcoding.scaffold.codegen.model.vo.ColumnInfoVO;
import com.xkcoding.scaffold.codegen.model.vo.TableInfoVO;
import com.xkcoding.scaffold.codegen.props.ScaffoldCodegenProperties;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 代码生成工具类
 * </p>
 *
 * @package: com.xkcoding.scaffold.codegen.utils
 * @description: 代码生成工具类
 * @author: yangkai.shen
 * @date: Created in 2019-03-27 10:47
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@UtilityClass
public class ScaffoldCodegenUtil {

    private final String ENTITY_JAVA_VM = "Model.java.vm";
    private final String MAPPER_JAVA_VM = "Mapper.java.vm";
    private final String SERVICE_JAVA_VM = "Service.java.vm";
    private final String SERVICE_IMPL_JAVA_VM = "ServiceImpl.java.vm";
    private final String CONTROLLER_JAVA_VM = "Controller.java.vm";
    private final String MAPPER_XML_VM = "Mapper.xml.vm";
    private final String API_JS_VM = "api.js.vm";

    private List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("vmtemplate/Model.java.vm");
        templates.add("vmtemplate/Mapper.java.vm");
        templates.add("vmtemplate/Mapper.xml.vm");
        templates.add("vmtemplate/Service.java.vm");
        templates.add("vmtemplate/ServiceImpl.java.vm");
        templates.add("vmtemplate/Controller.java.vm");

        templates.add("vmtemplate/api.js.vm");
        return templates;
    }

    /**
     * 生成代码
     */
    public void generatorCode(ScaffoldCodegenProperties codegenProperties, GenConfigRequest genConfigRequest, TableInfo table, List<ColumnInfo> columns, ZipOutputStream zip) {
        //配置信息
        Props mappingConfig = getConfig();
        boolean hasBigDecimal = false;
        //表信息
        TableInfoVO tableInfoVO = new TableInfoVO();
        tableInfoVO.setTableName(table.getTableName());

        if (StrUtil.isNotBlank(genConfigRequest.getComments())) {
            tableInfoVO.setComments(genConfigRequest.getComments());
        } else {
            tableInfoVO.setComments(table.getTableComment());
        }

        String tablePrefix;
        if (StrUtil.isNotBlank(genConfigRequest.getTablePrefix())) {
            tablePrefix = genConfigRequest.getTablePrefix();
        } else {
            tablePrefix = codegenProperties.getTablePrefix();
        }

        //表名转换成Java类名
        String className = tableToJava(tableInfoVO.getTableName(), tablePrefix);
        tableInfoVO.setCaseClassName(className);
        tableInfoVO.setLowerClassName(StrUtil.lowerFirst(className));

        //列信息
        List<ColumnInfoVO> columnList = Lists.newArrayList();
        for (ColumnInfo column : columns) {
            ColumnInfoVO columnInfoVO = new ColumnInfoVO();
            columnInfoVO.setColumnName(column.getColumnName());
            columnInfoVO.setDataType(column.getDataType());
            columnInfoVO.setComments(column.getColumnComment());
            columnInfoVO.setExtra(column.getExtra());

            //列名转换成Java属性名
            String attrName = columnToJava(columnInfoVO.getColumnName());
            columnInfoVO.setCaseAttrName(attrName);
            columnInfoVO.setLowerAttrName(StrUtil.lowerFirst(attrName));

            //列的数据类型，转换成Java类型
            String attrType = mappingConfig.getStr(columnInfoVO.getDataType(), "unknownType");
            columnInfoVO.setAttrType(attrType);
            if (!hasBigDecimal && "BigDecimal".equals(attrType)) {
                hasBigDecimal = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.getColumnKey()) && tableInfoVO.getPk() == null) {
                tableInfoVO.setPk(columnInfoVO);
            }

            columnList.add(columnInfoVO);
        }
        tableInfoVO.setColumns(columnList);

        //没主键，则第一个字段为主键
        if (tableInfoVO.getPk() == null) {
            tableInfoVO.setPk(tableInfoVO.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        //封装模板数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("tableName", tableInfoVO.getTableName());
        map.put("pk", tableInfoVO.getPk());
        map.put("className", tableInfoVO.getCaseClassName());
        map.put("classname", tableInfoVO.getLowerClassName());
        map.put("pathName", tableInfoVO.getLowerClassName().toLowerCase());
        map.put("columns", tableInfoVO.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("datetime", DateUtil.now());
        map.put("year", DateUtil.year(new Date()));

        if (StrUtil.isNotBlank(genConfigRequest.getComments())) {
            map.put("comments", genConfigRequest.getComments());
        } else {
            map.put("comments", tableInfoVO.getComments());
        }

        if (StrUtil.isNotBlank(genConfigRequest.getAuthor())) {
            map.put("author", genConfigRequest.getAuthor());
        } else {
            map.put("author", codegenProperties.getAuthor());
        }

        if (StrUtil.isNotBlank(genConfigRequest.getModuleName())) {
            map.put("moduleName", genConfigRequest.getModuleName());
        } else {
            map.put("moduleName", codegenProperties.getModuleName());
        }

        if (StrUtil.isNotBlank(genConfigRequest.getPackageName())) {
            map.put("package", genConfigRequest.getPackageName());
            map.put("mainPath", genConfigRequest.getPackageName());
        } else {
            map.put("package", codegenProperties.getBasePackageName());
            map.put("mainPath", codegenProperties.getBasePackageName());
        }
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, CharsetUtil.UTF_8);
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(Objects.requireNonNull(getFileName(template, tableInfoVO.getCaseClassName(), map
                        .get("package")
                        .toString(), map.get("moduleName").toString()))));
                IoUtil.write(zip, StandardCharsets.UTF_8, false, sw.toString());
                IoUtil.close(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException("渲染模板失败，表名：" + tableInfoVO.getTableName(), e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    private String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    private String tableToJava(String tableName, String tablePrefix) {
        if (StrUtil.isNotBlank(tablePrefix)) {
            tableName = tableName.replaceFirst(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    private Props getConfig() {
        return new Props("scaffold-codegen.properties");
    }

    /**
     * 获取文件名
     */
    private String getFileName(String template, String className, String packageName, String moduleName) {
        // 包路径
        String packagePath = ScaffoldCodegenConstants.SIGNATURE + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        // 资源路径
        String resourcePath = ScaffoldCodegenConstants.SIGNATURE + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        // api路径
        String apiPath = ScaffoldCodegenConstants.SIGNATURE + File.separator + "api" + File.separator;

        if (StrUtil.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }

        if (template.contains(ENTITY_JAVA_VM)) {
            return packagePath + "model" + File.separator + className + ".java";
        }

        if (template.contains(MAPPER_JAVA_VM)) {
            return packagePath + "mapper" + File.separator + className + "Mapper.java";
        }

        if (template.contains(SERVICE_JAVA_VM)) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains(SERVICE_IMPL_JAVA_VM)) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains(CONTROLLER_JAVA_VM)) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains(MAPPER_XML_VM)) {
            return resourcePath + "mapper" + File.separator + className + "Mapper.xml";
        }

        if (template.contains(API_JS_VM)) {
            return apiPath + className.toLowerCase() + ".js";
        }

        return null;
    }
}
