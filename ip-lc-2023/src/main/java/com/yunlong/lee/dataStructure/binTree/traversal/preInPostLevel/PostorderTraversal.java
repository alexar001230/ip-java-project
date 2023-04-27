package com.yunlong.lee.dataStructure.binTree.traversal.preInPostLevel;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 27/4/23 1:54 下午
 * @link
 */
public class PostorderTraversal {
    List<Integer> postorderTraversals = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return postorderTraversals;
        }
        doPostorderTraversalRecursion(root);
        return postorderTraversals;
    }


    //region 迭代遍历
    private void doPostorderTraversalIterate(TreeNode root) {
        Deque<TreeNode> nodeStack = new LinkedList<>();
        nodeStack.push(root);
        TreeNode cursorNode = root;
        while (!nodeStack.isEmpty()) {
            cursorNode = nodeStack.peek();
            if (Objects.nonNull(cursorNode)) {
                nodeStack.pop();
                nodeStack.push(cursorNode);
                nodeStack.push(null);
                if (Objects.nonNull(cursorNode.right)) {
                    nodeStack.push(cursorNode.right);
                }
                if (Objects.nonNull(cursorNode.left)) {
                    nodeStack.push(cursorNode.left);
                }
            } else {
                nodeStack.pop();
                TreeNode toProcess = nodeStack.pop();
                postorderTraversals.add(toProcess.val);
            }
        }
    }
    //endregion

    //region 递归
    private void doPostorderTraversalRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        } else {
            if (Objects.nonNull(root.left)) {
                doPostorderTraversalRecursion(root.left);
            }
            postorderTraversals.add(root.val);
            if (Objects.nonNull(root.right)) {
                doPostorderTraversalRecursion(root.right);
            }
            return;
        }
    }
    //endregion

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        System.out.println(new PostorderTraversal().postorderTraversal(root));
    }
}
