/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.code.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.ObjectUtil;
import com.xkcoding.code.constants.CodeTypeEnum;
import com.xkcoding.code.exception.CodeErrorException;
import com.xkcoding.code.props.ScaffoldCodeProperties;
import lombok.AllArgsConstructor;

/**
 * <p>
 * 验证码生成器
 * </p>
 *
 * @package: com.xkcoding.code.utils
 * @description: 验证码生成器
 * @author: yangkai.shen
 * @date: Created in 2019-03-14 15:25
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@AllArgsConstructor
public class CodeUtil {
    private final ScaffoldCodeProperties scaffoldCodeProperties;

    public ShearCaptcha generator(CodeTypeEnum codeType, Integer width, Integer height, Integer length) {
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(width, height, length, 2);

        if (ObjectUtil.equal(codeType, CodeTypeEnum.RANDOM)) {
            shearCaptcha.setGenerator(new RandomGenerator(scaffoldCodeProperties.getPool(), length));
        } else if (ObjectUtil.equal(codeType, CodeTypeEnum.MATH)) {
            shearCaptcha.setGenerator(new MathGenerator(1));
        } else {
            throw new CodeErrorException("不支持的验证码类型");
        }

        return shearCaptcha;
    }
}
