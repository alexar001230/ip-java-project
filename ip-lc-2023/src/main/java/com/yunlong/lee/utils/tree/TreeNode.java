package com.yunlong.lee.utils.tree;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 17/3/23 11:16 上午
 * @link
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
