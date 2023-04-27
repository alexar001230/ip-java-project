package com.yunlong.lee.dataStructure.binTree.traversal;

import com.yunlong.lee.utils.tree.TreeNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 110. 平衡二叉树
 * @date 26/4/23 6:23 下午
 * @link https://leetcode.cn/problems/balanced-binary-tree/
 */
public class IsBalanceBinTree {
    public boolean isBalanced(TreeNode root) {
        return doIsBalanced(root);
    }

    private boolean doIsBalanced(TreeNode root) {
        maxHeightRecursion(root);
        return isBalanced;
    }

    private boolean isBalanced = true;

    private int maxHeightRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        int leftMaxHeight = maxHeightRecursion(root.left);
        int rightMaxHeight = maxHeightRecursion(root.right);
        if (Math.abs(leftMaxHeight - rightMaxHeight) >= 2) {
            isBalanced = false;
        }
        return Math.max(leftMaxHeight, rightMaxHeight) + 1;
    }
}
