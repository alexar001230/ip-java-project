package com.yunlong.lee.dataStructure.binTree.shape;

import com.yunlong.lee.utils.tree.TreeNode;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 101. 对称二叉树
 * @date 5/5/23 4:23 下午
 * @link https://leetcode.cn/problems/symmetric-tree/
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return doIsSymmetric(root);
    }


    private boolean isSymmetric = true;

    private boolean doIsSymmetric(TreeNode root) {
        TreeNode rootCopy = root;
        doIsSymmetricRecursion(root, rootCopy);
        return isSymmetric;
    }

    //region 需要使用镜像树的技巧
    private void doIsSymmetricRecursion(TreeNode root, TreeNode rootCopy) {
        if (Objects.nonNull(root) && Objects.nonNull(rootCopy)) {
            if (root.val != rootCopy.val) {
                isSymmetric = false;
            }
            if (isSymmetric) {
                doIsSymmetricRecursion(root.left, rootCopy.right);

            }
            if (isSymmetric) {
                doIsSymmetricRecursion(root.right, rootCopy.left);
            }
        } else {
            if (Objects.isNull(root) && Objects.isNull(rootCopy)) {
                isSymmetric = true;
            } else {
                isSymmetric = false;
            }
        }
    }
    //endregion
}
