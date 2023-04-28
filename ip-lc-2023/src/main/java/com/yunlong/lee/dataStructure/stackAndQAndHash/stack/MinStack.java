package com.yunlong.lee.dataStructure.stackAndQAndHash.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lijie
 * @version 1.0
 * @description 155. 最小栈
 * @date 27/4/23 7:47 下午
 * @link https://leetcode.cn/problems/min-stack/solution/zui-xiao-zhan-by-leetcode-solution/
 */
public class MinStack {
    private Deque<Integer> innerMinStack;
    private Deque<Integer> elementsStack;

    public MinStack() {
        innerMinStack = new ArrayDeque<>();
        elementsStack = new ArrayDeque<>();
    }

    public void push(int val) {
        if (elementsStack.isEmpty()) {
            innerMinStack.push(val);
        } else {
            if (val < innerMinStack.peek()) {
                innerMinStack.push(val);
            }else{
                innerMinStack.push(innerMinStack.peek());
            }
        }
        elementsStack.push(val);
    }

    public void pop() {
        elementsStack.pop();
        innerMinStack.pop();
    }

    public int top() {
        return elementsStack.peek();
    }

    public int getMin() {
        return innerMinStack.peek();
    }
}
