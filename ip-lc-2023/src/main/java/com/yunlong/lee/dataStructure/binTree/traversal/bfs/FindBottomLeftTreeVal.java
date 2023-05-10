package com.yunlong.lee.dataStructure.binTree.traversal.bfs;

import com.yunlong.lee.utils.tree.TreeNode;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 513. 找树左下角的值
 * @date 10/5/23 7:05 下午
 * @link https://leetcode.cn/problems/find-bottom-left-tree-value/
 */
public class FindBottomLeftTreeVal {
    public int findBottomLeftValue(TreeNode root) {
        return doFindBottomLeftValue(root);
    }

    LinkedList<LinkedList<Integer>> allLevels = new LinkedList<>();

    private int doFindBottomLeftValue(TreeNode root) {
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
            allLevels.add(aLevel);
        }
        return allLevels.getLast().getFirst();
    }
}
