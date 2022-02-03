package com.yunlonglee.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

import static com.yunlonglee.util.PrintUtils.print;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 29/1/22 1:44 上午
 */
public class QueueRealizeStack {
    /**
     * 模拟压栈
     */
    private Queue<Integer> queuePush = new LinkedList<>();

    /**
     * 模拟弹栈
     */
    private Queue<Integer> queuePop = new LinkedList<>();


    public void push(int x) {
        while (!queuePop.isEmpty()) {
            queuePush.add(queuePop.poll());
        }
        queuePush.add(x);
    }

    public Integer pop() {
        int pushedSize = queuePush.size();
        if (pushedSize >= 1) {
            int i = 1;
            while (i <= pushedSize - 1) {
                queuePop.add(queuePush.poll());
                i++;
            }
            return queuePush.poll();
        } else {
            if (!queuePop.isEmpty()) {
                int i = 1;
                int popSize = queuePop.size();
                while (i <= popSize - 1) {
                    queuePush.add(queuePop.poll());
                    i++;
                }
                return queuePop.poll();
            }
        }
        return null;

    }

    public Integer top() {
        if (empty()) {
            return null;
        }
        Integer topElement = pop();
        queuePop.add(topElement);
        return topElement;
    }

    public boolean empty() {
        if (queuePop.size() == 0 && queuePush.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        QueueRealizeStack stack = new QueueRealizeStack();
        Boolean resultEmpty = stack.empty();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Integer result = stack.top();
        Integer resultPop = stack.pop();
        Integer result1 = stack.top();
        Integer resultPop1 = stack.pop();
        Integer result2 = stack.top();
        Boolean resultEmpty2 = stack.empty();
        Integer resultPop3 = stack.pop();
        boolean resultEmpty3 = stack.empty();
        print(result, resultPop, result1, resultPop1, result2, resultEmpty2,
                resultPop3, resultEmpty3);
    }
}
