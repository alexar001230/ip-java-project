package com.yunlong.lee.dataStructure.binTree.traversal.pathSum;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 112. 路径总和
 * @date 11/4/23 7:32 下午
 * @link https://leetcode.cn/problems/path-sum/
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return doHasPathSum(root,targetSum);
    }

    boolean hasSum2Target = false;

    private boolean doHasPathSum(TreeNode root, int targetSum) {
        checkPathSum2Target(root, targetSum);
        return hasSum2Target;
    }

    private void checkPathSum2Target(TreeNode root, int targetSum) {
        if (hasSum2Target) {
            return;
        }
        //1.出口
        if (Objects.isNull(root)) {
            return;
        }
        //2.处理数据
        targetSum = targetSum - root.val;
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            if (targetSum == 0) {
                hasSum2Target = true;
                return;
            }
        }
        //3.递归迭代左右孩子
        if (Objects.nonNull(root.left)) {
            checkPathSum2Target(root.left, targetSum);
        }

        if (Objects.nonNull(root.right)) {
            checkPathSum2Target(root.right, targetSum);
        }
        return;
    }

    public static void main(String[] args) {
        int[] inOrderArr = new int[]{7, 11, 2, 4, 5, 13, 8, 5, 4, 1};
        int[] postOrderArr = new int[]{7, 2, 11, 4, 13, 5, 1, 4, 8, 5};
        // TreeNode root = TreeNodeUtils.genBinTree();
        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrderArr, postOrderArr);
        System.out.println(new PathSum().hasPathSum(root, 23));
    }
}
