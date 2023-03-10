package com.yunlong.lee.dataStructure.str;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 8/2/23 2:06 下午
 * @link https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubStr {

    public static void main(String[] args) {
        String str = "bba";
        System.out.println(lengthOfLongestSubstring(str));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] sArr = s.toCharArray();
        int maxLen = 1;
        // int[] resultIdx = new int[2];
        for (int i = 0; i < sArr.length; i++) {
            Map char2IdxMap = new HashMap<Character, Integer>();
            char2IdxMap.put(sArr[i], i);
            for (int j = i + 1; j <= sArr.length - 1; j++) {
                if (Objects.isNull(char2IdxMap.get(sArr[j]))) {
                    char2IdxMap.put(sArr[j], j);
                    if (maxLen <= (j - i)) {
                        maxLen = j - i + 1;
                        // resultIdx[0] = i;
                        // resultIdx[1] = j;
                    }
                } else {
                    break;
                }
            }
        }
        return maxLen;
        // String resultStr = "";
        // for (int i = resultIdx[0]; i < resultIdx[1]; i++) {
        //     resultStr += sArr[i];
        // }
        // return resultStr;
    }
}
