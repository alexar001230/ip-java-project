package com.yunlonglee.dataStructure;

import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 150. 逆波兰表达式求值
 * @notice 逆波兰表达式:后缀表达式,将运算符写在操作数之后
 * @date 9/3/22 1:39 上午
 */
public class EvalRPN {
    /**
     * 思路:1.用栈存放数字
     *      2.碰到操作符号，数字栈弹栈计算
     *      3.注意-和/两个操作的left和right取值
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<String> numStack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String str = tokens[i];
            if (str.equals("+")) {
                int left = Integer.parseInt(numStack.pop());
                int right = Integer.parseInt(numStack.pop());
                int result = left + right;
                numStack.push(String.valueOf(result));
            } else if (str.equals("-")) {
                int right = Integer.parseInt(numStack.pop());
                int left = Integer.parseInt(numStack.pop());
                int result = left - right;
                numStack.push(String.valueOf(result));
            } else if (str.equals("/")) {
                int right = Integer.parseInt(numStack.pop());
                int left = Integer.parseInt(numStack.pop());
                int result = left / right;
                numStack.push(String.valueOf(result));
            } else if (str.equals("*")) {
                int left = Integer.parseInt(numStack.pop());
                int right = Integer.parseInt(numStack.pop());
                int result = left * right;
                numStack.push(String.valueOf(result));
            } else {
                numStack.push(str);
            }
        }
        return Integer.parseInt(numStack.pop());
    }
}
