package com.yunlong.lee.dataStructure.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author lijie
 * @version 1.0
 * @description 无重复最长子串
 * @date 6/4/23 10:32 上午
 * @link https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubStrWithoutRepeatChar {
    public int lengthOfLongestSubstring(String s) {
        return doLengthOfLongestSubstring(s);
    }


    //region 滑动窗口思路,不断扩大滑窗大小并且检验窗口中是否含有重复的字符
    private int doLengthOfLongestSubstring(String s) {
        int maxLen = 0;
        //记录无重复字符的窗口
        Set<Character> slideWindowSet = new HashSet<>();
        //左右双指针分别指向数组窗口的边界idx
        int left = 0;
        int right = -1;
        for (; left < s.length(); left++) {
            //碰到重复的字符，滑窗左边届右移删除重复串，重新计算
            if (left != 0) {
                slideWindowSet.remove(s.charAt(left - 1));
            }
            //没碰到重复的串时，不断扩大滑窗右边界
            for (; right + 1 < s.length() && !slideWindowSet.contains(s.charAt(right + 1)); right++) {
                slideWindowSet.add(s.charAt(right + 1));
            }
            //一次计算，记录最大的无重复子串长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    //endregion
    //region 借助map,双层循环计算最大无重复子串长度
    private int myDoLengthOfLongestSubstring(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        char[] sChars = s.toCharArray();
        int maxLen = 1;
        for (int i = 0; i < sChars.length; i++) {
            HashMap<Character, Integer> char2IdxOfIStartMap = new HashMap();
            int curMaxLenOfIStart = 0;
            for (int j = i; j < sChars.length; j++) {
                Integer aCharIdx = char2IdxOfIStartMap.get(sChars[j]);
                if (Objects.isNull(aCharIdx)) {
                    char2IdxOfIStartMap.put(sChars[j], j);
                    curMaxLenOfIStart++;
                } else {
                    curMaxLenOfIStart = j - i;
                    break;
                }
            }
            maxLen = Math.max(maxLen, curMaxLenOfIStart);
        }
        return maxLen;
    }
    //endregion

    public static void main(String[] args) {
        // String str = "bbbbb";
        String str = "abcabcbb";
        // String str = "pwwkew";
        System.out.println(new LongestSubStrWithoutRepeatChar().lengthOfLongestSubstring(str));
    }
}
