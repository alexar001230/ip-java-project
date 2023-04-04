package com.yunlong.lee.dataStructure.str;

/**
 * @author lijie
 * @version 1.0
 * @description 判断回文数，进阶要求不能转成字符串
 * @date 4/4/23 5:35 下午
 * @link https://leetcode.cn/problems/palindrome-number/
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        return judgePalindrome(x);
    }

    private boolean judgePalindrome(int x) {
        String xStr = String.valueOf(x);
        char[] xChars = xStr.toCharArray();
        int i = 0, j = xChars.length - 1;
        while (i <= j) {
            if (xChars[i] != xChars[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        int x = -121;
        System.out.println(new PalindromeNumber().isPalindrome(x));
    }
}
