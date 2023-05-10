package com.yunlong.lee.dataStructure.binTree;

import com.yunlong.lee.utils.tree.TreeNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 404. 左叶子之和
 * @date 10/5/23 6:51 下午
 * @link https://leetcode.cn/problems/sum-of-left-leaves/
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return doSumOfLeftLeaves(root);
    }

    private int doSumOfLeftLeaves(TreeNode root) {
        if(Objects.isNull(root)){
            return 0;
        }
        if(Objects.isNull(root.left) && Objects.isNull(root.right)){
            return 0;
        }
        traversalRecursion(root, true);
        return sumOfLeftLeaves;
    }

    private int sumOfLeftLeaves = 0;

    private void traversalRecursion(TreeNode root, boolean isLeft) {
        if (Objects.isNull(root)) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (isLeft && Objects.isNull(left) && Objects.isNull(right)) {
            sumOfLeftLeaves = sumOfLeftLeaves + root.val;
        }
        traversalRecursion(left, true);
        traversalRecursion(right, false);
    }
}
