/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.scaffold.log.model;


import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志实体类
 * </p>
 *
 * @package: com.xkcoding.scaffold.log.model
 * @description: 操作日志实体类
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:28
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class LogApi implements Serializable {
    private static final long serialVersionUID = 5243202901554667255L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 日志类型
     */
    private String type;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 服务ID
     */
    private String serviceId;
    /**
     * 服务器 ip
     */
    private String serverIp;
    /**
     * 服务器名
     */
    private String serverHost;
    /**
     * 环境
     */
    private String env;
    /**
     * 操作IP地址
     */
    private String remoteIp;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 操作方式
     */
    private String method;
    /**
     * 方法类
     */
    private String methodClass;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 操作提交的数据
     */
    private String params;
    /**
     * 执行时间
     */
    private String time;

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
