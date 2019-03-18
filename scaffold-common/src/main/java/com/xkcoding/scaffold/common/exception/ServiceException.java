/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.scaffold.common.exception;

import com.xkcoding.scaffold.common.api.IResultCode;
import com.xkcoding.scaffold.common.api.ResultCode;
import lombok.Getter;

/**
 * <p>
 * 通用业务异常
 * </p>
 *
 * @package: com.xkcoding.scaffold.common.exception
 * @description: 通用业务异常
 * @author: yangkai.shen
 * @date: Created in 2019-03-13 13:49
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 2359767895161832954L;

    @Getter
    private final IResultCode resultCode;
    @Getter
    private final Object data;

    public ServiceException(String message) {
        super(message);
        this.resultCode = ResultCode.INTERNAL_SERVER_ERROR;
        data = null;
    }

    public ServiceException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        data = null;
    }

    public ServiceException(IResultCode resultCode, Object data) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
        this.data = data;
    }

    public ServiceException(IResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
        data = null;
    }

    /**
     * 提高性能
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

}
