package com.yunlonglee.str;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 647. 回文子串
 * @date 13/3/22 3:43 上午
 */
public class CountSubstrings {
    public static int countSubstrings(String s) {
        //找所有的回文字串
        //取最长
        if (s.length() == 1) {
            return 1;
        }
        int maxLength = 0;
        String result = "";
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i <= s.length() - 1; i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subI2JStr = s.substring(i, j);
                if (isPalindrome(subI2JStr)) {
                    resultList.add(subI2JStr);
                }

            }
        }
        return resultList.size();
    }

    private static boolean isPalindrome(String str) {
        if (str.length() == 1) {
            return true;
        }
        int halfIndex;
        boolean lengthQiShu = false;
        if (str.length() % 2 != 0) {
            halfIndex = str.length() / 2;
            lengthQiShu = true;
        } else {
            halfIndex = str.length() / 2 - 1;
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < str.length()) {
            if (i <= halfIndex) {
                if (i == halfIndex && lengthQiShu) {

                } else {
                    stack.push(str.charAt(i));
                }

            } else {
                char aChar = stack.peek();
                if (aChar != str.charAt(i)) {
                    return false;
                } else {
                    stack.pop();
                }
            }
            i++;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(countSubstrings(s));
    }
}
