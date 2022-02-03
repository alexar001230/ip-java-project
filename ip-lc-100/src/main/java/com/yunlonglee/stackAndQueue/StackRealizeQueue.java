package com.yunlonglee.stackAndQueue;

import java.util.Stack;

import static com.yunlonglee.util.PrintUtils.print;

/**
 * @author lijie
 * @version 1.0
 * @description 用栈实现队列
 * 用两个栈输入栈+输出栈实现
 * @date 28/1/22 11:05 下午
 */
public class StackRealizeQueue {
    /**
     * 主要实现几个方法
     * push
     * pop
     * offer
     * top
     * isEmpty
     */
    private Stack<Integer> stackIn = new Stack<>();
    private Stack<Integer> stackOut = new Stack<>();


    public Void push(int x) {
        while (!stackOut.isEmpty()) {
            stackIn.push(stackOut.pop());
        }
        stackIn.push(x);
        return (Void) null;
    }

    public Integer pop() {
        if (stackIn.isEmpty() && stackOut.isEmpty()) {
            return null;
        }
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
        return stackOut.pop();
    }

    public int peek() {
        Integer topElement = this.pop();
        stackOut.push(topElement);
        return topElement;
    }


    public boolean empty() {
        if (stackIn.size() + stackOut.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        StackRealizeQueue stackRealizeQueue = new StackRealizeQueue();
        Void result1 = stackRealizeQueue.push(1);
        Void result2 = stackRealizeQueue.push(2);
        Void result3 = stackRealizeQueue.push(3);
        Void result4 = stackRealizeQueue.push(4);
        int resultPop1 = stackRealizeQueue.pop();
        Void result5 = stackRealizeQueue.push(5);
        int resultPop2 = stackRealizeQueue.pop();
        int resultPop3 = stackRealizeQueue.pop();
        int resultPop4 = stackRealizeQueue.pop();
        int resultPop5 = stackRealizeQueue.pop();
        print(result1, result2, result3, result4, resultPop1, result5, resultPop2,
                resultPop3, resultPop4, resultPop5);
    }



}
