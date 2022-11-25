package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description 279. 完全平方数
 * @date 13/3/22 10:45 下午
 */
public class NumSquares {
    public int numSquares(int n) {
        //dp[i],和为i完全平方数的最少数量
        //转移方程,dp[i] =  dp[i-1]+1;dp[i-4]+1;dp[i-9]+1;dp[i-16]+1  1+min
        // (dp[i-j^2])
        //遍历顺序,1...n
        //初始化,dp[0]=1;dp[1]=1,dp[2]=2;dp[3]=3;dp[4]=4,dp[5]=2,dp[6]=3,
        // dp[7]=4,dp[8]=2,dp[9]=3,dp[10]=4
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int iMin = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                iMin = Math.min(iMin, dp[i - j * j]);
            }
            dp[i] = iMin+1;
        }
        return dp[n];
    }
}
