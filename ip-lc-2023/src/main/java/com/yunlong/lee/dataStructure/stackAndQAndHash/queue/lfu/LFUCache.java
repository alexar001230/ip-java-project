package com.yunlong.lee.dataStructure.stackAndQAndHash.queue.lfu;

import com.yunlong.lee.utils.ds.DSUtils;

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
    //维护最少使用的key次数
    public int minUsedFreq = Integer.MAX_VALUE;


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

    public void put(int key, int value) {
        Integer oldValue = kvMap.get(key);
        if (Objects.isNull(oldValue)) {
            if (size < capacity) {
                addKeyValue(key, value);
            } else {
                removeMinUsedFreq(minUsedFreq);
                addKeyValue(key, value);
                minUsedFreq = 1;
            }
        } else {
            addKeyValue(key, value);
        }
    }


    public void refreshMinUsedKeyIfNeed(int key) {
        DLinkNode aNode = key2DLinkNodeMap.get(key);
        DLinkNode minUsedKeyNode = freq2RelatedNodesMap.get(minUsedFreq);
        if (Objects.nonNull(minUsedKeyNode)) {
            if (aNode.freq < minUsedKeyNode.freq) {
                minUsedFreq = aNode.freq;
            }
        } else {
            minUsedFreq = 1;
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

    private void removeMinUsedFreq(int minUsedFreq) {
        int minUsedKey = freq2RelatedNodesMap.get(minUsedFreq).key;
        if (Objects.isNull(kvMap.get(minUsedKey))) {
            return;
        }
        refreshKvMap(minUsedKey, NOT_VALID, OP_REMOVE);
        refreshFreq2NodesMap(minUsedKey, NOT_VALID, OP_REMOVE);
        refreshKey2NodeMap(minUsedKey, NOT_VALID, OP_REMOVE);
    }

    private void addKeyValue(int key, int value) {
        refreshKvMap(key, value, OP_ADD);
        refreshKey2NodeMap(key, value, OP_ADD);
        refreshFreq2NodesMap(key, value, OP_ADD);
        refreshMinUsedKeyIfNeed(key);
    }


    public static void main(String[] args) {
        String[] opArr = new String[]{"LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"};
        String params = "[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4]," +
                " [1], [3], [4]";
        DSUtils.printResByOperatesAndParams(LFUCache.class, opArr, params, "],");
    }
}
