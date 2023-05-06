package com.yunlong.lee.dataStructure.str.palindrome;

/**
 * @author lijie
 * @version 1.0
 * @description 9. 回文数
 * @date 4/4/23 5:35 下午
 * @link https://leetcode.cn/problems/palindrome-number/
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        return judgePalindromeByStr(x);
    }

    //region 通过字符串来做,不使用子串，需要翻转一半的数字和剩下的数字比对,相同则是回文数
    private boolean judgePalindromeByStr(int x) {
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
    //endregion

    public static void main(String[] args) {
        int x = -121;
        System.out.println(new PalindromeNumber().isPalindrome(x));
    }
}
