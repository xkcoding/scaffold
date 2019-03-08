/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.web.redis;

import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 将redis key序列化为字符串
 *
 * <p>
 * spring cache中的简单基本类型直接使用 StringRedisSerializer 会有问题
 * </p>
 *
 * @package: com.xkcoding.web.redis
 * @description: 将redis key序列化为字符串
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 18:13
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class RedisKeySerializer implements RedisSerializer<Object> {
    private final Charset charset;
    private final ConversionService converter;

    public RedisKeySerializer() {
        this(StandardCharsets.UTF_8);
    }

    public RedisKeySerializer(Charset charset) {
        Objects.requireNonNull(charset, "Charset must not be null");
        this.charset = charset;
        this.converter = DefaultConversionService.getSharedInstance();
    }

    @Override
    public Object deserialize(byte[] bytes) {
        // redis keys 会用到反序列化
        if (bytes == null) {
            return null;
        }
        return new String(bytes, charset);
    }

    @Override
    @Nullable
    public byte[] serialize(Object object) {
        Objects.requireNonNull(object, "redis key is null");
        String key;
        if (object instanceof SimpleKey) {
            key = "";
        } else if (object instanceof String) {
            key = (String) object;
        } else {
            key = converter.convert(object, String.class);
        }
        return key.getBytes(this.charset);
    }

}
