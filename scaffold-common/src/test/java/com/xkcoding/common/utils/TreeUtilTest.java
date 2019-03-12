/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.common.utils;

import cn.hutool.json.JSONUtil;
import com.xkcoding.common.node.ForestNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 树测试
 */
public class TreeUtilTest {

    /**
     * 测试归并
     */
    @Test
    public void testMerge() {
        List<ForestNode> list = new ArrayList<>();
        list.add(new ForestNode(1, 0, "1"));
        list.add(new ForestNode(2, 0, "2"));
        list.add(new ForestNode(3, 1, "3"));
        list.add(new ForestNode(4, 2, "4"));
        list.add(new ForestNode(5, 3, "5"));
        list.add(new ForestNode(6, 4, "6"));
        list.add(new ForestNode(7, 3, "7"));
        list.add(new ForestNode(8, 5, "8"));
        list.add(new ForestNode(9, 6, "9"));
        list.add(new ForestNode(10, 9, "10"));
        List<ForestNode> tns = TreeUtil.merge(list);
        tns.forEach(node -> System.out.println(JSONUtil.toJsonStr(node)));
        System.out.println(JSONUtil.toJsonStr(tns));
    }
}
