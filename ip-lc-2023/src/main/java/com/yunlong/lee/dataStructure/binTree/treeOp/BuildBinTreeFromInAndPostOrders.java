package com.yunlong.lee.dataStructure.binTree.treeOp;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 106. 从中序与后序遍历序列构造二叉树
 * @date 10/5/23 3:29 下午
 * @link https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class BuildBinTreeFromInAndPostOrders {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return doBuildTree(inorder, postorder);
    }

    private TreeNode doBuildTree(int[] inorder, int[] postorder) {
        if (Objects.isNull(inorder) || inorder.length == 0) {
            return null;
        }
        return buildTreeRecursion(inorder, 0, inorder.length, postorder, 0,
                postorder.length);
    }

    private TreeNode buildTreeRecursion(int[] inorder, int inStart, int inEnd,
                                        int[] postorder, int postStart, int postEnd) {
        if (inEnd - inStart < 1) {
            return null;
        } else if (inEnd - inStart == 1) {
            return new TreeNode(inorder[inStart]);
        }
        int aRootVal = postorder[postEnd - 1];
        TreeNode root = new TreeNode(aRootVal);
        int rootIdxOfInOrder = valIdxOfArr(inorder, aRootVal);
        int leftValCnt = rootIdxOfInOrder - inStart; // 不+1,因为rootIdx对应的值此轮用过了

        root.left = buildTreeRecursion(inorder, inStart, rootIdxOfInOrder,
                postorder, postStart, postStart + leftValCnt);
        root.right = buildTreeRecursion(inorder, rootIdxOfInOrder + 1, inEnd,
                postorder, postStart + leftValCnt, postEnd - 1);
        return root;
    }

    private int valIdxOfArr(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] inOrderArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] postOrderArr = new int[]{0, 3, 2, 1, 5, 8, 7, 6, 4};
        TreeNode root =
                new BuildBinTreeFromInAndPostOrders().buildTree(inOrderArr,
                        postOrderArr);
        TreeNodeUtils.inOrderPrint(root);
        TreeNodeUtils.postOrderPrint(root);
    }
}
