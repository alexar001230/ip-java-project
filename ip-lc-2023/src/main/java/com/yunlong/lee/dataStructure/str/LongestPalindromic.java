package com.yunlong.lee.dataStructure.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description 最长回文子串
 * @date 16/3/23 2:21 下午
 * @link https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class LongestPalindromic {
    private Map<Integer, Integer> maxLen2IdxMap = new HashMap<>();
    private int maxLen = 1;
    private int curMaxLen = 1;
    private int begin = 0;

    //二维动态规划
    public String longestPalindrome(String s) {
        //dp5
        //3.循环遍历  滑窗遍历,i到j子串，写满dp[i][j]数组,
        //4.初始化 1.sArr[i][i] = true,2.sArr[i][j] = true,j-i<3情况
        //5.举例验证
        if(null == s){
            return s;
        }
        int len = s.length();
        if(len < 2){
            return s;
        }
        //1.确定dp[i][j]含义,s下标i到j是否为回文串，如果Sij为回文串,那么Si+1j-1也为回文串,所以用二维动态规划
        boolean[][] dp = new boolean[len][len];////初始化
        //2.转移方程,dp[i][j] = dp[i+1][j-1] && sArr[i] == sArr[j]
        char[] charArr = s.toCharArray();
        for (int windowSize = 2; windowSize <= charArr.length; windowSize++) {
            for (int i = 0; i < len; i++) {
                int j = windowSize + i - 1;//根左边界退出右边界
                if (j >= len) {
                    break;
                }
                if (charArr[i] == charArr[j]) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j] == true && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    /**
     * 三重循环，超出时间限制，题解用的动态规划
     *
     * @param s
     * @return
     */
    public String myLongestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return s;
        }
        if (s.equals(reverseStr(s))) {
            return s;
        }
        int windowSize = s.length() - 1;
        for (int i = windowSize; i >= 0; i--) {
            int curEndIdx = i;
            for (int j = 0; j < curEndIdx; ) {
                String normal = s.substring(j, curEndIdx);
                String reverse = reverseStr(normal);
                if (normal.equals(reverse)) {
                    String res = s.substring(j, curEndIdx);
                    return res;
                }
                if (curEndIdx >= s.length()) {
                    break;
                }
                j++;
                curEndIdx++;
            }
        }
        return "";
    }

    private String reverseStr(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            res.append(s.charAt(i));
        }
        return res.toString();
    }


    public static void main(String[] args) {
        String s = "bb";
        System.out.println(new LongestPalindromic().longestPalindrome(s));
    }
}

