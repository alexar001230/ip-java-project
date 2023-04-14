package com.yunlong.lee.dataStructure.stackAndQ;

import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 232. 用栈实现队列
 * @date 13/4/23 9:50 上午
 * @link https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class StackMockQueue {
    //进队栈
    private Stack<String> PushStack = new Stack<>();
    //出队栈
    private Stack<String> PopStack = new Stack<>();

    public StackMockQueue() {

    }

    public void push(int x) {
        while (!PopStack.isEmpty()) {
            PushStack.push(PopStack.pop());
        }
        PushStack.push(String.valueOf(x));
        return;
    }

    public int pop() {
        if (empty()) {
            return -1;
        }
        while (!PushStack.isEmpty()) {
            PopStack.push(PushStack.pop());
        }
        return Integer.parseInt(PopStack.pop());
    }

    public int peek() {
        while (!PushStack.isEmpty()) {
            PopStack.push(PushStack.pop());
        }
        Integer toPeek = Integer.parseInt(PopStack.pop());
        PopStack.push(String.valueOf(toPeek));
        return toPeek;
    }

    public boolean empty() {
        return PopStack.empty() && PushStack.empty();
    }

}
