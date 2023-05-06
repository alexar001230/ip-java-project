package com.yunlong.lee.algo.backtrack.permutation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 567. 字符串的排列
 * @date 6/5/23 3:03 下午
 * @link https://leetcode.cn/problems/permutation-in-string/
 */
public class PermutationInStr {
    public boolean checkInclusion(String s1, String s2) {
        return doCheckInclusionBySlideWin(s1, s2);
    }


    //region 滑窗做法
    private boolean doCheckInclusionBySlideWin(String s1, String s2) {
        if(s2.length() < s1.length()){
            return false;
        }
        HashMap<Character, Integer> s1Char2TimesMap = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            s1Char2TimesMap.put(s1.charAt(i),
                    s1Char2TimesMap.getOrDefault(s1.charAt(i), 0) + 1);
        }

        HashMap<Character, Integer> s2Char2TimesMap = new HashMap<>();
        int windowSize = s1.length();
        for (int i = 0; i < s2.length(); ) {
            int left = i;
            int right = i + windowSize;
            if (i == 0) {
                while (left < right) {
                    s2Char2TimesMap.put(s2.charAt(left),
                            s2Char2TimesMap.getOrDefault(s2.charAt(left), 0) + 1);
                    left++;
                }
            }
            if (s1Char2TimesMap.equals(s2Char2TimesMap)) {
                return true;
            }
            int iCharCnt = s2Char2TimesMap.get(s2.charAt(i));
            if (iCharCnt == 1) {
                s2Char2TimesMap.remove(s2.charAt(i));
            } else {
                s2Char2TimesMap.put(s2.charAt(i), iCharCnt - 1);
            }
            if (i + windowSize <= s2.length() - 1) {
                s2Char2TimesMap.put(s2.charAt(i + windowSize),
                        s2Char2TimesMap.getOrDefault(s2.charAt(i + windowSize), 0) + 1);
            }
            i++;
        }
        return false;
    }
    //endregion

    //region 回溯超内存
    private boolean doCheckInclusion(String s1, String s2) {
        idxUsedArr = new boolean[s1.length()];
        Arrays.fill(idxUsedArr, false);
        backTracking(s1);
        for (String aStr : s1Permutations) {
            if (s2.contains(aStr)) {
                return true;
            }
        }
        return false;
    }

    private LinkedList<String> s1Permutations = new LinkedList<>();
    private StringBuilder aPath = new StringBuilder();
    private boolean[] idxUsedArr;


    private void backTracking(String s) {
        if (aPath.length() == s.length()) {
            s1Permutations.add(aPath.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (idxUsedArr[i]) {
                continue;
            }
            aPath.append(s.charAt(i));
            idxUsedArr[i] = true;
            backTracking(s);
            aPath.deleteCharAt(aPath.length() - 1);
            idxUsedArr[i] = false;
        }
    }
    //endregion

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(new PermutationInStr().checkInclusion(s1, s2));
    }
}
