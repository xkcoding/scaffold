/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.web.error;

import cn.hutool.core.bean.BeanUtil;
import com.xkcoding.scaffold.common.api.R;
import com.xkcoding.scaffold.common.api.ResultCode;
import com.xkcoding.log.publisher.ErrorLogPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @package: com.xkcoding.web.error
 * @description: 全局异常处理
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:56
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class ScaffoldErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        String requestUri = this.getAttr(webRequest, "javax.servlet.error.request_uri");
        Integer status = this.getAttr(webRequest, "javax.servlet.error.status_code");
        Throwable error = getError(webRequest);
        R result;
        if (error == null) {
            log.error("URL:{} error status:{}", requestUri, status);
            result = R.fail(ResultCode.FAILURE, "系统未知异常[HttpStatus]:" + status);
        } else {
            log.error(String.format("URL: %s 状态码: %d", requestUri, status), error);
            result = R.fail(status, error.getMessage());
        }
        //发送服务异常事件
        ErrorLogPublisher.publishEvent(error, requestUri);
        return BeanUtil.beanToMap(result);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    private <T> T getAttr(WebRequest webRequest, String name) {
        return (T) webRequest.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

}
