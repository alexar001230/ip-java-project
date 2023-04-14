package com.yunlong.lee.dataStructure.binTree.convert;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 剑指 Offer 36. 二叉搜索树与双向链表
 * @date 12/4/23 11:25 上午
 * @link https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 */
public class BinSearchTree2DoublyList {
    public TreeNode treeToDoublyList(TreeNode root) {
        return doTreeToDoublyList(root);
    }

    private LinkedList<TreeNode> inOrders = new LinkedList<>();

    private TreeNode doTreeToDoublyList(TreeNode root) {
        //0.空及特殊点考虑
        if (Objects.isNull(root)) {
            return null;
        }
        //1.中序遍历，存到链表中
        inOrderTraversalRecursion(root);
        //2.从链表中构建双向链表
        return convert2DoublyList(inOrders);
    }

    private void inOrderTraversalRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.nonNull(root.left)) {
            inOrderTraversalRecursion(root.left);
        }
        inOrders.add(root);
        if (Objects.nonNull(root.right)) {
            inOrderTraversalRecursion(root.right);
        }
        return;
    }

    private TreeNode convert2DoublyList(LinkedList<TreeNode> inOrders) {
        if (Objects.isNull(inOrders) || inOrders.size() == 0) {
            return null;
        }

        if (inOrders.size() == 1) {
            TreeNode theOne = inOrders.poll();
            theOne.left = theOne;
            theOne.right = theOne;
            return theOne;
        }

        TreeNode dummyHead = new TreeNode(Integer.MIN_VALUE);
        TreeNode toCur = inOrders.poll();
        toCur.left = dummyHead;
        dummyHead.right = toCur;

        // TreeNode post = pre.left;
        TreeNode fromCur = toCur;
        while (!inOrders.isEmpty()) {
            //1.保存要处理的节点信息
            fromCur = inOrders.peek();
            //2.处理节点，节点入队
            toCur.right = fromCur;
            fromCur.left = toCur;
            //3.新老处理游标后移
            if (inOrders.size() == 1) {
                fromCur.right = dummyHead.right;
                dummyHead.right.left = fromCur;
            } else {
                fromCur = fromCur.right;
                toCur = toCur.right;
                // post = pre.left;
            }
            inOrders.poll();
        }
        return dummyHead.right;
    }

    public static void main(String[] args) {
        int[] inOrders = new int[]{1, 2, 3, 4, 5};
        int[] postOrders = new int[]{1, 3, 2, 5, 4};
        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrders,
                postOrders);
        new BinSearchTree2DoublyList().treeToDoublyList(root);
    }
}
