/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.common.constants;

/**
 * <p>
 * 系统常量
 * </p>
 *
 * @package: com.xkcoding.common.constants
 * @description: 系统常量
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 15:26
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ScaffoldConstant {
    /**
     * 默认当前页码
     */
    Integer DEFAULT_CURRENT_PAGE = 1;
    /**
     * 默认每页条数
     */
    Integer DEFAULT_PAGE_SIZE = 10;
    /**
     * 默认为空消息
     */
    String DEFAULT_NULL_MESSAGE = "暂无承载数据";
    /**
     * 默认成功消息
     */
    String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    /**
     * 默认失败消息
     */
    String DEFAULT_FAILURE_MESSAGE = "操作失败";
}
