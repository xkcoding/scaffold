/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.codegen.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.xkcoding.scaffold.codegen.props.ScaffoldCodegenProperties;
import com.xkcoding.scaffold.launcher.constants.AppConstant;
import lombok.AllArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;

/**
 * <p>
 * 代码生成器自动装配
 * </p>
 *
 * @package: com.xkcoding.scaffold.codegen.config
 * @description: 代码生成器自动装配
 * @author: yangkai.shen
 * @date: Created in 2019-03-27 10:19
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({ScaffoldCodegenProperties.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@Profile({AppConstant.DEV_CODE, AppConstant.TEST_CODE})
@MapperScan(basePackages = {"com.xkcoding.scaffold.codegen.mapper"})
public class ScaffoldCodegenConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/codegen.html").addResourceLocations("classpath:/codegen/");
        registry.addResourceHandler("/libs/**").addResourceLocations("classpath:/codegen/libs/");
    }

    @Bean
    @ConditionalOnMissingBean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
