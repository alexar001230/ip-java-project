package com.yunlonglee.binTree;

/**
 * @author lijie
 * @version 1.0
 * @description 110. 平衡二叉树
 * @date 14/2/22 1:48 上午
 */
public class IsBalanced {
    //双递归
    public boolean isBalanced(TreeNode root) {
        if(null == root){
            return true;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.abs(leftDepth-rightDepth) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    private int maxDepth(TreeNode root){
        if(null == root){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth,rightDepth)+1;
    }
}
