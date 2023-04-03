package com.yunlong.lee.dataStructure.binTree.traversal;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 二叉树中序遍历
 * @date 17/3/23 11:15 上午
 * @link https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class InorderTraversal {
    List<Integer> inorderTraversals = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        recursiveInorderTravel(root);
        return inorderTraversals;
    }

    private void recursiveInorderTravel(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        } else {
            recursiveInorderTravel(root.left);
            inorderTraversals.add(root.val);
            // System.out.print("-->" + root.val);
            recursiveInorderTravel(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        new InorderTraversal().recursiveInorderTravel(root);
    }
}
