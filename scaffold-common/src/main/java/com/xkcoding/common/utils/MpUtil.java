/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.common.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xkcoding.common.constants.ScaffoldConstant;
import com.xkcoding.common.constants.StringConstant;
import com.xkcoding.common.query.PageQuery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Mybatis-Plus 工具类
 * </p>
 *
 * @package: com.xkcoding.common.utils
 * @description: Mybatis-Plus 工具类
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 16:44
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class MpUtil {
    /**
     * 驼峰
     */
    private static final Pattern CAMELCASE_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 下划线
     */
    private static final Pattern UNDERLINE_PATTERN = Pattern.compile("_[a-z]");

    /**
     * 转化成mybatis plus中的Page
     *
     * @param query 分页参数
     * @return Mybatis-Plus分页参数
     */
    public static <T, Q extends PageQuery> IPage<T> getPage(Q query) {
        checkPage(query);
        Page<T> page = new Page<>(query.getCurrent(), query.getSize());
        page.setAsc(ArrayUtil.toArray(StrUtil.splitTrim(query.getAscs(), StringConstant.COMMA), String.class));
        page.setDesc(ArrayUtil.toArray(StrUtil.splitTrim(query.getDescs(), StringConstant.COMMA), String.class));
        return page;
    }

    /**
     * 驼峰命名（形如：UserInfo）转化为下划线命名（user_info）
     *
     * @param camelCase 驼峰格式的字符串
     * @return 下划线形式的字符串
     */
    public static String camelCase2UnderlineCase(String camelCase) {
        Matcher matcher = CAMELCASE_PATTERN.matcher(camelCase);
        StringBuilder builder = new StringBuilder(camelCase);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() + i, matcher.end() + i, StringConstant.UNDERSCORE + matcher.group()
                    .toLowerCase());
        }
        if (StrUtil.equals(builder.charAt(0) + StrUtil.EMPTY, StringConstant.UNDERSCORE)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 下划线命名（user_info）转化为驼峰命名（形如：userInfo）
     *
     * @param underlineCase 下划线形式的字符串
     * @return 驼峰格式的字符串
     */
    public static String underlineCase2CamelCase(String underlineCase) {
        Matcher matcher = UNDERLINE_PATTERN.matcher(underlineCase);
        StringBuilder builder = new StringBuilder(underlineCase);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }

    /**
     * 校验分页参数
     *
     * @param query 分页参数
     */
    private static <Q extends PageQuery> void checkPage(Q query) {
        if (ObjectUtil.isNull(query.getCurrent())) {
            query.setCurrent(ScaffoldConstant.DEFAULT_CURRENT_PAGE);
        }
        if (ObjectUtil.isNull(query.getSize())) {
            query.setSize(ScaffoldConstant.DEFAULT_PAGE_SIZE);
        }
    }
}
