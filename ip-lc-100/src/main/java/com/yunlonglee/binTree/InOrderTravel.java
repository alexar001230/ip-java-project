package com.yunlonglee.binTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.genBinTree;

/**
 * @author lijie
 * @version 1.0
 * @description 中序遍历
 * @date 12/1/22 11:18 下午
 */
public class InOrderTravel {
    public static void main(String[] args) {
        TreeNode root = genBinTree();
        System.out.println(inOrderTravel(root));
    }


    public static List<Integer> inOrderTravel(TreeNode root) {
        List<Integer> inOrderList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.peek();
            if (null != curNode) {
                //防止重复处理
                stack.pop();
                if (null != curNode.right) {
                    stack.push(curNode.right);
                }
                stack.push(curNode);
                //访问过但是没处理过,用空标记
                stack.push(null);
                if (null != curNode.left) {
                    stack.push(curNode.left);
                }

            } else {
                //空标记先弹栈
                stack.pop();
                TreeNode toHandle = stack.peek();
                stack.pop();
                inOrderList.add(toHandle.val);
            }
        }
        return inOrderList;
    }
}
