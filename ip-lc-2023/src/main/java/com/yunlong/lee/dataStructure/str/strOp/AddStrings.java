package com.yunlong.lee.dataStructure.str.strOp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lijie
 * @version 1.0
 * @description 415. 字符串相加
 * @date 19/4/23 6:39 下午
 * @link https://leetcode.cn/problems/add-strings/
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        return doAddStrings(num1,num2);
    }

    private String doAddStrings(String num1, String num2) {
        Deque<Character> num1CharStack = str2Stack(num1);
        Deque<Character> num2CharStack = str2Stack(num2);

        int carry = 0;
        StringBuilder resSb = new StringBuilder();
        while (!num1CharStack.isEmpty() && !num2CharStack.isEmpty()) {
            int aSum = num1CharStack.pop() - '0' + num2CharStack.pop() - '0' + carry;
            carry = aSum / 10;
            resSb.append(aSum % 10);
        }

        while (!num1CharStack.isEmpty()) {
            int aSum = num1CharStack.pop() - '0' + carry;
            carry = aSum / 10;
            resSb.append(aSum % 10);
        }

        while (!num2CharStack.isEmpty()) {
            int aSum = num2CharStack.pop() - '0' + carry;
            carry = aSum / 10;
            resSb.append(aSum % 10);
        }

        if (carry > 0) {
            resSb.append(carry);
        }
        return resSb.reverse().toString();
    }

    private Deque<Character> str2Stack(String numStr) {
        int n = numStr.length();
        Deque<Character> charStack = new ArrayDeque<>();
        int i = 0;
        while (i < n) {
            charStack.push(numStr.charAt(i++));
        }
        return charStack;
    }
}
