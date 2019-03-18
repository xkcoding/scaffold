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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 钉钉整体跳转ActionCard类型，参考文档：https://open-doc.dingtalk.com/microapp/serverapi2/qf2nxq#a-nametefrxba%E6%95%B4%E4%BD%93%E8%B7%B3%E8%BD%ACactioncard%E7%B1%BB%E5%9E%8B
 * </p>
 *
 * @package: com.xkcoding.scaffold.notification.model.dingtalk
 * @description: 钉钉整体跳转ActionCard类型，参考文档：https://open-doc.dingtalk.com/microapp/serverapi2/qf2nxq#a-nametefrxba%E6%95%B4%E4%BD%93%E8%B7%B3%E8%BD%ACactioncard%E7%B1%BB%E5%9E%8B
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 13:51
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WholeActionCardDingTalkMessage extends AbstractDingTalkMessage {
    /**
     * 消息内容
     */
    private WholeActionCard actionCard;

    /**
     * 消息类型，此时固定为：actionCard
     */
    public WholeActionCardDingTalkMessage() {
        super(DingTalkType.WHOLE_ACTIONCARD);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WholeActionCard {
        /**
         * 首屏会话透出的展示内容
         */
        private String title;
        /**
         * markdown格式的消息
         */
        private String text;
        /**
         * 单个按钮的方案。(设置此项和singleURL后btns无效)
         */
        private String singleTitle;
        /**
         * 点击singleTitle按钮触发的URL
         */
        private String singleURL;
        /**
         * 0-按钮竖直排列，1-按钮横向排列
         */
        private String btnOrientation;
        /**
         * 0-正常发消息者头像，1-隐藏发消息者头像
         */
        private String hideAvatar;
    }

}
