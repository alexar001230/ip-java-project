package com.yunlong.lee.dataStructure.str.matching;

/**
 * @author lijie
 * @version 1.0
 * @description 745. 前缀和后缀搜索
 * @date 6/5/23 4:28 下午
 * @link https://leetcode.cn/problems/prefix-and-suffix-search/
 */
public class WordFilter {
    private String[] filterWords;

    public WordFilter(String[] words) {
        filterWords = words;
    }

    public int f(String pref, String suff) {
        for (int i = filterWords.length - 1; i >= 0; i--) {
            String aWord = filterWords[i];
            if (aWord.startsWith(pref) && aWord.endsWith(suff)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
