/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.config;

import com.xkcoding.scaffold.notification.constants.ScaffoldNotificationConstant;
import com.xkcoding.scaffold.notification.props.EmailProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * <p>
 * Thymeleaf自动装配
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.config
 * @description: Thymeleaf自动装配
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 15:29
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@AllArgsConstructor
@ConditionalOnProperty(value = "scaffold.notification.email.enabled", havingValue = "true")
public class ScaffoldTemplateAutoConfiguration {
    private final EmailProperties emailProperties;

    @Bean(ScaffoldNotificationConstant.EMAIL_TEMPLATE_ENGINE_BEAN)
    public TemplateEngine templateEngine(@Qualifier(ScaffoldNotificationConstant.EMAIL_TEMPLATE_RESOLVER_BEAN) SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.addTemplateResolver(templateResolver);
        engine.getConfiguration();

        return engine;
    }

    @Bean(ScaffoldNotificationConstant.EMAIL_TEMPLATE_RESOLVER_BEAN)
    public SpringResourceTemplateResolver templateResolver(ApplicationContext context) {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(context);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setPrefix(emailProperties.getPrefix());
        templateResolver.setSuffix(emailProperties.getSuffix());
        return templateResolver;
    }
}
