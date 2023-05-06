package com.yunlong.lee.algo.backtrack.permutation;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 22. 括号生成
 * @date 4/5/23 7:24 下午
 * @link https://leetcode.cn/problems/generate-parentheses/
 */
public class GenParentheses {
    public List<String> generateParenthesis(int n) {
        // return doGenerateParenthesis(n);
        // List<String> combinations = new ArrayList<String>();
        // generateAll(new char[2 * n], 0, combinations);
        // return combinations;
        // backTracking1("", n, n);
        // return allStrs;
        return doGenerateParenthesisByDP(n);
    }


    public List<String> doGenerateParenthesisByDP(int n) {
        //dp 5步
        //1.dpArr[i]含义, 由i组左右括号构成有效括号
        //2.转移方程,dpArr[i] = "("+dpArr[m]+")"+dpArr[i-m]
        //3.遍历顺序,双层循环
        //4.初始化
        //5.举例验证

        LinkedList<String>[] dpArr = new LinkedList[n + 1];
        dpArr[0] = new LinkedList<>(Arrays.asList(""));
        if (n <= 0) {
            return dpArr[0];
        }
        dpArr[1] = new LinkedList<>(Arrays.asList("()"));
        if (n == 1) {
            return dpArr[1];
        }
        dpArr[2] = new LinkedList<>(Arrays.asList("()()", "(())"));

        for (int i = 3; i < n + 1; i++) {
            LinkedList<String> aValidGroup = new LinkedList<>();
            for (int m = 0; m < i; m++) {
                LinkedList<String> mValid = dpArr[m];
                LinkedList<String> lValid = dpArr[i - m - 1];
                for (String aM : mValid) {
                    for (String al : lValid) {
                        aValidGroup.add("(" + aM + ")" + al);
                    }
                }
            }
            dpArr[i] = aValidGroup;
        }
        return dpArr[n];
    }


    boolean[] idxUsedArr;

    public List<String> doGenerateParenthesis(int n) {
        char[] charArr = new char[n * 2];
        idxUsedArr = new boolean[n * 2];
        for (int i = 0; i < n * 2; i++) {
            if (i < n) {
                charArr[i] = '(';
            } else {
                charArr[i] = ')';
            }
        }

        backTracking(charArr, n * 2);
        // for (String aStr : allStrs) {
        //     if (validSeq(aStr)) {
        //         validParentheses.add(aStr);
        //     }
        // }
        return allStrs;

    }

    LinkedList<String> allStrs = new LinkedList<>();
    HashMap<String, Boolean> str2ExistMap = new HashMap<>();
    LinkedList<String> validParentheses = new LinkedList<>();
    StringBuilder aStrSb = new StringBuilder();


    private boolean validSeq(String aStr) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < aStr.length(); i++) {
            if (aStr.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    private void backTracking1(String aStr, int left, int right) {
        if (left == 0 && right == 0
            // && validSeq(aStrSb.toString())
        ) {
            allStrs.add(aStr);
            return;
        }
        if (left == right) {
            backTracking1(aStr + "(", left - 1, right);
        } else if (left < right) {
            if (left > 0) {
                backTracking1(aStr + "(", left - 1, right);
            }
            // aStrSb.append(')');
            backTracking1(aStr + ")", left, right - 1);
        }
        // aStrSb.deleteCharAt(aStrSb.length() - 1);
    }


    //region 回溯递归中嵌套循环会超时，这里特点是左右括号可以随意取和给定数组任排区别,这里的每个元素的取值更少
    private void backTracking(char[] charArr, int k) {
        String targetStr = aStrSb.toString();
        if (targetStr.length() == k) {
            if (Objects.isNull(str2ExistMap.get(targetStr)) && validSeq(targetStr)) {
                allStrs.add(targetStr);
                str2ExistMap.put(targetStr, true);
            }
            return;
        }

        for (int i = 0; i < charArr.length; i++) {
            if (idxUsedArr[i]) {
                continue;
            }
            aStrSb.append(charArr[i]);
            idxUsedArr[i] = true;
            backTracking(charArr, k);
            String targetStr1 = aStrSb.toString();
            aStrSb = new StringBuilder(targetStr1.substring(0,
                    targetStr1.length() - 1));
            idxUsedArr[i] = false;
        }
    }
    //endregion


    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }


    public static void main(String[] args) {
        int n = 180;
        System.out.println(new GenParentheses().generateParenthesis(n));
    }
}
