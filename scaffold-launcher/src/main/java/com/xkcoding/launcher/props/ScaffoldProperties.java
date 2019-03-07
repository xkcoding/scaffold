/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.launcher.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 配置文件
 * </p>
 *
 * @package: com.xkcoding.launcher.props
 * @description: 配置文件
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 13:25
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@ConfigurationProperties(prefix = "scaffold")
public class ScaffoldProperties {

	/**
	 * 开发环境
	 */
	@Getter
	@Setter
	private String env;

	/**
	 * 服务名
	 */
	@Getter
	@Setter
	private String name;

	/**
	 * 判断是否为 本地开发环境
	 */
	@Getter
	@Setter
	private Boolean isLocal = Boolean.FALSE;

	/**
	 * 装载自定义配置scaffold.prop.xxx
	 */
	@Getter
	private final Map<String, String> prop = new HashMap<>();

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return value
	 */
	@Nullable
	public String get(String key) {
		return get(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return value
	 */
	@Nullable
	public String get(String key, @Nullable String defaultValue) {
		String value = prop.get(key);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return int value
	 */
	@Nullable
	public Integer getInt(String key) {
		return getInt(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return int value
	 */
	@Nullable
	public Integer getInt(String key, @Nullable Integer defaultValue) {
		String value = prop.get(key);
		if (value != null) {
			return Integer.valueOf(value.trim());
		}
		return defaultValue;
	}

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return long value
	 */
	@Nullable
	public Long getLong(String key) {
		return getLong(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return long value
	 */
	@Nullable
	public Long getLong(String key, @Nullable Long defaultValue) {
		String value = prop.get(key);
		if (value != null) {
			return Long.valueOf(value.trim());
		}
		return defaultValue;
	}

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return Boolean value
	 */
	@Nullable
	public Boolean getBoolean(String key) {
		return getBoolean(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return Boolean value
	 */
	@Nullable
	public Boolean getBoolean(String key, @Nullable Boolean defaultValue) {
		String value = prop.get(key);
		if (value != null) {
			value = value.toLowerCase().trim();
			return Boolean.parseBoolean(value);
		}
		return defaultValue;
	}

	/**
	 * 获取配置
	 *
	 * @param key key
	 * @return double value
	 */
	@Nullable
	public Double getDouble(String key) {
		return getDouble(key, null);
	}

	/**
	 * 获取配置
	 *
	 * @param key          key
	 * @param defaultValue 默认值
	 * @return double value
	 */
	@Nullable
	public Double getDouble(String key, @Nullable Double defaultValue) {
		String value = prop.get(key);
		if (value != null) {
			return Double.parseDouble(value.trim());
		}
		return defaultValue;
	}

	/**
	 * 判断是否存在key
	 *
	 * @param key prop key
	 * @return boolean
	 */
	public boolean containsKey(String key) {
		return prop.containsKey(key);
	}

}
