package com.yunlong.lee.algo.dp.base;

/**
 * @author lijie
 * @version 1.0
 * @description 62. 不同路径
 * @date 10/4/23 11:18 上午
 * @link https://leetcode.cn/problems/unique-paths/
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        return doUniquePaths(m, n);
    }

    private int doUniquePaths(int m, int n) {
        //dp 5步走
        //1.确定dp[i,j]含义，从i位置到j位置总共有dp[i,j]条不同的路径
        //2.转移方程，dp[i,j] = dp[i-1,j]+dp[i,j-1],跟谁有关，不能死背套路，关键看转移过程,将文本描述转换成语言实现
        //3.初始化，dp[0,0]=0,dp[0,j] = 1;dp[i,0] = 1;
        //4.遍历顺序 顺序遍历i,j
        //5.举例验证,dp[1,1] =

        if (m <= 1 || n <= 1) {
            return 1;
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 0;
        //第一行初始化
        for (int i = 1; i <= m - 1; i++) {
            dp[i][0] = 1;
        }
        //第一列初始化
        for (int j = 1; j <= n - 1; j++) {
            dp[0][j] = 1;
        }


        for (int i = 1; i <= m - 1; i++) {
            for (int j = 1; j <= n - 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }

        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // int m = 3, n = 7;
        int m = 3, n = 3;
        // int m = 3, n = 2;
        System.out.println(new UniquePaths().uniquePaths(m, n));
    }
}
