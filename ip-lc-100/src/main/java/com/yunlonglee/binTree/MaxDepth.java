package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 104. 二叉树的最大深度
 * @date 13/2/22 9:56 下午
 */
public class MaxDepth {
    public int maxDepthIteration(TreeNode root){
        if(null == root){
            return 0;
        }
        List<String> allPaths = new ArrayList<>();
        //遍历，记录深度，求出最大深度
        Stack<Object> stack = new Stack<>();
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            String path = (String) stack.pop();
            TreeNode curNode = (TreeNode) stack.pop();
            if (null == curNode.left && null == curNode.right) {
                allPaths.add(path);
            }
            if (null != curNode.left) {
                stack.push(curNode.left);
                stack.push(path + "-->" + curNode.left.val);
            }

            if (null != curNode.right) {
                stack.push(curNode.right);
                stack.push(path + "-->" + curNode.right.val);
            }
        }
        String maxPath = "";
        for (String path : allPaths) {
            if (maxPath.length() < path.length()) {
                maxPath = path;
            }
        }
        return maxPath.split("-->").length;
    }

    public int recursive(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftMaxDepth = recursive(root.left);
        int rightMaxDepth = recursive(root.left);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
