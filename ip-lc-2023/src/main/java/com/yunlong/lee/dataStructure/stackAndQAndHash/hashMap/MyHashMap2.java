package com.yunlong.lee.dataStructure.stackAndQAndHash.hashMap;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 706. 设计哈希映射
 * @date 10/3/23 7:36 下午
 * @link https://leetcode.cn/problems/design-hashmap/
 */
public class MyHashMap2 {
    private Node[] tab;
    private int capacity;

    class Node {
        int key;
        int val;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    public MyHashMap2() {
        capacity = 767;
        tab = new Node[capacity];
    }

    public void put(int key, int value) {
        int idx = k2Idx(key);
        Node cursorNode = tab[idx];
        if (Objects.isNull(cursorNode)) {
            tab[idx] = new Node(key, value);
            return;
        } else {
            while (Objects.nonNull(cursorNode)) {
                //说明已经存在了,直接覆盖
                if (cursorNode.key == key) {
                    cursorNode.val = value;
                    return;
                }
                if (Objects.isNull(cursorNode.next)) {
                    Node newNode = new Node(key, value);
                    cursorNode.next = newNode;
                }
                cursorNode = cursorNode.next;
            }
        }
        return;
    }

    public int get(int key) {
        int idx = k2Idx(key);
        Node cursorNode = tab[idx];
        if (Objects.isNull(cursorNode)) {
            return -1;
        } else {
            while (Objects.nonNull(cursorNode)) {
                if (cursorNode.key == key) {
                    return cursorNode.val;
                }
                cursorNode = cursorNode.next;
            }
        }
        return -1;
    }

    public void remove(int key) {
        int idx = k2Idx(key);
        Node cursorNode = tab[idx];
        if (Objects.isNull(cursorNode)) {
            return;
        } else {
            Node dummyHead = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
            dummyHead.next = cursorNode;
            Node prev = dummyHead;
            while (Objects.nonNull(cursorNode)) {
                if (cursorNode.key == key) {
                    prev.next = cursorNode.next;
                } else {
                    prev = cursorNode;
                }
                cursorNode = cursorNode.next;
            }
            tab[idx] = dummyHead.next;
        }
        return;
    }

    private int k2Idx(int key) {
        int hash = String.valueOf(key).hashCode();
        return hash % capacity;
    }

    public static void main(String[] args) {
        MyHashMap2 myHashMap = new MyHashMap2();
        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
        myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
        myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
    }

}
