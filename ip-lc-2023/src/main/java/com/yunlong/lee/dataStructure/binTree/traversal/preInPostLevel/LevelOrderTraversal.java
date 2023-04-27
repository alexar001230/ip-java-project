package com.yunlong.lee.dataStructure.binTree.traversal.preInPostLevel;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 二叉树层序遍历
 * @date 31/3/23 12:48 下午
 * @link https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class LevelOrderTraversal {
    private LinkedList<TreeNode> levelNodeQ = new LinkedList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelNums = new LinkedList<>();
        if (Objects.isNull(root)) {
            return levelNums;
        }
        levelNodeQ.add(root);
        while (!levelNodeQ.isEmpty()) {
            //记录每层的数据
            List<Integer> aLevelNums = new LinkedList<>();
            //层间标识隔开
            //levelNodeQ.add(null);
            int curLevelSize = levelNodeQ.size();
            while (curLevelSize > 0) {
                TreeNode curNode = levelNodeQ.peek();
                if (Objects.nonNull(curNode.left)) {
                    levelNodeQ.add(curNode.left);
                }
                if (Objects.nonNull(curNode.right)) {
                    levelNodeQ.add(curNode.right);
                }
                aLevelNums.add(curNode.val);
                curLevelSize--;
                levelNodeQ.poll();
            }
            //层数据保存
            levelNums.add(aLevelNums);
            //间隔标识出队
            // levelNodeQ.poll();
        }
        return levelNums;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        System.out.println(new LevelOrderTraversal().levelOrder(root));
    }


}
