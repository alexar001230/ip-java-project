package com.yunlong.lee.dataStructure.binTree.traversal.pathSum;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 二叉树路径和为某目标值(根节点到叶子节点)
 * @date 31/3/23 6:31 下午
 * @link https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 */
public class PathSum2Target {
    List<List<Integer>> allPaths = new LinkedList<>();
    List<List<Integer>> targetPaths = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        binTreePathRecursive(root, new LinkedList<>());
        findTargetPaths(allPaths, target);
        return targetPaths;
    }


    private void findTargetPaths(List<List<Integer>> allPaths, int target) {
        for (List<Integer> aPath : allPaths) {
            int aPathSum = aPath.stream().mapToInt(Integer::intValue).sum();
            if (aPathSum == target) {
                targetPaths.add(aPath);
            }
        }
    }

    private void binTreePathRecursive(TreeNode root, LinkedList<Integer> aPath) {
        //1.确定出口
        if (Objects.isNull(root)) {
            return;
        }
        //2.处理节点数据
        aPath.add(root.val);
        if (Objects.isNull(root.right) && Objects.isNull(root.left)) {
            allPaths.add(new LinkedList<>(aPath));
        }
        //3.递归迭代
        if (Objects.nonNull(root.left)) {
            binTreePathRecursive(root.left, aPath);
        }
        if (Objects.nonNull(root.right)) {
            binTreePathRecursive(root.right, aPath);
        }
        aPath.removeLast();
    }

    public static void main(String[] args) {
        int[] inOrderArr = new int[]{7, 11, 2, 4, 5, 13, 8, 5, 4, 1};
        int[] postOrderArr = new int[]{7, 2, 11, 4, 13, 5, 1, 4, 8, 5};

        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrderArr,
                postOrderArr);
        // TreeNode root = TreeNodeUtils.genBinTree();

        System.out.println(new PathSum2Target().pathSum(root, 22));
    }
}
