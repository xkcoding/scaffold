/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.notification.model.dingtalk;

import com.xkcoding.scaffold.notification.constants.DingTalkType;
import com.xkcoding.scaffold.notification.model.Message;
import lombok.Data;

/**
 * <p>
 * 钉钉消息，参考文档：https://open-doc.dingtalk.com/microapp/serverapi2/qf2nxq
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.model.dingtalk
 * @description:
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 11:19
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public abstract class AbstractDingTalkMessage implements Message {
    /**
     * 钉钉消息类型
     */
    private String msgtype;

    AbstractDingTalkMessage(DingTalkType dingTalkType) {
        this.msgtype = dingTalkType.getType();
    }
}
