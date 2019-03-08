/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.common.utils.xss;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.google.common.base.Charsets;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * XSS过滤处理
 * </p>
 *
 * @package: com.xkcoding.common.utils.xss
 * @description: XSS过滤处理
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 16:35
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 没被包装过的HttpServletRequest（特殊场景,需要自己过滤）
     */
    private HttpServletRequest originRequest;

    /**
     * html过滤
     */
    private final static HtmlFilter HTML_FILTER = new HtmlFilter();

    /**
     * 缓存报文,支持多次读取流
     */
    private final String body;

    public XssHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        originRequest = request;
        body = ServletUtil.getBody(originRequest);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        //为空，直接返回
        if (null == super.getHeader(HttpHeaders.CONTENT_TYPE)) {
            return super.getInputStream();
        }

        //非json类型，直接返回
        if (!super.getHeader(HttpHeaders.CONTENT_TYPE)
                .equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE) && !super.getHeader(HttpHeaders.CONTENT_TYPE)
                .equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            return super.getInputStream();
        }

        //为空，直接返回
        String requestStr = body;
        if (StrUtil.isBlank(requestStr)) {
            return super.getInputStream();
        }

        requestStr = xssEncode(requestStr);

        final ByteArrayInputStream bis = new ByteArrayInputStream(requestStr.getBytes(Charsets.UTF_8));

        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bis.read();
            }
        };

    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (StrUtil.isNotBlank(value)) {
            value = xssEncode(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (parameters == null || parameters.length == 0) {
            return null;
        }

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = xssEncode(parameters[i]);
        }
        return parameters;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = new LinkedHashMap<>();
        Map<String, String[]> parameters = super.getParameterMap();
        for (String key : parameters.keySet()) {
            String[] values = parameters.get(key);
            for (int i = 0; i < values.length; i++) {
                values[i] = xssEncode(values[i]);
            }
            map.put(key, values);
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (StrUtil.isNotBlank(value)) {
            value = xssEncode(value);
        }
        return value;
    }

    private String xssEncode(String input) {
        return HTML_FILTER.filter(input);
    }

    /**
     * 获取最原始的request
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getOriginRequest() {
        return originRequest;
    }

    /**
     * 获取最原始的request
     *
     * @param request request
     * @return HttpServletRequest
     */
    public static HttpServletRequest getOriginRequest(HttpServletRequest request) {
        if (request instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) request).getOriginRequest();
        }

        return request;
    }

}
