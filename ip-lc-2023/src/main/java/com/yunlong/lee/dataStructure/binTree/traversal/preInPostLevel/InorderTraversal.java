package com.yunlong.lee.dataStructure.binTree.traversal.preInPostLevel;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.*;

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
        doInOrderTraversalIterate(root);
        return inorderTraversals;
    }


    //region 迭代遍历
    private void doInOrderTraversalIterate(TreeNode root) {
        Deque<TreeNode> nodeStack = new LinkedList<>();
        if (Objects.nonNull(root)) {
            nodeStack.push(root);
        }
        TreeNode cursorNode = nodeStack.peek();
        while (!nodeStack.isEmpty()) {
            cursorNode = nodeStack.peek();
            if (Objects.nonNull(cursorNode)) {
                nodeStack.pop();
                if (Objects.nonNull(cursorNode.right)) {
                    nodeStack.push(cursorNode.right);
                }
                nodeStack.push(cursorNode);
                nodeStack.push(null);
                if (Objects.nonNull(cursorNode.left)) {
                    nodeStack.push(cursorNode.left);
                }
            } else {
                nodeStack.pop();
                TreeNode toProcess = nodeStack.pop();
                inorderTraversals.add(toProcess.val);
            }
        }
    }
    //endregion

    //region 递归遍历
    private void doInorderTravelRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        } else {
            doInorderTravelRecursion(root.left);
            inorderTraversals.add(root.val);
            // System.out.print("-->" + root.val);
            doInorderTravelRecursion(root.right);
        }
    }
    //endregion

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        System.out.println(new InorderTraversal().inorderTraversal(root));
    }
}
