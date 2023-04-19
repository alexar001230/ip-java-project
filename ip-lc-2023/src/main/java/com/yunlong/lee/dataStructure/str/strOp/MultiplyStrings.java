package com.yunlong.lee.dataStructure.str.strOp;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 43. 字符串相乘
 * @date 19/4/23 6:53 下午
 * @link https://leetcode.cn/problems/multiply-strings/
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        return doMultiply(num1, num2);
    }

    private String doMultiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int n = num1.length();
        int m = num2.length();
        List<String> perDigitMultiplyRes = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            //每个数相乘的进位
            int carry = 0;
            StringBuilder aResSb = new StringBuilder();
            for (int j = m - 1; j >= 0; j--) {
                int aMultiRes =
                        (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry;
                carry = aMultiRes / 10;
                aResSb.append(aMultiRes % 10);
            }
            if (carry > 0) {
                aResSb.append(carry);
            }
            aResSb.reverse();
            for (int j = 1; j < n - i; j++) {
                aResSb.append(0);
            }
            perDigitMultiplyRes.add(aResSb.toString());
        }

        // System.out.println(JSON.toJSONString(perDigitMultiplyRes));
        String res = "0";
        for (String aStr : perDigitMultiplyRes) {
            res = addTwoString(res, aStr);
        }
        return res;
    }


    public String addTwoString(String num1, String num2) {
        return doAddStrings(num1, num2);
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

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(new MultiplyStrings().multiply(num1, num2));
    }
}
