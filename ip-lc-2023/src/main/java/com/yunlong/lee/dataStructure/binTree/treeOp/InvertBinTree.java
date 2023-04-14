package com.yunlong.lee.dataStructure.binTree.treeOp;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 226. 翻转二叉树
 * @date 12/4/23 12:21 下午
 * @link https://leetcode.cn/problems/invert-binary-tree/
 */
public class InvertBinTree {
    public TreeNode invertTree(TreeNode root) {
        return doInvertTree(root);
    }

    private TreeNode doInvertTree(TreeNode root) {
        if(Objects.isNull(root)){
            return root;
        }
        if(Objects.isNull(root.right) && Objects.isNull(root.left)){
            return root;
        }
        invertBinTreeRecursion(root);
        return root;
    }

    private void invertBinTreeRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        if (Objects.nonNull(root.left)) {
            invertTree(root.left);
        }
        if (Objects.nonNull(root.right)) {
            invertTree(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        TreeNodeUtils.levelOrderTraversalPrint(root);
        new InvertBinTree().invertBinTreeRecursion(root);
        TreeNodeUtils.levelOrderTraversalPrint(root);
    }
}
