package com.yunlong.lee.dataStructure.binTree.treeOp;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 1038. 从二叉搜索树到更大和树
 * @date 5/5/23 5:06 下午
 * @link https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/
 */
public class Bst2Gst {
    public TreeNode bstToGst(TreeNode root) {
        return doBstToGst(root);
    }

    private LinkedList<Integer> inOrders = new LinkedList<>();
    private LinkedList<Integer> inOrdersPostSumStack = new LinkedList<>();

    private TreeNode doBstToGst(TreeNode root) {
        //1.中序遍历，求后缀和,放入栈
        inOrdersTraversal(root);
        //2.求后缀和
        postSum(inOrders);
        //3.中序遍历, 抛栈重新赋值
        inOrdersTraversalAndAssignVal(root);
        return root;
    }

    private void inOrdersTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        inOrdersTraversal(root.left);
        inOrders.add(root.val);
        inOrdersTraversal(root.right);
    }

    private void postSum(LinkedList<Integer> inOrders) {
        int sum = 0;
        for (int i = inOrders.size() - 1; i >= 0; i--) {
            sum = sum + inOrders.get(i);
            inOrdersPostSumStack.push(sum);
        }
    }

    private void inOrdersTraversalAndAssignVal(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        inOrdersTraversalAndAssignVal(root.left);
        root.val = inOrdersPostSumStack.pop();
        inOrdersTraversalAndAssignVal(root.right);
    }

    public static void main(String[] args) {
        int[] inOrderArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] postOrderArr = new int[]{0, 3, 2, 1, 5, 8, 7, 6, 4};
        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrderArr,
                postOrderArr);
        System.out.println(TreeNodeUtils.inOrder(root));
        new Bst2Gst().bstToGst(root);
        System.out.println(TreeNodeUtils.inOrder(root));
    }
}
