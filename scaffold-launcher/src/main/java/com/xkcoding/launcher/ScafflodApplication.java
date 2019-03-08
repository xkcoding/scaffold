/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.launcher;

import com.xkcoding.launcher.constants.AppConstant;
import com.xkcoding.launcher.service.LauncherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;

/**
 * <p>
 * 脚手架通用启动器，解决服务名以及环境变量问题
 * </p>
 *
 * @package: com.xkcoding.launcher
 * @description: 脚手架通用启动器，解决服务名以及环境变量问题
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 11:21
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class ScafflodApplication {
    /**
     * 创建Spring上下文
     * java -jar app.jar --spring.profiles.active=prod --server.port=2333
     *
     * @param appName 服务名
     * @param source  Application类
     * @return 创建一个当前环境的Spring上下文
     */
    public static ConfigurableApplicationContext run(String appName, Class source, String... args) {
        SpringApplicationBuilder builder = createSpringApplicationBuilder(appName, source, args);
        return builder.run(args);
    }

    public static SpringApplicationBuilder createSpringApplicationBuilder(String appName, Class source, String... args) {
        Assert.hasText(appName, "[appName]服务名不能为空");
        // 读取环境变量，使用spring boot的规则
        ConfigurableEnvironment environment = new StandardEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(new SimpleCommandLinePropertySource(args));
        propertySources.addLast(new MapPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, environment
                .getSystemProperties()));
        propertySources.addLast(new SystemEnvironmentPropertySource(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, environment
                .getSystemEnvironment()));
        // 获取配置的环境变量
        String[] activeProfiles = environment.getActiveProfiles();
        // 判断环境:dev、test、prod
        List<String> profiles = Arrays.asList(activeProfiles);
        // 预设的环境
        List<String> presetProfiles = new ArrayList<>(Arrays.asList(AppConstant.DEV_CODE, AppConstant.TEST_CODE, AppConstant.PROD_CODE));
        // 交集
        presetProfiles.retainAll(profiles);
        // 当前使用
        List<String> activeProfileList = new ArrayList<>(profiles);
        Function<Object[], String> joinFun = StringUtils::arrayToCommaDelimitedString;
        SpringApplicationBuilder builder = new SpringApplicationBuilder(source);
        String profile;
        if (activeProfileList.isEmpty()) {
            // 默认dev开发
            profile = AppConstant.DEV_CODE;
            activeProfileList.add(profile);
            builder.profiles(profile);
        } else if (activeProfileList.size() == 1) {
            profile = activeProfileList.get(0);
        } else {
            // 同时存在dev、test、prod环境时
            throw new RuntimeException("同时存在环境变量:[" + StringUtils.arrayToCommaDelimitedString(activeProfiles) + "]");
        }
        String startJarPath = ScafflodApplication.class.getResource("/").getPath().split("!")[0];
        String activePros = joinFun.apply(activeProfileList.toArray());
        log.info("----启动中，读取到的环境变量:[{}]，jar地址:[{}]----", activePros, startJarPath);
        Properties props = System.getProperties();
        props.setProperty("spring.application.name", appName);
        props.setProperty("spring.profiles.active", profile);
        props.setProperty("spring.banner.location", "classpath:scaffold_banner.txt");
        props.setProperty("scaffold.name", appName);
        props.setProperty("scaffold.version", AppConstant.SCAFFOLD_VERSION);
        props.setProperty("scaffold.env", profile);
        props.setProperty("scaffold.is-local", String.valueOf(isLocalDev()));
        props.setProperty("scaffold.dev-mode", profile.equals(AppConstant.PROD_CODE) ? "false" : "true");
        // 加载自定义组件
        ServiceLoader<LauncherService> loader = ServiceLoader.load(LauncherService.class);
        // 启动组件
        loader.forEach(launcherService -> launcherService.launcher(builder, appName, profile, isLocalDev()));
        return builder;
    }

    /**
     * 判断是否为本地开发环境
     *
     * @return boolean
     */
    public static boolean isLocalDev() {
        String osName = System.getProperty("os.name");
        return StringUtils.hasText(osName) && !(AppConstant.OS_NAME_LINUX.equals(osName.toUpperCase()));
    }
}
