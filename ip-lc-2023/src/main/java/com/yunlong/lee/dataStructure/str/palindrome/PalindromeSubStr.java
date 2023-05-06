package com.yunlong.lee.dataStructure.str.palindrome;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 647. 回文子串
 * @date 5/5/23 3:26 下午
 * @link https://leetcode.cn/problems/palindromic-substrings/
 */
public class PalindromeSubStr {
    public int countSubstrings(String s) {
        return doCountSubstringsByStr(s);
    }

    //region
    private int doCountSubstringsByStr(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        int cnt = 0;
        for (int windowSize = 1; windowSize <= s.length(); windowSize++) {
            int i = 0;
            int j = i + windowSize;
            while (j < s.length() + 1) {
                String aPalindrome = s.substring(i, j);
                String reverse =
                        new StringBuilder(aPalindrome).reverse().toString();
                if (aPalindrome.equals(reverse)) {
                    cnt++;
                }
                i++;
                j++;
            }
        }
        return cnt;
    }
    //endregion

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(new PalindromeSubStr().countSubstrings(s));
    }
}
