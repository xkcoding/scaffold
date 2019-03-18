/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.service;

import com.xkcoding.scaffold.notification.model.Message;

/**
 * <p>
 * 消息发送器抽象类
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.service
 * @description: 消息发送器抽象类
 * @author: yangkai.shen
 * @date: Created in 2019-03-16 23:53
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public abstract class AbstractMessageSender<T extends Message> implements MessageSenderService<T> {
    /**
     * 执行入口
     *
     * @param message 消息实体
     */
    @Override
    public void execute(T message) {
        validate(message);
        if (!process(message)) {
            fail(message);
        }
    }

    /**
     * 数据校验
     *
     * @param message 消息实体
     */
    @Override
    public abstract void validate(T message);

    /**
     * 业务处理
     *
     * @param message 消息实体
     * @return boolean
     */
    @Override
    public abstract boolean process(T message);

    /**
     * 失败处理
     *
     * @param message 消息实体
     */
    @Override
    public abstract void fail(T message);
}
