package com.yunlong.lee.dataStructure.binTree.traversal.max;

import com.yunlong.lee.utils.tree.TreeNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 104. 二叉树的最大深度
 * @date 5/5/23 4:06 下午
 * @link https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        return doMaxDepth(root);
    }

    private int doMaxDepth(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        doMaxDepthRecursion(root);
        return maxDepth;
    }

    private int curDepth = 0;
    private int maxDepth = Integer.MIN_VALUE;

    private void doMaxDepthRecursion(TreeNode root) {
        if (Objects.isNull(root.right) && Objects.isNull(root.left)) {
            maxDepth = Math.max(maxDepth, curDepth + 1);
            return;
        }
        curDepth++;
        if (Objects.nonNull(root.left)) {
            doMaxDepthRecursion(root.left);
        }
        if (Objects.nonNull(root.right)) {
            doMaxDepthRecursion(root.right);
        }
        curDepth--;
    }

    private int doMaxDepthRecursion1(TreeNode root) {
        if (root == null) {
            // maxDepth = Math.max(maxDepth, curDepth + 1);
            return 0;
        }
        int lMax = doMaxDepthRecursion1(root.left);
        maxDepth = Math.max(maxDepth, lMax + 1);
        int rMax = doMaxDepthRecursion1(root.right);
        maxDepth = Math.max(maxDepth, rMax + 1);
        return Math.max(lMax, rMax) + 1;
    }
}
