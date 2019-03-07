/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.common.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.xkcoding.common.jackson.MappingApiJackson2HttpMessageConverter;
import com.xkcoding.common.utils.xss.XssFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import java.util.List;

/**
 * <p>
 * 消息配置类
 * </p>
 *
 * @package: com.xkcoding.common.config
 * @description: 消息配置类
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 17:07
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MessageConfiguration implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    /**
     * 使用 JACKSON 作为JSON MessageConverter
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(x -> x instanceof StringHttpMessageConverter || x instanceof AbstractJackson2HttpMessageConverter);
        converters.add(new StringHttpMessageConverter(Charsets.UTF_8));
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new ResourceRegionHttpMessageConverter());
        converters.add(new MappingApiJackson2HttpMessageConverter(objectMapper));
    }

    /**
     * 防XSS注入
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }

}
