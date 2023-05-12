package com.yunlong.lee.algo.dp.knapsack.knapsack01;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 416. 分割等和子集
 * @date 12/5/23 10:41 上午
 * @link https://leetcode.cn/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        return doCanPartitionByScrollArr(nums);
    }


    //region 滚动数组
    private boolean doCanPartitionByScrollArr(int[] nums) {
        int len = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int bagSize = sum / 2;
        int[] dp = new int[bagSize + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]]+ nums[i]);
            }
        }
        return dp[bagSize] == bagSize;
    }

    //endregion
    //region 二维数组
    private boolean doCanPartition(int[] nums) {
        //sum-right - right = 0
        int len = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int bagSize = sum / 2;
        int[][] dp = new int[len + 1][bagSize + 1];
        for (int i = 1; i <= len; i++) {
            int noIdx = i - 1;
            int iNo = nums[noIdx];
            for (int j = 0; j <= bagSize; j++) {
                if (j >= iNo) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - iNo] + iNo);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][bagSize] == bagSize;
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 5};
        System.out.println(new PartitionEqualSubsetSum().canPartition(nums));
    }
}
