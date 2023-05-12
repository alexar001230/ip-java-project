package com.yunlong.lee.algo.dp.knapsack.knapsack01;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 494. 目标和
 * @date 12/5/23 12:15 上午
 * @link https://leetcode.cn/problems/target-sum/
 */
public class FindTargetSumWays {
    public int findTargetSumWays(int[] nums, int target) {
        return doFindTargetSumWays(nums, target);
    }


    private int doFindTargetSumWaysAns(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    private int doFindTargetSumWays(int[] nums, int target) {
        // sum-right-right = target --> right = (sum-target)/2 --> 转换成01背包
        int sum = Arrays.stream(nums).sum();
        int diff = sum - target;
        if (diff % 2 != 0 || diff < 0) {
            return 0;
        }
        int bagSize = diff / 2;
        int[][] dp = new int[nums.length + 1][bagSize + 1];
        // dp[i][j] = j<w[i]?dp[i-1][j-1]?max(dp[i-1][j],dp[i-1][j-w[i]]+v[i])
        dp[0][0] = 1;
        //迭代转移
        for (int i = 1; i <= nums.length; i++) {
            int numsIdx = i - 1;
            int iNo = nums[numsIdx];
            for (int j = 0; j <= bagSize; j++) {
                if (j >= iNo) {
                    dp[i][j] = dp[i - 1][j - iNo]/**选iNo**/ + dp[i - 1][j]/**不选iNo**/;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length][bagSize];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1};
        int target = 0;
        System.out.println(new FindTargetSumWays().findTargetSumWays(nums, target));
    }
}
