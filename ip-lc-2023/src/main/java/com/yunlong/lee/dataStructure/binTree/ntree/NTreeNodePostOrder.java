package com.yunlong.lee.dataStructure.binTree.ntree;

import com.yunlong.lee.utils.tree.Node;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 590. N 叉树的后序遍历
 * @date 27/4/23 11:23 上午
 * @link https://leetcode.cn/problems/n-ary-tree-postorder-traversal/
 */
public class NTreeNodePostOrder {
    public List<Integer> postorder(Node root) {
        doPostorderRecursion(root);
        return postOrders;
    }

    LinkedList<Integer> postOrders = new LinkedList<>();
    Deque<Node> nodeStack = new LinkedList<>();

    //region 迭代方式
    private void doPostorderIterate(Node root) {
        if (Objects.isNull(root)) {
            return;
        }
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            Node cursorNode = nodeStack.peek();
            if (Objects.nonNull(cursorNode)) {
                nodeStack.push(null);//null隔开做标记
                if (Objects.nonNull(cursorNode.children) && cursorNode.children.size() > 0) {
                    visitCursorNodeChildren(cursorNode);
                }
            } else {
                nodeStack.pop();//null标记抛栈
                Node toProcessNode = nodeStack.pop();
                postOrders.add(toProcessNode.val);
            }
        }
    }


    //访问游标节点孩子节点(孩子节点逆序入栈)
    private void visitCursorNodeChildren(Node cursorNode) {
        for (int i = cursorNode.children.size() - 1; i >= 0; i--) {
            nodeStack.push(cursorNode.children.get(i));
        }
    }
    //endregion


    //region 递归
    private void doPostorderRecursion(Node root) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.nonNull(root.children) && root.children.size() > 0) {
            for (int i = 0; i < root.children.size(); i++) {
                doPostorderRecursion(root.children.get(i));
            }
        }
        postOrders.add(root.val);
        return;
    }
    //endregion


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        LinkedList<Node> node3Childs = new LinkedList<>(Arrays.asList(node5,
                node6));
        node3.children = node3Childs;

        LinkedList<Node> node1Childs = new LinkedList<>(Arrays.asList(node3,
                node2, node4));
        node1.children = node1Childs;


        System.out.println(new NTreeNodePostOrder().postorder(node1));

    }
}
