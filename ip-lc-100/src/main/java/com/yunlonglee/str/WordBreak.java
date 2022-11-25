package com.yunlonglee.str;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 139. 单词拆分
 * @date 24/2/22 6:07 上午
 */
public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (null == s || s.length() == 0) {
            return false;
        }
        String result = "";
        for (String aStr : wordDict) {
            result = s.replace(aStr, "");
            s = result;
        }
        return s.length() == 0;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Lists.newArrayList("leet", "code");
        wordBreak(s, wordDict);
    }
}
