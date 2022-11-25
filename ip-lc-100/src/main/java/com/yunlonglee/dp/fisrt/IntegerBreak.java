package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description 343. 整数拆分
 * @date 17/2/22 2:10 下午
 */
public class IntegerBreak {
    public static int integerBreak(int n) {
        //1.dp[i]含义,正整数n拆分为至少两个数的最大乘积
        //2.转移方程
        //a.i=1不可拆分 dp[1] = 0;
        //b.i>=2,dp[i] = max(i*(n-i),i*dp[n-i])
        //3.遍历顺序  i in(1,n)
        //4.初始化  dp[1] = 0,dp[2] = 1
        //5.举例推导
        int[] dp = new int[n + 1];
        if (n == 0 || n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= (i/2+1); j++) {
                int tmp = i - j;
                dp[i] = Math.max(Math.max(j * tmp, j * dp[tmp]),max);
                max = Math.max(dp[i],max);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }
}
