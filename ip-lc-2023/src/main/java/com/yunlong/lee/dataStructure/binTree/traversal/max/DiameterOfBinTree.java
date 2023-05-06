package com.yunlong.lee.dataStructure.binTree.traversal.max;

import com.yunlong.lee.utils.tree.TreeNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 543. 二叉树的直径(一棵二叉树的直径长度是任意两个结点路径长度中的最大值)
 * @date 5/5/23 6:06 下午
 * @link https://leetcode.cn/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinTree {
    public int diameterOfBinaryTree(TreeNode root) {
        return doDiameterOfBinaryTree(root);
    }

    private int doDiameterOfBinaryTree(TreeNode root) {
        // maxDiameterOfBinaryTreeRecursion(root);
        // return maxDiameter;
        maxDepth(root);
        return maxDiameter - 1;
    }


    private int maxDepth(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        int lMax = maxDepth(root.left);
        int rMax = maxDepth(root.right);
        maxDiameter = Math.max(maxDiameter, lMax + rMax + 1);
        return Math.max(lMax, rMax) + 1;
    }

    private int maxDiameter = Integer.MIN_VALUE;


    //region todo 怎么调试都不对，nnd
    private void maxDiameterOfBinaryTreeRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        int lMaxDepth = maxDepthOfBinTreeRecursion(root.left, 0, 0);
        int rMaxDepth = maxDepthOfBinTreeRecursion(root.right, 0, 0);
        maxDiameter = Math.max(maxDiameter, lMaxDepth + rMaxDepth + 1);
    }

    private int maxDepthOfBinTreeRecursion(TreeNode root, int maxDepth, int curDepth) {
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            maxDepth = Math.max(maxDepth, curDepth + 1);
            return maxDepth;
        }
        curDepth++;
        maxDepthOfBinTreeRecursion(root.left, maxDepth, curDepth);
        maxDepthOfBinTreeRecursion(root.right, maxDepth, curDepth);
        curDepth--;
        return maxDepth;
    }
    //endregion
}
