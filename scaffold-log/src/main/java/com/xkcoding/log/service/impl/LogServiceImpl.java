/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.log.service.impl;

import com.xkcoding.common.api.R;
import com.xkcoding.log.model.LogApi;
import com.xkcoding.log.model.LogCustom;
import com.xkcoding.log.model.LogError;
import com.xkcoding.log.service.LogService;

/**
 * <p>
 * 默认日志接口实现
 * </p>
 *
 * @package: com.xkcoding.log.service.impl
 * @description: 默认日志接口实现
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 13:55
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class LogServiceImpl implements LogService {
    /**
     * 保存操作日志
     *
     * @param logApi 操作日志实体
     * @return 是否保存成功
     */
    @Override
    public R<Boolean> saveApiLog(LogApi logApi) {
        return R.success();
    }

    /**
     * 保存错误日志
     *
     * @param logError 错误日志实体
     * @return 是否保存成功
     */
    @Override
    public R<Boolean> saveErrorLog(LogError logError) {
        return R.success();
    }

    /**
     * 保存自定义日志
     *
     * @param logCustom 自定义日志实体
     * @return 是否保存成功
     */
    @Override
    public R<Boolean> saveCustomLog(LogCustom logCustom) {
        return R.success();
    }
}
