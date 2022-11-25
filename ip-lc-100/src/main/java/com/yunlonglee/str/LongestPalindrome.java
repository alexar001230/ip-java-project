package com.yunlonglee.str;

import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 5. 最长回文子串
 * @date 13/3/22 2:43 上午
 */
public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        //1.dp[i][j] 表示i到j是否为回文串
        //2.转移方程,dp[i][j] = dp[i+1][j-1]&&s[i]==s[j]
        //3.遍历顺序,i到j遍历
        //4.初始化  dp[i][i]=false,i>j dp[i][j] = false
        //5.举例推导
        int len = s.length();
        if (len < 2) {
            return s;
        }
        //dp[i][j]表示s[i...j]是否是回文串
        boolean[][] dp = new boolean[len][len];
        //初始化:所有长度为1的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = false;
        }

        int maxLen = 1;
        int begin = 0;

        char[] chars = s.toCharArray();
        //递推开始
        //先枚举字串的长度
        for (int l = 2; l <= len; l++) {
            //枚举左边界,左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                //由l和i可以确定右边界，l=j-i+1;
                //l=j-i+1;
                int j = l + i - 1;
                //右边界越界，可以退出当前循环
                if (j >= len) {
                    break;
                }
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }

                }
                //只要dp[i][j]==true,就表示s[i...j]是回文,此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }

        }
        return s.substring(begin, begin + maxLen);
    }


    public static String longestPalindrome1(String s) {
        //找所有的回文字串
        //取最长
        if (s.length() == 1) {
            return s;
        }
        int maxLength = 0;
        String result = "";
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subI2JStr = s.substring(i, j);
                if (maxLength < (j - i)) {
                    if (isPalindrome(subI2JStr)) {
                        maxLength = subI2JStr.length();
                        result = subI2JStr;
                    }
                }
            }
        }
        return result;
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
        String s = "azwdzwmwcqzgcobeeiphemqbjtxzwkhiqpbrprocbppbxrnsxnwgikiaqutwpftbiinlnpyqstkiqzbggcsdzzjbrkfmhgtnbujzszxsycmvipjtktpebaafycngqasbbhxaeawwmkjcziybxowkaibqnndcjbsoehtamhspnidjylyisiaewmypfyiqtwlmejkpzlieolfdjnxntonnzfgcqlcfpoxcwqctalwrgwhvqvtrpwemxhirpgizjffqgntsmvzldpjfijdncexbwtxnmbnoykxshkqbounzrewkpqjxocvaufnhunsmsazgibxedtopnccriwcfzeomsrrangufkjfzipkmwfbmkarnyyrgdsooosgqlkzvorrrsaveuoxjeajvbdpgxlcrtqomliphnlehgrzgwujogxteyulphhuhwyoyvcxqatfkboahfqhjgujcaapoyqtsdqfwnijlkknuralezqmcryvkankszmzpgqutojoyzsnyfwsyeqqzrlhzbc";
//        String s = "babad";
        longestPalindrome(s);
    }
}
