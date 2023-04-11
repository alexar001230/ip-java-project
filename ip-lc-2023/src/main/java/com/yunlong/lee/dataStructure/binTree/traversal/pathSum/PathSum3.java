package com.yunlong.lee.dataStructure.binTree.traversal.pathSum;

import com.alibaba.fastjson.JSON;
import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 437. 路径总和 III
 * @date 10/4/23 3:53 下午
 * @link https://leetcode.cn/problems/path-sum-iii/
 */
public class PathSum3 {
    public int pathSum(TreeNode root, int targetSum) {
        return doPathSum(root, targetSum);
    }

    private LinkedList<LinkedList<Integer>> allPaths = new LinkedList<>();
    private int totalCnt = 0;

    private int doPathSum(TreeNode root, int targetSum) {
        //1.dfs先求所有的路径
        findAllPaths(root, new LinkedList<>());
        //2.在所有路径中找和为targetSum的路径
        for (LinkedList<Integer> aPath : allPaths) {
            int curCnt = sum2TargetCnt(aPath, targetSum);
            totalCnt += curCnt;
        }
        return totalCnt;
    }

    private int sum2TargetCnt(LinkedList<Integer> aPath, int target) {
        int cnt = 0;
        for (int i = 0; i < aPath.size() - 1; i++) {
            if (aPath.get(i) == target) {
                cnt++;
                continue;
            }
            if (aPath.get(i) > target) {
                continue;
            }
            int left = target - aPath.get(i);
            for (int j = i + 1; j < aPath.size(); j++) {
                left = left - aPath.get(j);
                if (left == 0) {
                    cnt++;
                }
                if (left < 0) {
                    break;
                }
            }
        }
        return cnt;
    }


    //region dfs 递归遍历求所有路径
    private void findAllPaths(TreeNode root, LinkedList<Integer> aPath) {
        //1.出口
        if (Objects.isNull(root)) {
            return;
        }
        //2.处理数据
        aPath.add(root.val);
        if (Objects.isNull(root.right) && Objects.isNull(root.left)) {
            allPaths.add(new LinkedList<>(aPath));
            aPath.removeLast();
            return;
        }
        //3.递归迭代
        if (Objects.nonNull(root.left)) {
            findAllPaths(root.left, aPath);
        }
        if (Objects.nonNull(root.right)) {
            findAllPaths(root.right, aPath);
        }
        aPath.removeLast();
        return;
    }

    //endregion
    private LinkedList<Integer> inOrders = new LinkedList<>();

    private List<Integer> inOrder(TreeNode root) {
        inOrderRecursion(root);
        return inOrders;
    }

    private void inOrderRecursion(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        } else {
            inOrderRecursion(root.left);
            inOrders.add(root.val);
            inOrderRecursion(root.right);
        }
    }

    public static void main(String[] args) {
        int[] inOrder = new int[]{3, 3, -2, 5, 2, 1, 10, -3, 11};
        int[] postOrder = new int[]{3, -2, 3, 1, 2, 5, 11, -3, 10};
        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrder, postOrder);
        // TreeNode root = TreeNodeUtils.genBinTree();
        System.out.println(JSON.toJSONString(TreeNodeUtils.levelOrderTraversal(root)));
        // System.out.println(JSON.toJSONString(TreeNodeUtils.inOrder(root)));
        // System.out.println(JSON.toJSONString(TreeNodeUtils.postOrder(root)));
        System.out.println(new PathSum3().pathSum(root, 8));
        // System.out.println(JSON.toJSONString(new PathSum3().inOrder(root)));
    }

    //       10
    //    5       -3
    // 3      2        11
    //   -2      1
    //  3

}
