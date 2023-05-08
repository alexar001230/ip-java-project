package com.yunlong.lee.dataStructure.stackAndQAndHash.stack;

import java.util.Objects;
import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/3/23 12:09 下午
 * @link https://leetcode.cn/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {

    Character left = '(';
    Character right = ')';

    public int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == left) {
                stack.push(i);
            } else {
                stack.pop();
                //栈不空说明,仍然连续有效
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    //栈不空,说明不连续了，需要用max做比较记录，前边的遍历失效
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }


    public int myLongestValidParentheses(String s) {
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 2; j < s.length() + 1; j++) {
                String subStr = s.substring(i, j);
                Record record = isValidAndMaxSize(subStr);
                if (record.valid) {
                    max = Math.max(max, record.max);
                }
            }
        }
        return max;
    }

    class Record {
        boolean valid;
        int max;

        public Record(boolean valid, int max) {
            this.valid = valid;
            this.max = max;
        }
    }

    private Record isValidAndMaxSize(String subStr) {
        Stack<Character> stack = new Stack<>();

        Record record = new Record(false, 0);
        int result = 0;

        for (int i = 0; i < subStr.length(); i++) {
            if (!stack.empty()) {
                Character element = stack.peek();
                if (element.equals(left) && subStr.charAt(i) == right) {
                    result += 2;
                    stack.pop();
                    continue;
                }
            }
            stack.push(subStr.charAt(i));
        }
        if (stack.isEmpty()) {
            record.max = result;
            record.valid = true;
        }
        return record;
    }


    public static void main(String[] args) {
        String s = "(()";
        System.out.println(new LongestValidParentheses().longestValidParentheses(s));
    }
}


