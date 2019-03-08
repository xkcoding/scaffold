/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.log.publisher;

import com.xkcoding.common.constants.ScaffoldConstant;
import com.xkcoding.common.utils.SpringUtil;
import com.xkcoding.common.utils.WebUtil;
import com.xkcoding.log.annotations.ApiLog;
import com.xkcoding.log.constant.EventConstant;
import com.xkcoding.log.event.ApiLogEvent;
import com.xkcoding.log.model.LogApi;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * API操作日志信息事件发送
 * </p>
 *
 * @package: com.xkcoding.log.publisher
 * @description: API操作日志信息事件发送
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 11:22
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class ApiLogPublisher {

    public static void publishEvent(String methodName, String methodClass, ApiLog apiLog, long time) {
        HttpServletRequest request = WebUtil.getRequest();
        LogApi logApi = new LogApi();
        logApi.setType(ScaffoldConstant.LOG_NORMAL_TYPE);
        logApi.setTitle(apiLog.value());
        logApi.setTime(String.valueOf(time));
        logApi.setMethodClass(methodClass);
        logApi.setMethodName(methodName);
        Map<String, Object> event = new HashMap<>(16);
        event.put(EventConstant.EVENT_LOG, logApi);
        event.put(EventConstant.EVENT_REQUEST, request);
        SpringUtil.publishEvent(new ApiLogEvent(event));
    }

}
