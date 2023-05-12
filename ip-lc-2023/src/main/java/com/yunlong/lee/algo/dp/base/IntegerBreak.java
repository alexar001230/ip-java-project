package com.yunlong.lee.algo.dp.base;

/**
 * @author lijie
 * @version 1.0
 * @description 343. 整数拆分
 * @date 11/5/23 2:36 下午
 * @link https://leetcode.cn/problems/integer-break/
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        return doIntegerBreak(n);
    }

    private int doIntegerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

    // private int break2NoMaxRecursion(int n) {
    //     if (n <= 4) {
    //         dp = new int[n + 1];
    //         dp[0] = 1;  //2
    //         dp[1] = 2;  //3
    //         dp[2] = 4;  // 4
    //         return dp[n - 2];
    //     }
    //     int max = 0;
    //     for (int j = 2; j <= n / 2; j++) {
    //         int no1 = j;
    //         int no2 = n - j;
    //         int aMax = no1 * no2;
    //         int max1 = break2NoMaxRecursion(no1);
    //         int max2 = break2NoMaxRecursion(no2);
    //         int breakMax = Math.max(Math.max(max1 * max2, no1 * max2), no2 * max1);
    //         int curMax = Math.max(aMax, breakMax);
    //         max = Math.max(curMax, max);
    //     }
    //     // dp[i] = max;
    //     return max;
    // }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(new IntegerBreak().integerBreak(n));
    }
}
