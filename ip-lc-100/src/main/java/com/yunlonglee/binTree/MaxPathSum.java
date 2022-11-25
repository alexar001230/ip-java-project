package com.yunlonglee.binTree;

/**
 * @author lijie
 * @version 1.0
 * @description 124. 二叉树中的最大路径和
 * @date 13/2/22 8:28 下午
 */
public class MaxPathSum {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        //递归，节点最大贡献值，1.空节点为0，2.叶子节点为叶子节点值 3.非叶子节点  非叶节点值+max(left,right)
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftMaxGain = Math.max(maxGain(root.left), 0);
        int rightMaxGain = Math.max(maxGain(root.right), 0);
        int newMax = root.val + leftMaxGain + rightMaxGain;
        maxSum = Math.max(maxSum, newMax);
        int nodeMaxSum = root.val + Math.max(leftMaxGain, rightMaxGain);
        return nodeMaxSum;
    }

}
