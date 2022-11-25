package com.yunlonglee.stackAndQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 155. 最小栈
 * @date 13/2/22 11:13 下午
 */
public class MinStack {
    private volatile int minVal = Integer.MAX_VALUE;
    private List<Integer> intList = new LinkedList<>();

    public MinStack() {

    }

    public void push(int val) {
        if (minVal > val) {
            minVal = val;
        }
        intList.add(val);
    }

    public void pop() {
        intList.remove(intList.size() - 1);
        int min = Integer.MAX_VALUE;
        if (intList.size() > 0) {
            for (int i : intList) {
                if (min > i) {
                    min = i;
                }
            }
        }
        minVal = min;
    }

    public int top() {
        return intList.get(intList.size() - 1);
    }

    public int getMin() {
        return minVal;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }
}
