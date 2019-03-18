/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.service.email;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.xkcoding.scaffold.common.utils.Assert;
import com.xkcoding.scaffold.notification.constants.EmailType;
import com.xkcoding.scaffold.notification.constants.ScaffoldNotificationConstant;
import com.xkcoding.scaffold.notification.model.email.EmailMessage;
import com.xkcoding.scaffold.notification.service.AbstractMessageSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * <p>
 * 邮箱发送
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.service.email
 * @description: 邮箱发送
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 15:40
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "scaffold.notification.email.enabled", havingValue = "true")
public class EmailMessageSender extends AbstractMessageSender<EmailMessage> {
    private final JavaMailSender mailSender;
    @Qualifier(ScaffoldNotificationConstant.EMAIL_TEMPLATE_ENGINE_BEAN)
    private final TemplateEngine templateEngine;

    /**
     * 数据校验
     *
     * @param message 消息实体
     */
    @Override
    public void validate(EmailMessage message) {
        if (ObjectUtil.equal(message.getEmailType(), EmailType.SIMPLE)) {
            Assert.isNotEmpty(message.getAttachments(), "简单邮件不支持附件，请更换复杂邮件类型");
            Assert.isNotEmpty(message.getStaticResources(), "简单邮件不支持静态资源，请更换复杂邮件类型");
        }
    }

    /**
     * 业务处理
     *
     * @param message 消息实体
     * @return boolean
     */
    @Override
    public boolean process(EmailMessage message) {
        switch (message.getEmailType()) {
            case SIMPLE:
                return processSimpleEmail(message);
            case MIME:
                return processMimeEmail(message);
            default:
                return false;
        }
    }

    /**
     * 处理复杂邮件类型
     *
     * @param message 邮件内容
     * @return boolean
     */
    private boolean processMimeEmail(EmailMessage message) {
        // 注意邮件发送可能出现异常，注意catch
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(message.getFrom());
            helper.setTo(ArrayUtil.toArray(message.getTos(), String.class));
            helper.setSubject(message.getSubject());

            if (StrUtil.isNotBlank(message.getTemplate())) {
                // 使用模板生成邮件内容
                Context context = new Context();
                // 变量设置
                message.getParams().forEach(context::setVariable);
                String content = templateEngine.process(message.getTemplate(), context);

                helper.setText(content, true);
            } else {
                helper.setText(message.getContent(), true);
            }

            // 设置抄送人列表
            if (CollUtil.isEmpty(message.getCcs())) {
                helper.setCc(ArrayUtil.toArray(message.getCcs(), String.class));
            }

            // 设置附件
            if (CollUtil.isNotEmpty(message.getAttachments())) {
                for (EmailMessage.Attachment attachment : message.getAttachments()) {
                    helper.addAttachment(attachment.getAttachmentName(), attachment.getAttachment());
                }
            }

            // 设置静态资源
            if (CollUtil.isNotEmpty(message.getStaticResources())) {
                for (EmailMessage.StaticResource staticResource : message.getStaticResources()) {
                    helper.addInline(staticResource.getResourceId(), staticResource.getResource());
                }
            }

            mailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            log.error("复杂邮件发送异常！");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 处理简单邮件类型
     *
     * @param message 邮件内容
     * @return boolean
     */
    private boolean processSimpleEmail(EmailMessage message) {
        // 注意邮件发送可能出现异常，注意catch
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(message.getFrom());
            simpleMailMessage.setTo(ArrayUtil.toArray(message.getTos(), String.class));
            simpleMailMessage.setSubject(message.getSubject());
            simpleMailMessage.setText(message.getContent());

            // 设置抄送人列表
            if (CollUtil.isEmpty(message.getCcs())) {
                simpleMailMessage.setCc(ArrayUtil.toArray(message.getCcs(), String.class));
            }
            mailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            log.error("简单邮件发送异常！");
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 失败处理
     *
     * @param message 消息实体
     */
    @Override
    public void fail(EmailMessage message) {
        log.error("邮件发送失败，当前邮件类型：{}，当前收件人列表：{}", message.getEmailType(), JSONUtil.toJsonStr(message.getTos()));
    }
}
