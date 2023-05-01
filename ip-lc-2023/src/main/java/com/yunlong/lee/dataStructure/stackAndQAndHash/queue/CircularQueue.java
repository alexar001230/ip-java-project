package com.yunlong.lee.dataStructure.stackAndQAndHash.queue;

import com.yunlong.lee.utils.ds.DSUtils;

import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 622. 设计循环队列
 * @date 28/4/23 1:46 下午
 * @link https://leetcode.cn/problems/design-circular-queue/
 */
public class CircularQueue {
    private int capacity;
    private int size;
    private Node dummyHead;
    private Node dummyTail;

    class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public CircularQueue(int k) {
        capacity = k;
        dummyHead = new Node(Integer.MIN_VALUE);
        dummyTail = new Node(Integer.MAX_VALUE);
        dummyHead.next = dummyTail;
        dummyTail.next = dummyHead;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        } else {
            Node aNew = new Node(value);
            if (isEmpty()) {
                dummyHead.next = aNew;
                dummyTail = aNew;
            } else {
                dummyTail.next = aNew;
                dummyTail = dummyTail.next;
            }
            dummyTail.next = dummyHead;
            size++;
            return true;
        }
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        } else {
            Node toDel = dummyHead.next;
            dummyHead.next = toDel.next;
            size--;
            return true;
        }
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        } else {
            return dummyHead.next.val;
        }
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        } else {
            return dummyTail.val;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        String[] opArr = new String[]{"CircularQueue", "enQueue", "Rear", "Front", "deQueue", "Front", "deQueue", "Front", "enQueue", "enQueue", "enQueue", "enQueue"};
        String params = "[[3],[2],[],[],[],[],[],[],[4],[2],[2],[3]]";
        String nonParamOps = "isEmpty,isFull,Front,Rear,deQueue";
        DSUtils.printResByOperatesAndParams(CircularQueue.class, opArr,
                params,null);
    }
}
