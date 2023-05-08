package com.yunlong.lee.dataStructure.str.palindrome;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 409. 最长回文串
 * @date 6/5/23 5:18 下午
 * @link https://leetcode.cn/problems/longest-palindrome/
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        return doLongestPalindrome(s);
    }

    private int doLongestPalindrome(String s) {
        HashMap<Character, Integer> char2CntMap = new HashMap<>();
        HashMap<Character, Integer> mirrorMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char aChar = s.charAt(i);
            Integer cnt = char2CntMap.get(aChar);
            if (Objects.isNull(cnt)) {
                char2CntMap.put(aChar, 1);
            } else {
                char2CntMap.put(aChar, cnt + 1);
            }
        }
        mirrorMap.putAll(char2CntMap);

        int len = 0;
        for (Map.Entry<Character, Integer> aEntry : char2CntMap.entrySet()) {
            char aChar = aEntry.getKey();
            int cnt = aEntry.getValue();
            if (cnt % 2 == 0) {
                mirrorMap.remove(aChar);
                len = len + cnt;
            } else {
                len = len + cnt - 1;
                mirrorMap.put(aChar, cnt % 2);
            }
        }
        if (mirrorMap.isEmpty()) {
            return len;
        } else {
            return len + 1;
        }
    }

    public static void main(String[] args) {
        String s = "abccccdd";
        System.out.println(new LongestPalindrome().longestPalindrome(s));
    }
}
