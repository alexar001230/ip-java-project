package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description 1143. 最长公共子序列
 * @date 20/2/22 1:01 上午
 */
public class LongestCommonSubSequence {
    public static int longestCommonSubsequence(String text1, String text2) {
        //dp[i][j]含义，text1到i，text2到j，最大公共字串长度
        //转移方程，dp[i][j] = 1.max(dp[i-1][j],dp[i][j-1]) 2.dp[i-1][j-1]+1
        //遍历顺序  两个串遍历
        //初始化，dp[0][i] = 0,dp[j][0] = 0
        //举例推导

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                System.out.println(dp[i][j]);
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        String t1 = "abcde";
        String t2 = "ace";
        t1.split(",");

        System.out.println(longestCommonSubsequence(t1, t2));
    }
}
