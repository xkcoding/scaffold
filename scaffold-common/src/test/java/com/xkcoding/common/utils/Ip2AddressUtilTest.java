/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * IP地址转换工具类测试
 */
@Slf4j
public class Ip2AddressUtilTest {
    private String ip1="125.120.35.2";
    private String ip2="42.200.180.111";

    /**
     * 测试ip2region
     */
    @Test
    public void testGetAddressInLocal(){
        log.info("测试ip2region");
        String address1 = Ip2AddressUtil.getAddressInLocal(ip1);
        log.info("【address1】= {}", address1);
        String address2 = Ip2AddressUtil.getAddressInLocal(ip2);
        log.info("【address2】= {}", address2);
    }

    /**
     * 测试淘宝ip，不是很稳定
     */
    @Test
    public void testGetAddressOnNetByTaoBao(){
        log.info("测试淘宝ip");
        String address1 = Ip2AddressUtil.getAddressOnNetByTaoBao(ip1);
        log.info("【address1】= {}", address1);
        String address2 = Ip2AddressUtil.getAddressOnNetByTaoBao(ip2);
        log.info("【address2】= {}", address2);
    }

}
