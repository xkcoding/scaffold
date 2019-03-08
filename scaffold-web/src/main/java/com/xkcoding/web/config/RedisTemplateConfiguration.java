/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.web.config;

import com.xkcoding.web.redis.RedisKeySerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * <p>
 * RedisTemplate 配置
 * </p>
 *
 * @package: com.xkcoding.web.config
 * @description: RedisTemplate 配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-08 18:15
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@EnableCaching
@Configuration
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisTemplateConfiguration {

    /**
     * value 值 序列化
     *
     * @return RedisSerializer
     */
    @Bean
    @ConditionalOnMissingBean(RedisSerializer.class)
    public RedisSerializer<Object> redisSerializer() {
        return new JdkSerializationRedisSerializer();
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory, RedisSerializer<Object> redisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        RedisKeySerializer redisKeySerializer = new RedisKeySerializer();
        // key 序列化
        redisTemplate.setKeySerializer(redisKeySerializer);
        redisTemplate.setHashKeySerializer(redisKeySerializer);
        // value 序列化
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1));
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

}
