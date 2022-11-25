package com.yunlonglee.binTree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import static com.yunlonglee.binTree.BuildTreeFromInAndPost.buildTree;

/**
 * @author lijie
 * @version 1.0
 * @description 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * @date 13/2/22 7:09 下午
 */
public class LowestCommonAncester {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p,
                                                TreeNode q) {
        //两次遍历，因为是搜索树，1.遍历二叉树，寻找到p节点的路径，并记录成路径数组 2.寻找q节点，并记录路径数组
        //3.同时遍历两个数组路径，寻找第一个分叉点，即是最近公共祖先
        List<Integer> pPath = new ArrayList<>();
        List<Integer> qPath = new ArrayList<>();
        findPath(root, p, pPath);
        findPath(root, q, qPath);
        TreeNode lowestCommonAncester = new TreeNode(0);
        Integer commonAncesterVal = null;
        //存在分叉点
        int minSize = Math.min(pPath.size(), qPath.size());
        int i = 0;
        while (i < minSize) {
            if (pPath.get(i).equals(qPath.get(i))) {
                i++;
                continue;
            } else {
                if (i >= 1) {
                    commonAncesterVal = pPath.get(i - 1);
                    break;
                }
            }
        }
        //在同一条路径上
        if (null == commonAncesterVal) {
            List<Integer> minPath = pPath.size() < qPath.size() ? pPath : qPath;
            commonAncesterVal = minPath.get(i - 1);
        }
        lowestCommonAncester = findTargetNode(root, commonAncesterVal);
        return lowestCommonAncester;
    }

    private static TreeNode findTargetNode(TreeNode root, int target) {
        if (null == root) {
            return null;
        }
        if (target == root.val) {
            return root;
        }
        if (target < root.val && null != root.left) {
            return findTargetNode(root.left, target);
        }
        if (target > root.val && null != root.right) {
            return findTargetNode(root.right, target);
        }
        return null;
    }

    private static void findPath(TreeNode root, TreeNode target,
                                 List<Integer> path) {
        //递归三步曲 1.递归出口 2.迭代条件 3.是否需要返回值
        if (null == root) {
            return;
        }
        path.add(root.val);
        if (target.val == root.val) {
            return;
        }
        if (target.val < root.val) {
            findPath(root.left, target, path);
        }
        if (target.val > root.val) {
            findPath(root.right, target, path);
        }
        return;
    }

    public static void main(String[] args) {
        int[] inOrder = new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] postOrder = new int[]{0, 3, 5, 4, 2, 7, 9, 8, 6};
        TreeNode treeNode = buildTree(inOrder, postOrder);
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(4);
        TreeNode target = lowestCommonAncestor(treeNode, p, q);
        System.out.println(JSON.toJSONString(target));
    }
}
