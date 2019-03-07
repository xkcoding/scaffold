/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.common.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分页查询条件
 * </p>
 *
 * @package: com.xkcoding.common.query
 * @description: 分页查询条件
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 16:22
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "查询条件")
public class PageQuery {
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer current;

    /**
     * 每页的数量
     */
    @ApiModelProperty(value = "每页的数量")
    private Integer size;

    /**
     * 排序的字段名
     */
    @ApiModelProperty(hidden = true)
    private String ascs;

    /**
     * 排序方式
     */
    @ApiModelProperty(hidden = true)
    private String descs;
}
