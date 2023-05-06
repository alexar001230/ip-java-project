package com.yunlong.lee.algo.backtrack.split;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 131. 分割回文串
 * @date 6/5/23 2:29 下午
 * @link https://leetcode.cn/problems/palindrome-partitioning/
 */
public class PalindromePartition {
    public List<List<String>> partition(String s) {
        return doPartition(s);
    }

    private List<List<String>> doPartition(String s) {
        backTracking(s, 0);
        return res;
    }

    private LinkedList<List<String>> res = new LinkedList<>();
    private LinkedList<String> aValidPath = new LinkedList<>();

    private void backTracking(String s, int startIdx) {
        if (startIdx >= s.length()) {
            res.add(new LinkedList<>(aValidPath));
            return;
        }
        for (int i = startIdx; i < s.length(); i++) {
            if (isValidPalindrome(s.substring(startIdx, i + 1))) {
                String aStr = s.substring(startIdx, i + 1);
                aValidPath.push(aStr);
            } else {
                continue;
            }
            backTracking(s, i + 1);
            aValidPath.pop();
        }
    }

    private boolean isValidPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static void main(String[] args) {
        String s = "cdd";
        System.out.println(new PalindromePartition().partition(s));
    }
}
