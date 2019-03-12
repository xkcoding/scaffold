/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.common.api;

import cn.hutool.core.util.ObjectUtil;
import com.xkcoding.common.constants.ScaffoldConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Optional;

/**
 * <p>
 * 统一API响应结果封装
 * </p>
 *
 * @package: com.xkcoding.common.api
 * @description: 统一API响应结果封装
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 15:23
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
@Setter
@ToString
@ApiModel(description = "返回信息")
@NoArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = -7618165659758377287L;

    @ApiModelProperty(value = "状态码", required = true)
    private int code;
    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;
    @ApiModelProperty(value = "返回消息", required = true)
    private String msg;
    @ApiModelProperty(value = "承载数据")
    private T data;

    private R(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private R(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private R(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private R(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isSuccess(@Nullable R<?> result) {
        return Optional.ofNullable(result)
                .map(x -> ObjectUtil.equal(ResultCode.SUCCESS.code, x.code))
                .orElse(Boolean.FALSE);
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isNotSuccess(@Nullable R<?> result) {
        return !R.isSuccess(result);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(T data) {
        return data(data, ScaffoldConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(T data, String msg) {
        return data(HttpServletResponse.SC_OK, data, msg);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(int code, T data, String msg) {
        return new R<>(code, data, data == null ? ScaffoldConstant.DEFAULT_NULL_MESSAGE : msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> success(String msg) {
        return new R<>(ResultCode.SUCCESS, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(IResultCode resultCode) {
        return new R<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(IResultCode resultCode, String msg) {
        return new R<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param data       数据
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(IResultCode resultCode, T data) {
        return new R<>(resultCode, data);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param data       数据
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(IResultCode resultCode, String msg, T data) {
        return new R<>(resultCode, data, msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(String msg) {
        return new R<>(ResultCode.FAILURE, msg);
    }


    /**
     * 返回R
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, null, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(IResultCode resultCode) {
        return new R<>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(IResultCode resultCode, String msg) {
        return new R<>(resultCode, msg);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param data       数据
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(IResultCode resultCode, T data) {
        return new R<>(resultCode, data);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param data       数据
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(IResultCode resultCode, String msg, T data) {
        return new R<>(resultCode, data, msg);
    }

    /**
     * 返回R
     *
     * @param flag 成功状态
     * @return R
     */
    public static <T> R<T> status(boolean flag) {
        return flag ? success(ScaffoldConstant.DEFAULT_SUCCESS_MESSAGE) : fail(ScaffoldConstant.DEFAULT_FAILURE_MESSAGE);
    }

    /**
     * 返回R
     *
     * @return R
     */
    public static <T> R<T> success() {
        return status(true);
    }

    /**
     * 返回R
     *
     * @return R
     */
    public static <T> R<T> fail() {
        return status(false);
    }

}
