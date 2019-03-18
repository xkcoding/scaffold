/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.model.email;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xkcoding.scaffold.notification.constants.EmailType;
import com.xkcoding.scaffold.notification.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 邮件消息
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.model.email
 * @description: 邮件消息
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 15:42
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage implements Message {
    /**
     * 邮件类型
     */
    private EmailType emailType;
    /**
     * 收件人
     */
    private List<String> tos = Lists.newArrayList();
    /**
     * 发件人
     */
    private String from;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 模板名
     */
    private String template;
    /**
     * 模板变量
     */
    private Map<String, Object> params = Maps.newHashMap();
    /**
     * 附件列表
     */
    private List<Attachment> attachments = Lists.newArrayList();
    /**
     * 静态资源列表
     */
    private List<StaticResource> staticResources = Lists.newArrayList();
    /**
     * 抄送
     */
    private List<String> ccs = Lists.newArrayList();

    /**
     * 附件
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attachment {
        /**
         * 附件名
         */
        private String attachmentName;
        /**
         * 附件
         */
        private File attachment;
    }

    /**
     * 静态资源
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StaticResource {
        /**
         * 静态资源id
         */
        private String resourceId;
        /**
         * 静态资源
         */
        private File resource;
    }
}
