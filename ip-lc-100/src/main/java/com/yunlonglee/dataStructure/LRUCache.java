package com.yunlonglee.dataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 146. LRU 缓存
 * @date 13/2/22 11:52 下午
 */
public class LRUCache {
    private HashMap<Integer, DLinkNode> cacheMap = new HashMap<>();
    private int size = 0;
    private int capacity = 0;
    public DLinkNode head;
    public DLinkNode tail;

    class DLinkNode {
        public int key;
        public int value;
        public DLinkNode pre;
        public DLinkNode next;

        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public DLinkNode() {
        }
    }

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkNode dLinkNode = cacheMap.get(key);
        if (null != dLinkNode) {
            move2Head(dLinkNode);
            return dLinkNode.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        DLinkNode node = cacheMap.get(key);
        if (null == node) {
            DLinkNode newNode = new DLinkNode(key, value);
            cacheMap.put(key, newNode);
            add2Head(newNode);
            size++;
            if (size > this.capacity) {
                DLinkNode tail = removeTail();
                cacheMap.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            move2Head(node);
        }
    }

    private void add2Head(DLinkNode newNode) {
        newNode.pre = this.head;
        newNode.next = this.head.next;
        this.head.next.pre = newNode;
        this.head.next = newNode;
    }

    private void removeNode(DLinkNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void move2Head(DLinkNode node) {
        removeNode(node);
        add2Head(node);
    }

    private DLinkNode removeTail() {
        DLinkNode res = tail.pre;
        removeNode(res);
        return res;
    }



    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}
