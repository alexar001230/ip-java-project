package com.yunlong.lee.dataStructure.stackAndQAndHash.queue;

import com.yunlong.lee.utils.ds.DSUtils;

import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 225. 用队列实现栈
 * @date 28/4/23 3:20 下午
 * @link https://leetcode.cn/problems/implement-stack-using-queues/description/
 */
public class QueueMockStack {

    private LinkedList<Integer> pushQ;
    private LinkedList<Integer> popQ;


    public QueueMockStack() {
        pushQ = new LinkedList<>();
        popQ = new LinkedList<>();
    }

    public void push(int x) {
        if (empty()) {
            pushQ.offer(x);
        } else {
            while (!popQ.isEmpty()) {
                pushQ.offer(popQ.pollLast());
            }
            pushQ.offer(x);
        }
    }

    public int pop() {
        if (popQ.isEmpty()) {
            while (!pushQ.isEmpty()) {
                popQ.offer(pushQ.pollLast());
            }
        }
        return popQ.poll();
    }

    public int top() {
        int top = pop();
        push(top);
        return top;
    }

    public boolean empty() {
        return pushQ.isEmpty() && popQ.isEmpty();
    }

    public static void main(String[] args) {
        String[] opArr = {"MyStack", "push", "pop", "empty"};
        String params = "[[],[1],[],[]]";
        DSUtils.printResByOperatesAndParams(QueueMockStack.class, opArr,
                params,null
        );
    }
}
