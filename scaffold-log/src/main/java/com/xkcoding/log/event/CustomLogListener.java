/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.log.event;


import com.xkcoding.common.utils.UrlUtil;
import com.xkcoding.common.utils.WebUtil;
import com.xkcoding.scaffold.launcher.props.ScaffoldProperties;
import com.xkcoding.scaffold.launcher.server.ServerInfo;
import com.xkcoding.log.constant.EventConstant;
import com.xkcoding.log.model.LogCustom;
import com.xkcoding.log.service.LogService;
import com.xkcoding.log.service.SecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 异步监听自定义日志事件
 * </p>
 *
 * @package: com.xkcoding.log.event
 * @description: 异步监听自定义日志事件
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:33
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@AllArgsConstructor
public class CustomLogListener {

    private final LogService logService;
    private final SecurityService securityService;
    private final ServerInfo serverInfo;
    private final ScaffoldProperties scaffoldProperties;

    @Async
    @Order
    @EventListener(CustomLogEvent.class)
    @SuppressWarnings("unchecked")
    public void saveUsualLog(CustomLogEvent event) {
        Map<String, Object> source = (Map<String, Object>) event.getSource();
        LogCustom logCustom = (LogCustom) source.get(EventConstant.EVENT_LOG);
        HttpServletRequest request = (HttpServletRequest) source.get(EventConstant.EVENT_REQUEST);
        logCustom.setRequestUri(UrlUtil.getPath(request.getRequestURI()));
        logCustom.setUserAgent(request.getHeader(WebUtil.USER_AGENT_HEADER));
        logCustom.setMethod(request.getMethod());
        logCustom.setParams(WebUtil.getRequestParamString(request));
        logCustom.setServerHost(serverInfo.getHostName());
        logCustom.setServiceId(scaffoldProperties.getName());
        logCustom.setEnv(scaffoldProperties.getEnv());
        logCustom.setServerIp(serverInfo.getIpWithPort());
        logCustom.setCreateBy(securityService.getCurrentUserName(request));
        logCustom.setCreateTime(LocalDateTime.now());
        logService.saveCustomLog(logCustom);
    }

}
