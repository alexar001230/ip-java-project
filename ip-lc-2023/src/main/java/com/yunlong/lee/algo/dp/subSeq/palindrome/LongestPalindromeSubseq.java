package com.yunlong.lee.algo.dp.subSeq.palindrome;

/**
 * @author lijie
 * @version 1.0
 * @description 516. 最长回文子序列
 * @date 6/5/23 6:10 下午
 * @link https://leetcode.cn/problems/longest-palindromic-subsequence/description/
 */
public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        return doLongestPalindromeSubseq(s);
    }

    private int doLongestPalindromeSubseq(String s) {
        //dp 5 步走
        //1.dp[i][j]含义，串下标i到j最长回文子串的长度
        //2.转移方程,a.s[i]=s[j],dp[i][j] = dp[i+1][j-1]+2,
        // b.s[i]!=s[j],dp[i][j] = max(dp[i+1][j],dp[i][j-1])
        //3.初始化,i<=j,i=j,dp[i][j] = 1,i>j,dp[i][j] = 0
        //4.循环遍历,外层逆序  i+1，内层顺序 j-1
        //5.举例验证
        int len = s.length();
        int[][] dpArr = new int[s.length()][s.length()];

        for (int i = len - 1; i >= 0; i--) {
            dpArr[i][i] = 1;
            char iChar = s.charAt(i);
            for (int j = i + 1; j < len; j++) {
                if (iChar == s.charAt(j)) {
                    dpArr[i][j] = dpArr[i + 1][j - 1] + 2;
                } else {
                    dpArr[i][j] = Math.max(dpArr[i + 1][j], dpArr[i][j - 1]);
                }
            }
        }
        return dpArr[0][s.length() - 1];
    }

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(new LongestPalindromeSubseq().longestPalindromeSubseq(s));
    }
}
