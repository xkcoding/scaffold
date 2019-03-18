/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.service.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.xkcoding.scaffold.common.utils.Assert;
import com.xkcoding.scaffold.notification.model.sms.SmsMessage;
import com.xkcoding.scaffold.notification.props.SmsAliyunProperties;
import com.xkcoding.scaffold.notification.service.AbstractMessageSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 阿里大鱼短信发送
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.service.sms
 * @description: 阿里大鱼短信发送
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 15:39
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@Component
@AllArgsConstructor
public class SmsAliyunMessageSender extends AbstractMessageSender<SmsMessage> {
    private final SmsAliyunProperties smsAliyunProperties;
    private static final String PRODUCT = "Dysmsapi";
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    /**
     * 数据校验
     *
     * @param message 消息实体
     */
    @Override
    public void validate(SmsMessage message) {
        Assert.isBlank(message.getMobile(), "手机号不能为空");
        Assert.isBlank(message.getContent(), "短信内容不能为空");
    }

    /**
     * 业务处理
     *
     * @param message 消息实体
     * @return boolean
     */
    @Override
    public boolean process(SmsMessage message) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsAliyunProperties.getAccessKey(), smsAliyunProperties
                .getSecretKey());
        try {
            DefaultProfile.addEndpoint("cn-hou", "cn-hangzhou", PRODUCT, DOMAIN);
        } catch (ClientException e) {
            log.error("初始化短信 SDK 异常", e);
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(message.getMobile());

        //必填:短信签名-可在短信控制台中找到
        request.setSignName(message.getSignName());

        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsAliyunProperties.getChannels().get(message.getTemplate()));

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"
        request.setTemplateParam(message.getContent());
        request.setOutId(message.getMobile());

        //hint 此处可能会抛出异常，注意catch
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            log.info("短信发送完毕，手机号：{}，返回状态：{}", message.getMobile(), sendSmsResponse.getCode());
        } catch (ClientException e) {
            log.error("短信发送异常，手机号：{}", message.getMobile());
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 失败处理
     *
     * @param message 消息实体
     */
    @Override
    public void fail(SmsMessage message) {
        log.error("短信发送失败，当前短信签名：{}，当前短信模板：{}，当前手机号：{}", message.getSignName(), message.getTemplate(), message.getMobile());
    }
}
