/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.service;

/**
 * <p>
 * 信息发送接口
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.service
 * @description: 信息发送接口
 * @author: yangkai.shen
 * @date: Created in 2019-03-16 23:52
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface MessageSenderService {
    /**
     * 执行入口
     */
    void execute();

    /**
     * 数据校验
     */
    void check();

    /**
     * 业务处理
     *
     * @return boolean
     */
    boolean process();

    /**
     * 失败处理
     */
    void failHandler();
}
