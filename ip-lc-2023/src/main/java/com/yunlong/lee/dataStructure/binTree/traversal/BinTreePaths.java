package com.yunlong.lee.dataStructure.binTree.traversal;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 257. 二叉树的所有路径
 * @date 26/4/23 7:40 下午
 * @link https://leetcode.cn/problems/binary-tree-paths/
 */
public class BinTreePaths {

    private String SEP = "->";

    public List<String> binaryTreePaths(TreeNode root) {
        return doBinaryTreePathsByIntList(root);
    }

    //region 列表转子串
    private LinkedList<LinkedList<Integer>> allIntPaths = new LinkedList<>();
    private LinkedList<Integer> aPathInts = new LinkedList<>();

    public List<String> doBinaryTreePathsByIntList(TreeNode root) {
        doBinaryTreePathsByStr(root);
        return allPaths;
    }

    private void preOrderTraversalIntList(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            aPathInts.add(root.val);
            allIntPaths.add(new LinkedList<>(aPathInts));
            aPathInts.removeLast();
            return;
        }
        aPathInts.add(root.val);
        if (Objects.nonNull(root.left)) {
            preOrderTraversalIntList(root.left);
        }
        if (Objects.nonNull(root.right)) {
            preOrderTraversalIntList(root.right);
        }
        aPathInts.removeLast();
    }
    //endregion

    //region list会string就不会了 todo
    private LinkedList<String> allPaths = new LinkedList<>();
    private String aPath = "";

    public List<String> doBinaryTreePathsByStr(TreeNode root) {
        preOrderTraversal(root);
        return allPaths;
    }

    private void preOrderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            if (aPath.length() == 0) {
                aPath = aPath + root.val;
            } else {
                aPath = aPath + SEP + root.val;
            }
            allPaths.add(aPath);
            if (aPath.contains(SEP)) {
                aPath = aPath.substring(0, aPath.lastIndexOf(SEP));
            }
            return;
        }
        if (aPath.length() == 0) {
            aPath = aPath + root.val;
        } else {
            aPath = aPath + SEP + root.val;
        }
        if (Objects.nonNull(root.left)) {
            preOrderTraversal(root.left);
        }
        if (Objects.nonNull(root.right)) {
            preOrderTraversal(root.right);
        }
        if (aPath.contains(SEP)) {
            aPath = aPath.substring(0, aPath.lastIndexOf(SEP));
        }
    }
    //endregion

    public static void main(String[] args) {
        int[] inOrders = new int[]{1};
        int[] postOrders = new int[]{1};

        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrders,
                postOrders);

        System.out.println(new BinTreePaths().binaryTreePaths(root));
    }
}
