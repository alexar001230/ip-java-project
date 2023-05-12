package com.yunlong.lee.algo.dp.base;

/**
 * @author lijie
 * @version 1.0
 * @description 746. 使用最小花费爬楼梯
 * @date 10/5/23 8:14 下午
 * @link https://leetcode.cn/problems/min-cost-climbing-stairs/
 */
public class MinCostClimbStairs {
    public int minCostClimbingStairs(int[] cost) {
        return doMinCostClimbingStairs(cost);
    }

    private int doMinCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            int cost1 = cost[i - 1] + dp[i - 1];
            int cost2 = cost[i - 2] + dp[i - 2];
            dp[i] = Math.min(cost1, cost2);
        }
        return dp[cost.length];
    }


    public static void main(String[] args) {
        int[] costArr = new int[]{10, 15, 20};
        System.out.println(new MinCostClimbStairs().minCostClimbingStairs(costArr));
    }
}
