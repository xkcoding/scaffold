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
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 未知异常实体类
 * </p>
 *
 * @package: com.xkcoding.log.model
 * @description: 未知异常实体类
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:28
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class LogCustom implements Serializable {
    private static final long serialVersionUID = 7115088311761096702L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 服务ID
     */
    private String serviceId;
    /**
     * 服务器名
     */
    private String serverHost;
    /**
     * 服务器IP地址
     */
    private String serverIp;
    /**
     * 系统环境
     */
    private String env;
    /**
     * 日志级别
     */
    private String logLevel;
    /**
     * 日志业务id
     */
    private String logId;
    /**
     * 日志数据
     */
    private String logData;
    /**
     * 操作方式
     */
    private String method;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 操作提交的数据
     */
    private String params;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;

}
