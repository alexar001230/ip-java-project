package com.yunlonglee.binTree;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.buildTree;

/**
 * @author lijie
 * @version 1.0
 * @description 543. 二叉树的直径
 * @date 16/2/22 1:16 上午
 */
public class DiameterOfBinaryTree {
    static int result;

    public static int diameterOfBinaryTree(TreeNode root) {
        result = 1;
        depth(root);
        return result - 1;
    }

    private static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = depth(root.left);
        int rDepth = depth(root.right);
        result = Math.max(result, lDepth + rDepth + 1);
        return Math.max(lDepth, rDepth) + 1;
    }

    public static void main(String[] args) {
        int[] inOrder = new int[]{4, 2, 5, 1, 3};
        int[] postOrder = new int[]{4, 5, 2, 3, 1};
        TreeNode root = buildTree(inOrder, postOrder);
        diameterOfBinaryTree(root);
    }
}
