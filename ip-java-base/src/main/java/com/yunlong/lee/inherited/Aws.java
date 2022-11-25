package com.yunlong.lee.inherited;

import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 15/3/22 11:57 上午
 */
public class Aws {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ']') {
                char aChar = stack.peek();
                if (aChar == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (chars[i] == '}') {
                char aChar = stack.peek();
                if (aChar == '{') {
                    stack.pop();
                } else {
                    return false;
                }

            } else if (chars[i] == ']') {
                char aChar = stack.peek();
                if (aChar == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }
}
