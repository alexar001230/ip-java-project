package com.yunlong.lee.dataStructure.binTree.traversal;

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
        if (Objects.nonNull(root)) {
            aPath.add(root.val);
        }

        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            List<Integer> newPath = aPath;
            allPaths.add(newPath);
            aPath.removeLast();
            return;
        }
        binTreePathRecursive(root.left, aPath);
        binTreePathRecursive(root.right, aPath);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtils.genBinTree();
        System.out.println(new PathSum2Target().pathSum(root, 12));
    }
}
