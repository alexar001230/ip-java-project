package com.yunlonglee.binTree;

/**
 * @author lijie
 * @version 1.0
 * @description 226. 翻转二叉树
 * @date 20/2/22 12:49 上午
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if(null == root){
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.left;
        root.left  = root.right;
        root.right = tmp;
        return root;
    }
}
