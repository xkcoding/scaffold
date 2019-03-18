

/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xkcoding.scaffold.common.exception.ParamException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * 数据校验
 * </p>
 *
 * @package: com.xkcoding.scaffold.common.utils
 * @description: 数据校验
 * @author: yangkai.shen
 * @date: Created in 2019-03-18 10:57
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Assert {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws ParamException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws ParamException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new ParamException(msg.toString());
        }
    }

    public static void isBlank(String str, String message) {
        if (StrUtil.isBlank(str)) {
            throw new ParamException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (ObjectUtil.isNull(object)) {
            throw new ParamException(message);
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (CollUtil.isEmpty(collection)) {
            throw new ParamException(message);
        }
    }

    public static void isNotEmpty(Collection<?> collection, String message) {
        if (CollUtil.isNotEmpty(collection)) {
            throw new ParamException(message);
        }
    }
}
