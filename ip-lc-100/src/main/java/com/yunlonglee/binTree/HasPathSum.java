package com.yunlonglee.binTree;

/**
 * @author lijie
 * @version 1.0
 * @description 路径总和
 * @date 6/2/22 6:04 下午
 */
public class HasPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        boolean result = recursiveHasPathSum(root, sum);
        return result;
    }

    private boolean recursiveHasPathSum(TreeNode root, int targetSum) {
        if (null != root) {
            if (root.val == targetSum) {
                return true;
            }
            boolean leftHasPathSum = recursiveHasPathSum(root.left,
                    targetSum - root.val);
            boolean rightHasPathSum = recursiveHasPathSum(root.right,
                    targetSum - root.val);
            return leftHasPathSum || rightHasPathSum;
        }
        return false;
    }


    public boolean hasPathSum2(TreeNode root,int targetSum){
        if(null==root){
            return false;
        }

        if(null == root.left && null == root.right){
            return targetSum == root.val;
        }

        boolean leftHas = false;
        boolean rightHas = false;



        if(null!=root.left){
            leftHas = hasPathSum(root.left,targetSum - root.val);
        }
        if(null!=root.right){
            rightHas = hasPathSum(root.right,targetSum-root.val);
        }

        return leftHas || rightHas;
    }
}
