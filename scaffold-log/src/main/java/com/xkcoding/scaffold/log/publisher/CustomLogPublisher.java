/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.log.publisher;

import com.xkcoding.scaffold.common.utils.SpringUtil;
import com.xkcoding.scaffold.common.utils.WebUtil;
import com.xkcoding.scaffold.log.constant.EventConstant;
import com.xkcoding.scaffold.log.event.CustomLogEvent;
import com.xkcoding.scaffold.log.model.LogCustom;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 自定义日志信息事件发送
 * </p>
 *
 * @package: com.xkcoding.scaffold.log.publisher
 * @description: 自定义日志信息事件发送
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:28
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class CustomLogPublisher {

    public static void publishEvent(String level, String id, String data) {
        HttpServletRequest request = WebUtil.getRequest();
        LogCustom logCustom = new LogCustom();
        logCustom.setLogLevel(level);
        logCustom.setLogId(id);
        logCustom.setLogData(data);
        Map<String, Object> event = new HashMap<>(16);
        event.put(EventConstant.EVENT_LOG, logCustom);
        event.put(EventConstant.EVENT_REQUEST, request);
        SpringUtil.publishEvent(new CustomLogEvent(event));
    }

}
