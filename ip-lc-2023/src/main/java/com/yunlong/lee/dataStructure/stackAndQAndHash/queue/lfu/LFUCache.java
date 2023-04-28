package com.yunlong.lee.dataStructure.stackAndQAndHash.queue.lfu;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 460. LFU 缓存
 * @date 28/4/23 4:18 下午
 * @link https://leetcode.cn/problems/lfu-cache/
 */
public class LFUCache {
    public int capacity;
    public int size;
    //维护key,value缓存数据信息map
    public HashMap<Integer, Integer> kvMap;
    //维护<次数,使用频率是freq的node链表> map
    public HashMap<Integer, DLinkNode> freq2RelatedNodesMap;
    public DLinkNode dummyHead;
    public DLinkNode dummyTail;
    //维护<key,对应Node> map
    public HashMap<Integer, DLinkNode> key2DLinkNodeMap;
    //维护最少使用的key
    public int minUsedKey = Integer.MAX_VALUE;


    class DLinkNode {
        public int key;
        public int value;
        public int freq;

        public DLinkNode pre;
        public DLinkNode post;

        public DLinkNode(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }


    public LFUCache(int capacity) {
        this.capacity = capacity;
        kvMap = new HashMap<>();
        freq2RelatedNodesMap = new HashMap<>();
        key2DLinkNodeMap = new HashMap<>();
        size = key2DLinkNodeMap.size();
    }

    public int get(int key) {
        Integer value = kvMap.get(key);
        if (Objects.isNull(value)) {
            return NOT_VALID;
        } else {
            addKeyValue(key, NOT_VALID);
            return value;
        }
    }

    public void refreshMinUsedKeyIfNeed(int key) {
        DLinkNode aNode = key2DLinkNodeMap.get(key);
        DLinkNode minUsedKeyNode = key2DLinkNodeMap.get(minUsedKey);
        if (Objects.nonNull(minUsedKeyNode)) {
            if (aNode.freq < minUsedKeyNode.freq) {
                minUsedKey = key;
            }
        } else {
            minUsedKey = 1;
        }
    }

    private void refreshKey2NodeMap(int key, int value, String op) {
        if (OP_REMOVE.equals(op)) {
            key2DLinkNodeMap.remove(key);
        } else {
            DLinkNode aNode = key2DLinkNodeMap.get(key);
            if (Objects.isNull(aNode)) {
                key2DLinkNodeMap.put(key, new DLinkNode(key, value, 1));
            } else {
                int curFreq = aNode.freq + 1;
                aNode.freq = curFreq;
                if (value != NOT_VALID) {
                    aNode.value = value;
                }
            }
        }

    }

    private void refreshFreq2NodesMap(int key, int value, String op) {
        if (OP_REMOVE.equals(op)) {
            DLinkNode aNode = key2DLinkNodeMap.get(key);
            DLinkNode curNodes = freq2RelatedNodesMap.get(aNode.freq);
            if (curNodes.post == curNodes) {
                freq2RelatedNodesMap.remove(aNode.freq);
            } else {
                aNode.pre.post = aNode.post;
                aNode.post.pre = aNode.pre;
                aNode.post = null;
                aNode.pre = null;
            }
        } else {
            DLinkNode aNode = key2DLinkNodeMap.get(key);
            if (Objects.isNull(aNode)) {

            } else {
                int curFreq = aNode.freq;
                DLinkNode curNodes = freq2RelatedNodesMap.get(curFreq);
                if (Objects.isNull(curNodes)) {
                    aNode.pre = aNode;
                    aNode.post = aNode;
                    freq2RelatedNodesMap.put(curFreq, aNode);
                } else {
                    DLinkNode tail = curNodes.pre;
                    aNode.post = curNodes;
                    aNode.pre = tail;
                    tail.post = curNodes;
                    curNodes.pre = aNode;
                }
            }
        }
    }

    private void refreshKvMap(int key, int value, String op) {
        if (OP_REMOVE.equals(op)) {
            kvMap.remove(key);
        } else {
            Integer oldValue = kvMap.get(key);
            if (Objects.isNull(oldValue)) {
                kvMap.put(key, value);
                size++;
            } else {
                kvMap.put(key, value);
            }
        }
    }


    private int NOT_VALID = -1;
    private String OP_REMOVE = "remove";
    private String OP_ADD = "add";

    private void removeMinUsedKey(int key) {
        refreshKvMap(key, NOT_VALID, OP_REMOVE);
        refreshFreq2NodesMap(key, NOT_VALID, OP_REMOVE);
        refreshKey2NodeMap(key, NOT_VALID, OP_REMOVE);
    }

    private void addKeyValue(int key, int value) {
        refreshKvMap(key, value, OP_ADD);
        refreshKey2NodeMap(key, value, OP_ADD);
        refreshFreq2NodesMap(key, value, OP_ADD);
        refreshMinUsedKeyIfNeed(key);
    }

    public void put(int key, int value) {
        Integer oldValue = kvMap.get(key);
        if (Objects.isNull(oldValue)) {
            if (size < capacity) {
                addKeyValue(key, value);
            } else {
                removeMinUsedKey(minUsedKey);
                addKeyValue(key, value);
                minUsedKey = 1;
            }
        } else {
            addKeyValue(key, value);
        }
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }
}
