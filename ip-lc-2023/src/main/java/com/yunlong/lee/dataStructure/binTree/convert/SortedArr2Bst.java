package com.yunlong.lee.dataStructure.binTree.convert;

import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lijie
 * @version 1.0
 * @description 有序数组转二叉搜索树
 * @date 31/3/23 2:42 下午
 * @link https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 */
public class SortedArr2Bst {

    public TreeNode sortedArrayToBST(int[] nums) {
        //return traversalRecursive(nums, 0, nums.length - 1);
        sortedArrayToBSTIterator(nums);
        return root;
    }


    //region 非递归方式
    TreeNode root = new TreeNode(-1);
    private TreeNode sortedArrayToBSTIterator(int[] nums) {
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> leftIdxQ = new LinkedList<>();
        Queue<Integer> rightIdxQ = new LinkedList<>();
        nodeQ.add(root);
        leftIdxQ.add(0);
        rightIdxQ.add(nums.length - 1);
        while (!nodeQ.isEmpty()) {
            TreeNode curNode = nodeQ.poll();
            int leftIdx = leftIdxQ.poll();
            int rightIdx = rightIdxQ.poll();

            int midIdx = (leftIdx + rightIdx) / 2;

            curNode.val = nums[midIdx];

            if (leftIdx <= midIdx - 1) {
                curNode.left = new TreeNode(-1);
                nodeQ.add(curNode.left);
                leftIdxQ.add(leftIdx);
                rightIdxQ.add(midIdx - 1);
            }

            if (rightIdx >= midIdx + 1) {
                curNode.right = new TreeNode(-1);
                nodeQ.add(curNode.right);
                leftIdxQ.add(midIdx + 1);
                rightIdxQ.add(rightIdx);
            }
        }
        return root;
    }
    //endregion

    //region 递归方式
    private TreeNode traversalRecursive(int[] nums,
                                        int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = traversalRecursive(nums, left, mid - 1);
        root.right = traversalRecursive(nums, mid + 1, right);
        return root;
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 9};
        System.out.println(TreeNodeUtils.levelOrderTraversal(
                new SortedArr2Bst().sortedArrayToBST(nums)));
    }

}
