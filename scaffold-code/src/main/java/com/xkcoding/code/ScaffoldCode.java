/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.code;

import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xkcoding.code.constants.CodeTypeEnum;
import com.xkcoding.code.exception.CodeErrorException;
import com.xkcoding.code.props.ScaffoldCodeProperties;
import com.xkcoding.code.utils.CodeUtil;
import com.xkcoding.common.constants.ScaffoldConstant;
import com.xkcoding.common.utils.WebUtil;
import com.xkcoding.scaffold.launcher.constants.AppConstant;
import com.xkcoding.scaffold.launcher.props.ScaffoldProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FastByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * <p>
 * 图形验证码
 * </p>
 *
 * @package: com.xkcoding.code
 * @description: 图形验证码
 * @author: yangkai.shen
 * @date: Created in 2019-03-14 13:35
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@AllArgsConstructor
@Data
public class ScaffoldCode {
    private ScaffoldProperties scaffoldProperties;
    private ScaffoldCodeProperties scaffoldCodeProperties;
    private String cookieName;
    private Cache codeCache;
    private CodeUtil codeUtil;

    /**
     * 生成4位随机验证码，宽高默认
     *
     * @param response HttpServletResponse
     * @return {ResponseEntity}
     */
    public ResponseEntity<Resource> generateRandomCode(HttpServletResponse response) {
        return generateRandomCode(response, scaffoldCodeProperties.getWidth(), scaffoldCodeProperties.getHeight(), scaffoldCodeProperties
                .getLength());
    }

    /**
     * 生成指定位数的随机验证码，宽高默认
     *
     * @param response HttpServletResponse
     * @param length   验证码长度
     * @return {ResponseEntity}
     */
    public ResponseEntity<Resource> generateRandomCode(HttpServletResponse response, Integer length) {
        return generateRandomCode(response, scaffoldCodeProperties.getWidth(), scaffoldCodeProperties.getHeight(), length);
    }

    /**
     * 生成4位随机验证码，可指定宽、高
     *
     * @param response HttpServletResponse
     * @param width    验证码宽度
     * @param height   验证码高度
     * @return {ResponseEntity}
     */
    public ResponseEntity<Resource> generateRandomCode(HttpServletResponse response, Integer width, Integer height) {
        return generateRandomCode(response, width, height, scaffoldCodeProperties.getLength());
    }

    /**
     * 生成随机验证码，可指定宽、高、位数
     *
     * @param response HttpServletResponse
     * @param width    验证码宽度
     * @param height   验证码高度
     * @param length   验证码长度
     * @return {ResponseEntity}
     */
    public ResponseEntity<Resource> generateRandomCode(HttpServletResponse response, Integer width, Integer height, Integer length) {
        return generate(response, CodeTypeEnum.RANDOM, width, height, length);
    }

    /**
     * 生成算术验证码，宽高默认
     *
     * @param response HttpServletResponse
     * @return {ResponseEntity}
     */
    public ResponseEntity<Resource> generateMathCode(HttpServletResponse response) {
        return generateMathCode(response, scaffoldCodeProperties.getWidth(), scaffoldCodeProperties.getHeight());
    }

    /**
     * 生成算术验证码，可指定宽高
     *
     * @param response HttpServletResponse
     * @param width    验证码宽度
     * @param height   验证码高度
     * @return {ResponseEntity}
     */
    public ResponseEntity<Resource> generateMathCode(HttpServletResponse response, Integer width, Integer height) {
        return generate(response, CodeTypeEnum.MATH, width, height, scaffoldCodeProperties.getLength());
    }

    /**
     * 生成验证码
     *
     * @param response HttpServletResponse
     * @param codeType 验证码类型
     * @param width    验证码宽度
     * @param height   验证码高度
     * @param length   验证码长度
     * @return {ResponseEntity}
     */
    public ResponseEntity<Resource> generate(HttpServletResponse response, CodeTypeEnum codeType, Integer width, Integer height, Integer length) {
        // 生成验证码
        byte[] bytes = generateByteArray(response, codeType, width, height, length);
        Resource resource = new ByteArrayResource(bytes);
        return new ResponseEntity<>(resource, this.getResponseHeaders(), HttpStatus.OK);
    }

    /**
     * 生成验证码byte数组
     *
     * @param response HttpServletResponse
     * @param codeType 验证码类型
     * @param width    验证码宽度
     * @param height   验证码高度
     * @param length   验证码长度
     * @return byte数组
     */
    public byte[] generateByteArray(HttpServletResponse response, CodeTypeEnum codeType, Integer width, Integer height, Integer length) {
        BufferedImage image = generator(response, codeType, width, height, length).getImage();
        try (FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()) {
            ImageIO.write(image, "JPEG", outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new CodeErrorException(e);
        }
    }

    /**
     * 生成验证码Base64形式
     *
     * @param response HttpServletResponse
     * @param codeType 验证码类型
     * @param width    验证码宽度
     * @param height   验证码高度
     * @param length   验证码长度
     * @return {String}
     */
    public String generateBase64(HttpServletResponse response, CodeTypeEnum codeType, Integer width, Integer height, Integer length) {
        return generator(response, codeType, width, height, length).getImageBase64();
    }

    /**
     * 生成验证码图片
     *
     * @param response HttpServletResponse
     * @param codeType 验证码类型
     * @param width    验证码宽度
     * @param height   验证码高度
     * @param length   验证码长度
     * @return {BufferedImage}
     */
    public BufferedImage generateImage(HttpServletResponse response, CodeTypeEnum codeType, Integer width, Integer height, Integer length) {
        return generator(response, codeType, width, height, length).getImage();
    }

    /**
     * 验证码生成器
     *
     * @param response HttpServletResponse
     * @param codeType 验证码类型
     * @param width    验证码宽度
     * @param height   验证码高度
     * @param length   验证码长度
     * @return 生成器
     */
    private ShearCaptcha generator(HttpServletResponse response, CodeTypeEnum codeType, Integer width, Integer height, Integer length) {
        String cookieName = getCookieName();
        // 先检查cookie的uuid是否存在
        String cookieValue = WebUtil.getCookieVal(cookieName);
        boolean hasCookie = true;
        if (StrUtil.isBlank(cookieValue)) {
            hasCookie = false;
            cookieValue = StrUtil.uuid();
        }

        ShearCaptcha generator = codeUtil.generator(codeType, width, height, length);
        // 转成大写重要
        String code = generator.getCode().toUpperCase();

        // 不存在cookie时设置cookie
        if (!hasCookie) {
            WebUtil.setCookie(response, cookieName, cookieValue, ScaffoldConstant.DEFAULT_CODE_COOKIE_MAX_AGE);
        }

        // 开发模式、测试模式打印生成验证码
        if (StrUtil.containsAnyIgnoreCase(scaffoldProperties.getEnv(), AppConstant.DEV_CODE, AppConstant.TEST_CODE)) {
            if (log.isDebugEnabled()) {
                log.debug("{} 验证码 {}", cookieValue, code);
            }
        }

        codeCache.put(cookieValue, code);
        // 生成验证码
        return generator;
    }

    /**
     * 校验随机验证码，仅能验证一次，验证后立即删除
     *
     * @param response      HttpServletResponse
     * @param userInputCode 用户输入的验证码
     * @return 验证通过返回 true, 否则返回 false
     */
    public boolean validateRandom(HttpServletResponse response, String userInputCode) {
        return validate(response, userInputCode, CodeTypeEnum.RANDOM);
    }

    /**
     * 校验算术验证码，仅能验证一次，验证后立即删除
     *
     * @param response      HttpServletResponse
     * @param userInputCode 用户输入的验证码
     * @return 验证通过返回 true, 否则返回 false
     */
    public boolean validateMath(HttpServletResponse response, String userInputCode) {
        return validate(response, userInputCode, CodeTypeEnum.MATH);
    }

    /**
     * 仅能验证一次，验证后立即删除
     *
     * @param response      HttpServletResponse
     * @param userInputCode 用户输入的验证码
     * @param codeType      验证码类型
     * @return 验证通过返回 true, 否则返回 false
     */
    private boolean validate(HttpServletResponse response, String userInputCode, CodeTypeEnum codeType) {
        if (log.isDebugEnabled()) {
            log.debug("用户输入验证码 {}", userInputCode);
        }
        String cookieName = getCookieName();
        String cookieValue = WebUtil.getCookieVal(cookieName);
        if (StrUtil.isBlank(cookieValue)) {
            return false;
        }
        String code = codeCache.get(cookieValue, String.class);
        if (StrUtil.isBlank(code)) {
            return false;
        }
        boolean result;
        if (ObjectUtil.equal(codeType, CodeTypeEnum.RANDOM)) {
            // 转成大写重要
            userInputCode = userInputCode.toUpperCase();
            result = userInputCode.equals(code);
        } else if (ObjectUtil.equal(codeType, CodeTypeEnum.MATH)) {
            result = new MathGenerator(1).verify(code, userInputCode);
        } else {
            result = false;
        }

        if (result) {
            codeCache.evict(cookieValue);
            WebUtil.removeCookie(response, cookieName);
        }
        return result;
    }

    private HttpHeaders getResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setPragma("no-cache");
        headers.setCacheControl("no-cache");
        headers.setExpires(0);
        headers.setContentType(MediaType.IMAGE_JPEG);
        return headers;
    }
}
