package com.yunlonglee.binTree;

import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 662. 二叉树最大宽度
 * @date 13/2/22 9:13 下午
 */
public class WidthOfBinTree {
    /**
     * 技巧:1.用val值来对层节点序列计数，2.向左，left.val = root.val*2
     * 向右,right.val = root.val*2+1,
     * 3.计算层宽，levelQ.getLast().val - levelQ.getFirst() + 1;
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        int maxLevel = Integer.MIN_VALUE;
        //层序遍历，找出最大宽度的层
        LinkedList<TreeNode> levelQ = new LinkedList<>();
        levelQ.offer(root);
        while (!levelQ.isEmpty()) {
            int levelElementSize = levelQ.size();
            //队尾值-队头值+1表示层宽
            int levelWidth = levelQ.getLast().val - levelQ.getFirst().val + 1;
            if (maxLevel < levelWidth) {
                maxLevel = levelWidth;
            }
            int i = 0;
            while (i < levelElementSize) {
                TreeNode curNode = levelQ.peek();
                if (null != curNode.left) {
                    curNode.left.val = curNode.val * 2;
                    levelQ.add(curNode.left);
                }
                if (null != curNode.right) {
                    curNode.right.val = curNode.val * 2 + 1;
                    levelQ.add(curNode.right);
                }
                levelQ.poll();
                i++;
            }
        }
        return maxLevel;
    }
}
