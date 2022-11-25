package com.yunlonglee.str;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description 3. 无重复字符的最长子串
 * @date 17/2/22 6:33 上午
 */
public class LengthOfLongestSubString {
    /**
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int result = 1;
        char[] charArr = s.toCharArray();
        int i = 0;
        while (i < charArr.length) {
//            if (charArr.length - 1 - i <= result) {
//                break;
//            }
            int j = i;
            Map<Character, Integer> char2NumsMap = new HashMap<>();
            while (j < charArr.length) {
                if (null == char2NumsMap.get(charArr[j])) {
                    char2NumsMap.put(charArr[j], 1);
                    j++;
                } else {
                    result = Math.max(result, j - i);
                    break;
                }
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
