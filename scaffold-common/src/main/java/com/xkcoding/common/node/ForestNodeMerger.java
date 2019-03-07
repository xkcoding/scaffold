/*
 * Copyright 2019 Yangkai.Shen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.xkcoding.common.node;

import java.util.List;

/**
 * <p>
 * 森林节点归并类
 * </p>
 *
 * @package: com.xkcoding.common.node
 * @description: 森林节点归并类
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 15:55
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class ForestNodeMerger {

	/**
	 * 将节点数组归并为一个森林（多棵树）（填充节点的children域）
	 * 时间复杂度为O(n^2)
	 *
	 * @param items 节点域
	 * @param <T>   T 泛型标记
	 * @return 多棵树的根节点集合
	 */
	public static <T extends INode> List<T> merge(List<T> items) {
		ForestNodeManager<T> forestNodeManager = new ForestNodeManager<>(items);
		items.forEach(forestNode -> {
			if (forestNode.getParentId() != 0) {
				INode node = forestNodeManager.getTreeNodeAT(forestNode.getParentId());
				if (node != null) {
					node.getChildren().add(forestNode);
				} else {
					forestNodeManager.addParentId(forestNode.getId());
				}
			}
		});
		return forestNodeManager.getRoot();
	}

}
