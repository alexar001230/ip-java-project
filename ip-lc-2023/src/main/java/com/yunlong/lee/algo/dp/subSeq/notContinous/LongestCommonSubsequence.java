package com.yunlong.lee.algo.dp.subSeq.notContinous;

/**
 * @author lijie
 * @version 1.0
 * @description 1143. 最长公共子序列
 * @date 6/5/23 7:16 下午
 * @link https://leetcode.cn/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        return doLongestCommonSubsequence(text1, text2);
    }

    private int doLongestCommonSubsequence(String text1, String text2) {
        //dp
        //1.dp[i][j],t1[0:i]与t2[0:j],最长的公共子序列长度
        //2.转移方程,dp[i][j] = t1[i] == t2[j]?dp[i-1][j-1]+1:
        // max(dp[i-1][j],dp[i][j-1])
        //3.遍历顺序,小到大
        //4.初始化,当i=0,t1[0,0]为空串,不论j如何取值,公共串都为空,dp[0][j] = 0,同样dp[i][0] = 0,
        //5.举例验证
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dpArr = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i <= len1; i++) {
            dpArr[i][0] = 0;
        }
        for (int j = 0; j <= len2; j++) {
            dpArr[0][j] = 0;
        }

        for (int i = 1; i <= len1; i++) {
            char iChar = text1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char jChar = text2.charAt(j - 1);
                if (iChar == jChar) {
                    dpArr[i][j] = dpArr[i - 1][j - 1] + 1;
                } else {
                    dpArr[i][j] = Math.max(dpArr[i][j - 1], dpArr[i - 1][j]);
                }
            }
        }
        return dpArr[len1][len2];
    }

    public static void main(String[] args) {
        String s1 = "mhunuzqrkzsnidwbun";
        String s2 = "szulspmhwpazoxijwbq";
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence(s1, s2));
    }
}
