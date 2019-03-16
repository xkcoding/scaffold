/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.code.props;

import cn.hutool.core.util.RandomUtil;
import com.xkcoding.scaffold.common.constants.ScaffoldConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 验证码配置
 * </p>
 *
 * @package: com.xkcoding.scaffold.code.props
 * @description: 验证码配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-14 13:38
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@ConfigurationProperties("scaffold.code")
public class ScaffoldCodeProperties {
    /**
     * 是否启用验证码，默认值：false
     */
    private boolean enabled = false;
    /**
     * 验证码默认cookie名，默认值：scaffold-code
     */
    private String cookieName = ScaffoldConstant.DEFAULT_CODE_COOKIE_NAME;
    /**
     * 验证码默认缓存，默认值：SCAFFOLD::CODE
     */
    private String cacheName = ScaffoldConstant.DEFAULT_CODE_CACHE_NAME;
    /**
     * 验证码生成策略，默认值：数字+大写字母
     */
    private String pool = RandomUtil.BASE_CHAR_NUMBER.toUpperCase();
    /**
     * 验证码宽度，默认值：108
     */
    private Integer width = ScaffoldConstant.DEFAULT_CODE_WIDTH;
    /**
     * 验证码高度，默认值：40
     */
    private Integer height = ScaffoldConstant.DEFAULT_CODE_HEIGHT;
    /**
     * 验证码长度，默认值：4
     */
    private Integer length = ScaffoldConstant.DEFAULT_CODE_LENGTH;
}
