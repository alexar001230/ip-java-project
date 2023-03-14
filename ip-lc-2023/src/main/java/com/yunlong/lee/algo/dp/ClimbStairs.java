package com.yunlong.lee.algo.dp;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/3/23 7:21 下午
 * @link https://leetcode.cn/problems/climbing-stairs/
 */
public class ClimbStairs {
    /**
     * dp 五步走
     * 1.dp[i]含义,到第i阶楼梯又dp[i]种方法
     * 2.转移方程,dp[i]= dp[i-1]+1+dp[i-2]+1
     * 3.初始化 dp[0]=0,dp[1] = 1,dp[2] = 2
     * 4.遍历顺序
     * 5.举例验证
     */


    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new ClimbStairs().climbStairs(3));
    }
}
