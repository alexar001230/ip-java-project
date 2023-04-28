package com.yunlong.lee.dataStructure.stackAndQAndHash;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 有效括号
 * @date 16/3/23 7:25 下午
 * @link https://leetcode.cn/problems/valid-parentheses/
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        if (null == s || s.length() == 0 || s.length() == 1) {
            return false;
        }
        List<Character> popStackChars = Arrays.asList(')', ']', '}');
        LinkedList stack = new LinkedList<Character>();
        char[] sArr = s.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            if (popStackChars.contains(sArr[i])) {
                if (null != stack.peek()) {
                    char top = (char) stack.peek();
                    boolean kuohaoMatch = sArr[i] == ')' && top == '(';
                    boolean midKuohaoMatch = sArr[i] == ']' && top == '[';
                    boolean bigKuohaoMatch = sArr[i] == '}' && top == '{';
                    if (kuohaoMatch || midKuohaoMatch || bigKuohaoMatch) {
                        stack.pop();
                        continue;
                    }
                }
            }
            stack.push(sArr[i]);
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = ")(";
        System.out.println(new ValidParentheses().isValid(s));
    }
}
