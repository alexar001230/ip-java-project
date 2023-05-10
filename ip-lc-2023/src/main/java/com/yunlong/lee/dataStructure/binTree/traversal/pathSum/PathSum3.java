package com.yunlong.lee.dataStructure.binTree.traversal.pathSum;

import com.alibaba.fastjson.JSON;
import com.yunlong.lee.utils.tree.TreeNode;
import com.yunlong.lee.utils.tree.TreeNodeUtils;

import java.util.HashMap;
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


    //region 前缀和，在一次递归深度遍历过程中，通过map保存前缀和为target-curVal与数量的map
    //将结果累加,更新map同时回溯时记得-1
    private int res = 0;

    private int doPathSumByPrefixSum(TreeNode root, int targetSum) {
        target = targetSum;
        prefixSum2CntMap.put(0L, 1);
        preOrderTraversalRecursion(root, 0L);
        return res;
    }

    HashMap<Long, Integer> prefixSum2CntMap = new HashMap<>();
    int target;

    private void preOrderTraversalRecursion(TreeNode root, long curPrefixSum) {
        if (Objects.isNull(root)) {
            return;
        }
        curPrefixSum = curPrefixSum + root.val;
        int aCnt = prefixSum2CntMap.getOrDefault(curPrefixSum - target, 0);
        res = res + aCnt;
        prefixSum2CntMap.put(curPrefixSum,
                prefixSum2CntMap.getOrDefault(curPrefixSum, 0) + 1);
        preOrderTraversalRecursion(root.left, curPrefixSum);
        preOrderTraversalRecursion(root.right, curPrefixSum);
        prefixSum2CntMap.put(curPrefixSum,
                prefixSum2CntMap.getOrDefault(curPrefixSum, 0) - 1);
    }
    //endregion


    //region 1.dfs求所有路径 2.所有路径求子序和为target
    // 这个算法会重复计算骨干路径上和为target的路径,这部分怎么去掉(通过map去掉了，但是oj超时了，复杂度n3)
    private LinkedList<LinkedList<TreeNode>> allPaths = new LinkedList<>();
    private int totalCnt = 0;

    private int doPathSum(TreeNode root, int targetSum) {
        //1.dfs先求所有的路径
        findAllPaths(root, new LinkedList<>());
        //2.在所有路径中找和为targetSum的路径
        for (LinkedList<TreeNode> aPath : allPaths) {
            int curCnt = sum2TargetCntByPrefixSum(aPath, targetSum);
            totalCnt += curCnt;
        }
        return totalCnt;
    }

    // 通过前缀和算子序和,但是无法去重了
    private int sum2TargetCntByPrefixSum(LinkedList<TreeNode> aPath,
                                         long target) {
        int cnt = 0;
        long pre = 0;
        HashMap<Long, Integer> preJSum2CntMap = new HashMap<>();
        for (int i = 0; i < aPath.size(); i++) {
            pre = pre + aPath.get(i).val;
            if (preJSum2CntMap.containsKey(pre - target)) {
                cnt = cnt + preJSum2CntMap.get(pre - target);
            }
            preJSum2CntMap.put(pre, preJSum2CntMap.getOrDefault(pre, 0) + 1);
        }
        return cnt;
    }


    private HashMap<String, Boolean> antiRepeatMap = new HashMap<>();

    private int sum2TargetCnt(LinkedList<TreeNode> aPath, long target) {
        int cnt = 0;
        for (int i = 0; i < aPath.size() - 1; i++) {
            if ((long) aPath.get(i).val == target) {
                String hash = String.valueOf(aPath.get(i).hashCode());
                if (Objects.isNull(antiRepeatMap.get(hash))) {
                    System.out.println("curPath=" + aPath + "idx=" + i + ",hash" +
                            "=" + hash);
                    cnt++;
                    antiRepeatMap.put(hash, true);
                }
            }
            long left = target - (long) aPath.get(i).val;
            for (int j = i + 1; j < aPath.size(); j++) {
                left = left - (long) aPath.get(j).val;
                if (left == 0) {
                    String hash =
                            String.valueOf(aPath.get(i).hashCode()) + aPath.get(j).hashCode();
                    if (Objects.isNull(antiRepeatMap.get(hash))) {
                        System.out.println("curPath=" + aPath + "idx=" + i +
                                "," + j + ",hash=" + hash);
                        cnt++;
                        antiRepeatMap.put(hash, true);
                    }
                }
            }
        }
        if ((long) aPath.get(aPath.size() - 1).val == target) {

            String hash =
                    String.valueOf(aPath.get(aPath.size() - 1).hashCode());
            if (Objects.isNull(antiRepeatMap.get(hash))) {
                System.out.println("curPath=" + aPath + "idx=" + (aPath.size() - 1) + ",hash=" + hash);
                cnt++;
                antiRepeatMap.put(hash, true);
            }
        }
        return cnt;
    }


    //region dfs 递归遍历求所有路径
    private void findAllPaths(TreeNode root, LinkedList<TreeNode> aPath) {
        //1.出口
        if (Objects.isNull(root)) {
            return;
        }
        //2.处理数据
        aPath.add(root);

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

    public static void main(String[] args) {
        int a = 1000000000;
        int b = 294967296;
        int[] inOrder = new int[]{1000000000, 1000000000, 1000000000, 294967296,
                1000000000, 1000000000};
        int[] postOrder = new int[]{1000000000, 1000000000, 1000000000,
                294967296, 1000000000, 1000000000};
        TreeNode root = TreeNodeUtils.buildTreeByInAndPostOrder(inOrder, postOrder);
        // Integer[] levelOrders = new Integer[]{1000000000, 1000000000, null, 294967296, null, 1000000000, null, 1000000000, null, 1000000000};
        // TreeNode root = TreeNodeUtils.buildTreeByLevelOrder(levelOrders);
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
