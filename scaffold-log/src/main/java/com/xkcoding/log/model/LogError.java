/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.log.model;


import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务异常日志实体类
 * </p>
 *
 * @package: com.xkcoding.log.model
 * @description: 服务异常日志实体类
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:28
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class LogError implements Serializable {
    private static final long serialVersionUID = -616342209061133851L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 应用名
     */
    private String serviceId;
    /**
     * 环境
     */
    private String env;
    /**
     * 服务器 ip
     */
    private String serverIp;
    /**
     * 服务器名
     */
    private String serverHost;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 请求url
     */
    @Nullable
    private String requestUri;
    /**
     * 操作方式
     */
    private String method;
    /**
     * 堆栈信息
     */
    private String stackTrace;
    /**
     * 异常名
     */
    private String exceptionName;
    /**
     * 异常消息
     */
    private String message;
    /**
     * 类名
     */
    private String methodClass;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 操作提交的数据
     */
    private String params;
    /**
     * 代码行数
     */
    private Integer lineNumber;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;
}
