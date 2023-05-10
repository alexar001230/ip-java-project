package com.yunlong.lee.dataStructure.binTree.traversal.bfs;

import com.yunlong.lee.utils.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 107. 二叉树的层序遍历 II
 * @date 10/5/23 7:23 下午
 * @link https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 */
public class levelOrderFromBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        return doLevelOrderBottom(root);
    }

    List<List<Integer>> allLevelsFromBottom = new LinkedList<>();

    private List<List<Integer>> doLevelOrderBottom(TreeNode root) {
        if(Objects.isNull(root)){
            return allLevelsFromBottom;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curSize = 1;
        while (!queue.isEmpty()) {
            curSize = queue.size();
            LinkedList<Integer> aLevel = new LinkedList<>();
            for (int i = 0; i < curSize; i++) {
                TreeNode aNode = queue.poll();
                if (Objects.nonNull(aNode.left)) {
                    queue.offer(aNode.left);
                }
                if (Objects.nonNull(aNode.right)) {
                    queue.offer(aNode.right);
                }
                aLevel.add(aNode.val);
            }
            // allLevelsFromBottom.addFirst(aLevel);
            allLevelsFromBottom.add(0,aLevel);
        }
        return allLevelsFromBottom;
    }
}
