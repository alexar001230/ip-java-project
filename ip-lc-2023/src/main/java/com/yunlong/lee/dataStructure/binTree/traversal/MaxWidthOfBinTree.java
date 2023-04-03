package com.yunlong.lee.dataStructure.binTree.traversal;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lijie
 * @version 1.0
 * @description 二叉树的最大宽度
 * @date 31/3/23 3:54 下午
 * @link https://leetcode.cn/problems/maximum-width-of-binary-tree/
 */
public class MaxWidthOfBinTree {
    public int widthOfBinaryTree(TreeNode root) {
        return levelOrderTraversal(root);
    }


    private Queue<OrderedTreeNode> levelNodesQ = new LinkedList<>();
    private List<List<OrderedTreeNode>> allLevels = new LinkedList<>();

    class OrderedTreeNode {
        //当前节点
        public TreeNode treeNode;
        //当前节点编号,规则:假设parent为n，则当前节点为left,order=2n,为right,order=2n+1
        public int order;

        public OrderedTreeNode(TreeNode treeNode, int order) {
            this.treeNode = treeNode;
            this.order = order;
        }
    }

    public int levelOrderTraversal(TreeNode root) {
        OrderedTreeNode orderedRoot = new OrderedTreeNode(root, 1);
        levelNodesQ.offer(orderedRoot);
        while (!levelNodesQ.isEmpty()) {
            List<OrderedTreeNode> aLevel = new ArrayList<>();
            //层节点个数
            int curQSize = levelNodesQ.size();
            while (curQSize > 0) {
                OrderedTreeNode curOrderedNode = levelNodesQ.poll();
                TreeNode curNode = curOrderedNode.treeNode;
                int curOrder = curOrderedNode.order;
                curQSize--;
                //不空节点染色
                if (curNode.left != null) {
                    OrderedTreeNode newLeftOrderedNode =
                            new OrderedTreeNode(curNode.left, curOrder * 2);
                    levelNodesQ.offer(newLeftOrderedNode);
                }
                if (curNode.right != null) {
                    OrderedTreeNode newRightOrderedNode =
                            new OrderedTreeNode(curNode.right, curOrder * 2 + 1);
                    levelNodesQ.offer(newRightOrderedNode);
                }
                aLevel.add(curOrderedNode);
            }
            allLevels.add(aLevel);
        }
        int maxWidth = Integer.MIN_VALUE;
        for (List<OrderedTreeNode> levelOrderNodes : allLevels) {
            int curWidth =
                    levelOrderNodes.get(levelOrderNodes.size() - 1).order - levelOrderNodes.get(0).order + 1;
            maxWidth = Math.max(maxWidth,curWidth);
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        System.out.println(new MaxWidthOfBinTree().levelOrderTraversal(root));
    }
}
