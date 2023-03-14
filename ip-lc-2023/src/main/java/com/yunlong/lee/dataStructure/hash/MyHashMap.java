package com.yunlong.lee.dataStructure.hash;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/3/23 7:36 下午
 * @link
 */
public class MyHashMap {
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


    public MyHashMap() {

    }

    public void put(int key, int value) {
        int hash = String.valueOf(key).hashCode();
        if (null == tab) {
            tab = new Node[capacity];
        }
        int idx = hash & capacity;
        if (null == tab[idx]) {
            tab[idx] = new Node(key, value);
        } else {
            Node cur = tab[idx];
            while (null != cur.next) {
                //说明已经存在了,直接覆盖
                if (cur.key == key) {
                    cur.val = value;
                    return;
                }
                cur = cur.next;
            }
            Node newNode = new Node(key, value);
            cur.next = newNode;
            return;
        }
    }

    public int get(int key) {
        int hash = String.valueOf(key).hashCode();
        if (tab == null) {
            return -1;
        }
        Node head = tab[hash & capacity];
        while (head.next != null) {
            if (head.key == key) {
                return head.val;
            }
            head = head.next;
        }
        return -1;
    }

    public void remove(int key) {
        int hash = String.valueOf(key).hashCode();
        if (tab == null) {
            return;
        }
        Node head = tab[hash & capacity];
        Node prev = head;
        Node cur = head;
        while (cur.next != null) {
            if (cur.key == key) {
                prev.next = cur.next;
                cur.next = null;
                return;
            }
            cur = cur.next;
        }
        return;
    }

}
