package com.yunlong.lee.dataStructure.stackAndQAndHash.queue;

import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 622. 设计循环队列
 * @date 27/4/23 8:07 下午
 * @link https://leetcode.cn/problems/design-circular-queue/
 */
public class MyCircularQueue {
    private int capacity;
    private int size;
    private LinkedList<Integer> elements;

    public MyCircularQueue(int k) {
        capacity = k;
        elements = new LinkedList<>();
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        } else {
            elements.offer(value);
            size++;
            return true;
        }
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        } else {
            elements.poll();
            size--;
            return true;
        }
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        } else {
            return elements.getFirst();
        }
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        } else {
            return elements.getLast();
        }
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

    public boolean isFull() {
        return elements.size() == capacity;
    }
}
