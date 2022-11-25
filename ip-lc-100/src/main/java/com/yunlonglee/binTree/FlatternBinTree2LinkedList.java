package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.buildTree;

/**
 * @author lijie
 * @version 1.0
 * @description 114. 二叉树展开为链表
 * @date 24/2/22 4:41 上午
 */
public class FlatternBinTree2LinkedList {
    public static void flatten(TreeNode root) {
        if (null == root) {
            return;
        }
        List<TreeNode> preOrders = new ArrayList<>();
        preOrderTravel(root, preOrders);
        TreeNode cursor = root;
        root.left = null;
        for (int i = 1; i < preOrders.size(); i++) {
            TreeNode newNode = preOrders.get(i);
            newNode.left = null;
            cursor.right = newNode;
            cursor = cursor.right;
        }
    }

    public static void preOrderTravel(TreeNode root, List<TreeNode> preOrders) {
        if (null == root) {
            return;
        }
        preOrders.add(root);
        preOrderTravel(root.left, preOrders);
        preOrderTravel(root.right, preOrders);
    }

    public static void main(String[] args) {
        int[] inOrder = {3, 2, 4, 1, 5, 6};
        int[] postOrder = {3, 4, 2, 6, 5, 1};
        TreeNode root = buildTree(inOrder, postOrder);

        flatten(root);
    }
}
