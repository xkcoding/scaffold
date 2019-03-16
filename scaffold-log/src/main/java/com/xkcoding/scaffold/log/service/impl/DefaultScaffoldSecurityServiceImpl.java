/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.scaffold.log.service.impl;

import com.xkcoding.scaffold.common.constants.ScaffoldConstant;
import com.xkcoding.scaffold.log.service.SecurityService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 默认认证接口
 * </p>
 *
 * @package: com.xkcoding.scaffold.log.service.impl
 * @description: 默认认证接口
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 15:01
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class DefaultScaffoldSecurityServiceImpl implements SecurityService {

    /**
     * 获取当前用户姓名
     *
     * @param request request
     * @return 当前用户名
     */
    @Override
    public String getCurrentUserName(HttpServletRequest request) {
        return ScaffoldConstant.ANONYMOUS_USER_NAME;
    }
}
