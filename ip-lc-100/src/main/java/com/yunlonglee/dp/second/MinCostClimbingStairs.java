package com.yunlonglee.dp.second;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 17/2/22 1:49 下午
 */
public class MinCostClimbingStairs {
    public static int minCostClimbingStairs(int[] cost) {
        //dp动态规划5步走
        //1.dp[i]含义，到第i个阶梯最小费用
        //2.转移方程，dp[i] = min(dp[i-1],dp[i-2])+cost[i]
        //3.遍历顺序,遍历楼梯层级费用cost[i]
        //4.初始化，dp[0]=cost[0],dp[1]=cost[1],dp[2] = min(dp[0],dp[1])+cost[2]
        //5.举例推导
        if (cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        if (cost.length == 3) {
            return Math.min(cost[0] + cost[2], cost[1]);
        }
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        dp[2] = Math.min(cost[0], cost[1]) + cost[2];
        //dp[3] = Math.min(cost[1]+cost[3],cost[2]);
        for (int i = 3; i <= cost.length - 1; i++) {
            if (i < cost.length) {
                dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
            } else {
                dp[i] = Math.min(dp[i - 1], dp[i - 2] + cost[i]);
            }

        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 1, 1};
        System.out.println(minCostClimbingStairs(arr));
    }
}
