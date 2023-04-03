package com.yunlong.lee.dataStructure.binTree.traversal;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 二叉搜索树的最近公共祖先
 * @date 31/3/23 10:35 上午
 * @link https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestor {
    TreeNode common = new TreeNode();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findCommonAncestor(root, p, q);
        return common;
    }

    //region 非递归两次遍历
    private void findCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<Integer> pathP = getNodePath(root, p);
        List<Integer> pathQ = getNodePath(root, q);
        common = new TreeNode(getLastCommon(pathP, pathQ));
        return;
    }

    private Integer getLastCommon(List<Integer> pathP, List<Integer> pathQ) {
        List<Integer> shortList = pathP.size() > pathQ.size() ? pathQ : pathP;
        List<Integer> longList = pathP.size() > pathQ.size() ? pathP : pathQ;
        for (int i = 0; i < shortList.size(); i++) {
            if (!shortList.get(i).equals(longList.get(i))) {
                return shortList.get(i - 1);
            }
        }
        return shortList.get(shortList.size() - 1);
    }

    private List<Integer> getNodePath(TreeNode root, TreeNode x) {
        List<Integer> path = new LinkedList<>();
        for (; ; ) {
            path.add(root.val);
            if (root.val == x.val) {
                break;
            } else if (root.val > x.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return path;
    }
    //endregion


    //region 递归做法

    private void findRecursive(TreeNode root, TreeNode p,
                               TreeNode q) {
        TreeNode left = p;
        TreeNode right = q;
        if (p.val > q.val) {
            left = q;
            right = p;
        }
        findCommonNodeRecursive(root, left, right);
    }

    private void findCommonNodeRecursive(TreeNode root, TreeNode left,
                                         TreeNode right) {
        if (Objects.isNull(root)) {
            return;
        }
        if (root.val >= left.val && root.val <= right.val) {
            common = root;
            return;
        } else if (left.val < root.val && right.val < root.val) {
            findCommonNodeRecursive(root.left, left, right);
        } else {
            findCommonNodeRecursive(root.right, left, right);
        }
    }
    //endregion

    public static void main(String[] args) {
        // int[] inOrder = new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9};
        // int[] postOrder = new int[]{0, 3, 5, 4, 2, 7, 9, 8, 6};

        int[] inOrder = new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] postOrder = new int[]{0, 3, 5, 4, 2, 7, 9, 8, 6};
        TreeNode root = TreeNodeUtils.buildTree(inOrder, postOrder);
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        TreeNode common = new LowestCommonAncestor().lowestCommonAncestor(root, p
                , q);
        System.out.println(common.val);
    }
}
