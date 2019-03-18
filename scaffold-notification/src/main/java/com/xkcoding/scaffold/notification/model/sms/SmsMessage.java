/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.model.sms;

import com.google.common.collect.Maps;
import com.xkcoding.scaffold.notification.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


/**
 * <p>
 * 阿里大鱼短信消息，参考文档：https://help.aliyun.com/document_detail/101414.html
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.model.sms
 * @description: 阿里大鱼短信消息，参考文档：https://help.aliyun.com/document_detail/101414.html
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 09:15
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsMessage implements Message {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 短信模板的参数
     */
    private Map<String, String> params = Maps.newHashMap();
    /**
     * 短信类型(验证码或者通知短信)，
     */
    private String type;
    /**
     * 短信签名
     */
    private String signName;
    /**
     * 短信模板
     */
    private String template;
}
