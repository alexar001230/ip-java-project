package com.yunlong.lee.dataStructure.str.subStr;

/**
 * @author lijie
 * @version 1.0
 * @description 392. 判断子序列
 * @date 1/5/23 12:33 下午
 * @link https://leetcode.cn/problems/is-subsequence/
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        return doIsSubsequence(s, t);
    }

    private boolean doIsSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int i = 0;
        int j = 0;
        for (; i < sLen && j < tLen; ) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(j);
            if (tChar != sChar) {
                j++;
            } else {
                i++;
                j++;
            }
        }
        return i == sLen && j <= tLen;
    }

    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(new IsSubsequence().isSubsequence(s, t));
    }
}
