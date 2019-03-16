/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.log.event;

import com.xkcoding.scaffold.common.utils.UrlUtil;
import com.xkcoding.scaffold.common.utils.WebUtil;
import com.xkcoding.scaffold.launcher.props.ScaffoldProperties;
import com.xkcoding.scaffold.launcher.server.ServerInfo;
import com.xkcoding.scaffold.log.constant.EventConstant;
import com.xkcoding.scaffold.log.model.LogApi;
import com.xkcoding.scaffold.log.service.LogService;
import com.xkcoding.scaffold.log.service.SecurityService;
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
 * 异步监听操作日志事件
 * </p>
 *
 * @package: com.xkcoding.scaffold.log.event
 * @description: 异步监听操作日志事件
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:32
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@AllArgsConstructor
public class ApiLogListener {

    private final LogService logService;
    private final SecurityService securityService;
    private final ServerInfo serverInfo;
    private final ScaffoldProperties scaffoldProperties;

    @Async
    @Order
    @EventListener(ApiLogEvent.class)
    @SuppressWarnings("unchecked")
    public void saveApiLog(ApiLogEvent event) {
        Map<String, Object> source = (Map<String, Object>) event.getSource();
        LogApi logApi = (LogApi) source.get(EventConstant.EVENT_LOG);
        HttpServletRequest request = (HttpServletRequest) source.get(EventConstant.EVENT_REQUEST);
        logApi.setServiceId(scaffoldProperties.getName());
        logApi.setServerHost(serverInfo.getHostName());
        logApi.setServerIp(serverInfo.getIpWithPort());
        logApi.setEnv(scaffoldProperties.getEnv());
        logApi.setRemoteIp(WebUtil.getIP(request));
        logApi.setUserAgent(request.getHeader(WebUtil.USER_AGENT_HEADER));
        logApi.setRequestUri(UrlUtil.getPath(request.getRequestURI()));
        logApi.setMethod(request.getMethod());
        logApi.setParams(WebUtil.getRequestParamString(request));
        logApi.setCreateBy(securityService.getCurrentUserName(request));
        logApi.setCreateTime(LocalDateTime.now());
        logService.saveApiLog(logApi);
    }

}
