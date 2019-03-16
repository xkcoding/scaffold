/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.scaffold.launcher.server;

import com.xkcoding.scaffold.launcher.utils.IpUtil;
import lombok.Getter;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 服务器信息
 * </p>
 *
 * @package: com.xkcoding.scaffold.launcher.server
 * @description: 服务器信息
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 15:15
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
@Configuration
public class ServerInfo implements SmartInitializingSingleton {
    private final ServerProperties serverProperties;
    private String hostName;
    private String ip;
    private Integer port;
    private String ipWithPort;

    @Autowired(required = false)
    public ServerInfo(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.hostName = IpUtil.getHostName();
        this.ip = IpUtil.getHostIp();
        this.port = ObjectUtils.isEmpty(serverProperties.getPort()) ? 8080 : serverProperties.getPort();
        this.ipWithPort = String.format("%s:%d", ip, port);
    }
}
