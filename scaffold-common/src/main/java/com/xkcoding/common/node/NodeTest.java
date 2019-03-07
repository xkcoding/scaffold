/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.xkcoding.common.node;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 节点测试
 * </p>
 *
 * @package: com.xkcoding.common.node
 * @description: 节点测试
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 16:02
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class NodeTest {

    public static void main(String[] args) {
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
        List<ForestNode> tns = ForestNodeMerger.merge(list);
        tns.forEach(node -> System.out.println(JSONUtil.toJsonStr(node)));
        System.out.println(JSONUtil.toJsonStr(tns));
    }

}
