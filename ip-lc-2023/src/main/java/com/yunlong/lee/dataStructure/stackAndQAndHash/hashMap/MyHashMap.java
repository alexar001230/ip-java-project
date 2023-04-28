package com.yunlong.lee.dataStructure.stackAndQAndHash.hashMap;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 706. 设计哈希映射
 * @date 27/4/23 4:24 下午
 * @link https://leetcode.cn/problems/design-hashmap/
 */
public class MyHashMap {
    private Integer[] arr;
    private int capacity;

    public MyHashMap() {
        capacity = 10000;
        arr = new Integer[capacity];
    }

    public void put(int key, int value) {
        if (get(key) == -1) {
            arr[k2Idx(key)] = value;
        } else {
            arr[k2Idx(key)] = value;
        }
    }

    public int get(int key) {
        Integer val = arr[k2Idx(key)];
        return Objects.isNull(val) ? -1 : val;
    }

    public void remove(int key) {
        if (get(key) == -1) {
            return;
        } else {
            put(key, -1);
            return;
        }
    }

    private int k2Idx(int key) {
        return key % capacity;
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
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
