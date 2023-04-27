package com.yunlong.lee.dataStructure.binTree.traversal.preInPostLevel;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 27/4/23 1:53 下午
 * @link
 */
public class PreorderTraversal {
    List<Integer> preorderTraversals = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return preorderTraversals;
        }
        doPreorderTraversalRecursion(root);
        return preorderTraversals;
    }


    //region 迭代遍历
    private void doPreorderTraversalIterate(TreeNode root) {
        Deque<TreeNode> nodeStack = new LinkedList<>();
        nodeStack.push(root);
        TreeNode cursorNode = root;
        while (!nodeStack.isEmpty()) {
            cursorNode = nodeStack.peek();
            if (Objects.nonNull(cursorNode)) {
                nodeStack.pop();
                if (Objects.nonNull(cursorNode.right)) {
                    nodeStack.push(cursorNode.right);
                }
                if (Objects.nonNull(cursorNode.left)) {
                    nodeStack.push(cursorNode.left);
                }
                nodeStack.push(cursorNode);
                nodeStack.push(null);
            } else {
                nodeStack.pop();
                TreeNode toProcess = nodeStack.pop();
                preorderTraversals.add(toProcess.val);
            }
        }
    }
    //endregion

    //region 递归
    private void doPreorderTraversalRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        } else {
            preorderTraversals.add(root.val);
            if (Objects.nonNull(root.left)) {
                doPreorderTraversalRecursion(root.left);
            }
            if (Objects.nonNull(root.right)) {
                doPreorderTraversalRecursion(root.right);
            }
            return;
        }
    }
    //endregion

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        System.out.println(new PreorderTraversal().preorderTraversal(root));
    }
}
