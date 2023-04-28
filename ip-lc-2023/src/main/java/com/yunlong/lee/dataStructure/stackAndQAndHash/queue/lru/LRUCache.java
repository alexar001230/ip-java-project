package com.yunlong.lee.dataStructure.stackAndQAndHash.queue.lru;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 146. LRU 缓存
 * @date 24/4/23 10:42 上午
 * @link https://leetcode.cn/problems/lru-cache/
 */
public class LRUCache {
    class DLinkNode {
        DLinkNode pre;
        DLinkNode post;
        int val;
        int key;


        public DLinkNode(int key, int val) {
            this.val = val;
            this.key = key;
        }

        public DLinkNode() {

        }


    }

    private DLinkNode dummyHead = new DLinkNode(Integer.MIN_VALUE,
            Integer.MIN_VALUE);
    private DLinkNode dummyTail = new DLinkNode(Integer.MAX_VALUE,
            Integer.MAX_VALUE);

    private int size;
    private int capacity;


    private DLinkNode dLinkNode;
    private HashMap<Integer, DLinkNode> key2NodeCacheMap = new HashMap<>();


    public LRUCache(int capacity) {
        this.dLinkNode = new DLinkNode();
        dummyHead.post = dummyTail;
        dummyTail.pre = dummyHead;
        this.capacity = capacity;
    }

    public int get(int key) {
        DLinkNode node = key2NodeCacheMap.get(key);
        if (Objects.isNull(node)) {
            return -1;
        } else {
            moveNode2Head(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        DLinkNode node = key2NodeCacheMap.get(key);
        if (Objects.nonNull(node)) {
            node.val = value;
            moveNode2Head(node);
            return;
        } else {
            DLinkNode aNew = new DLinkNode(key, value);
            // if (this.size == this.capacity) {
            //     removeNode(this.dummyTail.pre);
            // }
            add2Head(aNew);
            key2NodeCacheMap.put(key, aNew);
        }
    }

    private void add2Head(DLinkNode toAdd) {
        DLinkNode origin = this.dummyHead.post;
        toAdd.pre = this.dummyHead;
        this.dummyHead.post = toAdd;
        toAdd.post = origin;
        origin.pre = toAdd;
        this.size++;
        if (this.size > capacity) {
            key2NodeCacheMap.remove(dummyTail.pre.key);
            removeNode(dummyTail.pre);
        }
    }


    private void removeNode(DLinkNode toRemove) {
        DLinkNode pre = toRemove.pre;
        if (toRemove.equals(dummyTail.pre)) {
            pre.post = dummyTail;
            dummyTail.pre = pre;
        } else {
            DLinkNode post = toRemove.post;
            pre.post = toRemove.post;
            post.pre = toRemove.pre;
        }
        this.size--;
    }

    private void moveNode2Head(DLinkNode toHead) {
        // toHead.pre = this.dummyHead;
        // this.dummyHead.post = toHead;
        // this.size++;
        removeNode(toHead);
        add2Head(toHead);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        System.out.println("null");
        lruCache.put(1, 1);
        System.out.println("null");
        lruCache.put(2, 2);
        System.out.println("null");
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println("null");
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println("null");
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
