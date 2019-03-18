/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.service.dingtalk;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xkcoding.scaffold.common.utils.Assert;
import com.xkcoding.scaffold.notification.model.dingtalk.AbstractDingTalkMessage;
import com.xkcoding.scaffold.notification.props.DingTalkProperties;
import com.xkcoding.scaffold.notification.service.AbstractMessageSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 钉钉消息发送
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.service.dingtalk
 * @description: 钉钉消息发送
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 15:39
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "scaffold.notification.dingtalk.enabled", havingValue = "true")
public class DingTalkMessageSender extends AbstractMessageSender<AbstractDingTalkMessage> {
    private final DingTalkProperties dingTalkProperties;

    /**
     * 数据校验
     *
     * @param message 消息实体
     */
    @Override
    public void validate(AbstractDingTalkMessage message) {
        String webhook = dingTalkProperties.getWebhook();
        Assert.isBlank(webhook, "钉钉配置错误，webhook为空");
    }

    /**
     * 业务处理
     *
     * @param message 消息实体
     * @return boolean
     */
    @Override
    public boolean process(AbstractDingTalkMessage message) {
        String webhook = dingTalkProperties.getWebhook();
        String result = HttpUtil.post(webhook, JSONUtil.toJsonPrettyStr(message));
        log.info("钉钉提醒成功,报文响应:{}", result);
        return true;
    }

    /**
     * 失败处理
     *
     * @param message 消息实体
     */
    @Override
    public void fail(AbstractDingTalkMessage message) {
        // do nothing
    }
}
