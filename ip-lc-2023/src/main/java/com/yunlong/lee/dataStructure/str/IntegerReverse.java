package com.yunlong.lee.dataStructure.str;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 7. 整数反转
 * @date 20/4/23 8:57 下午
 * @link https://leetcode.cn/problems/reverse-integer/
 */
public class IntegerReverse {
    public int reverse(int x) {
        return doReverse(x);
    }

    private Deque<Character> charStack = new ArrayDeque<>();

    private int doReverse(int x) {
        if (x == 0) {
            return 0;
        }

        String xStr = String.valueOf(x);
        char sign = '-';
        boolean isPositive = true;
        for (int i = 0; i < xStr.length(); i++) {
            if (xStr.charAt(i) == sign) {
                isPositive = false;
                continue;
            }
            charStack.push(xStr.charAt(i));
        }
        StringBuilder reverseSb = new StringBuilder();
        while (!charStack.isEmpty()) {
            char aChar = charStack.pop();
            if (aChar == '0' && reverseSb.length() == 0) {
                continue;
            }
            reverseSb.append(aChar);
        }
        String xReverseStr = reverseSb.toString();
        if (!isPositive) {
            xReverseStr = "-" + xReverseStr;
        }
        long xLong = Long.parseLong(xReverseStr);
        if (xLong > Integer.MAX_VALUE || xLong < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) xLong;
    }


}
