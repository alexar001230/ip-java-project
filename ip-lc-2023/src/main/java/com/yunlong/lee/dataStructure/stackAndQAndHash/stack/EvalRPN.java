package com.yunlong.lee.dataStructure.stackAndQAndHash.stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 150. 逆波兰表达式求值
 * @date 6/5/23 4:56 下午
 * @link https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/
 */
public class EvalRPN {

    private LinkedList<String> dataStack = new LinkedList<>();

    private HashSet<String> fuhaoSet =
            new HashSet<>(Arrays.asList("+", "-", "*", "/"));

    public int evalRPN(String[] tokens) {
        return doEvalRPN(tokens);
    }

    private int doEvalRPN(String[] tokens) {
        for (String aStr : tokens) {
            if (!fuhaoSet.contains(aStr)) {
                dataStack.push(aStr);
            } else {
                int rNo = Integer.parseInt(dataStack.pop());
                int lNo = Integer.parseInt(dataStack.pop());
                if (aStr.endsWith("+")) {
                    dataStack.push(String.valueOf(lNo + rNo));
                }
                if (aStr.endsWith("-")) {
                    dataStack.push(String.valueOf(lNo - rNo));
                }
                if (aStr.endsWith("*")) {
                    dataStack.push(String.valueOf(lNo * rNo));
                }
                if (aStr.endsWith("/")) {
                    dataStack.push(String.valueOf(lNo / rNo));
                }
            }
        }
        return Integer.parseInt(dataStack.pop());
    }
}
