package com.yunlong.lee.dataStructure.stackAndQAndHash.queue.lru;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 146. LRU 缓存
 * @date 24/4/23 9:56 上午
 * @link https://leetcode.cn/problems/lru-cache/
 */
public class MyLRUCache {

    /**
     * 自己的思路是利用数组做存储，利用map维护k-idx,在通过数组下标获取相应的value,
     * 这样存在的一个问题1.get时对应的kv-idx变化需要维护(看了答案这部分是对的)，
     * 另外还有，头下标减到0，做数据交换也是个复杂点,2.put过程同样需要维护两个映射
     */

    private int[] cacheArr;

    private int headIdx;
    private int tailIdx;

    private HashMap<Integer, Integer> k2VIdxMap = new HashMap<>();
    private HashMap<Integer, Integer> vIdx2KeyMap = new HashMap<>();

    public MyLRUCache(int capacity) {
        cacheArr = new int[capacity];
    }

    public int get(int key) {
        if (cacheArr.length == 0) {
            return -1;
        }
        Integer vIdx = k2VIdxMap.get(key);
        if (Objects.isNull(vIdx)) {
            return -1;
        }
        int originHeadVal = cacheArr[0];
        int originHeadIdx = 0;

        k2VIdxMap.put(key, 0);
        swap(cacheArr, 0, vIdx);

        // k2VIdxMap.put()
        return cacheArr[vIdx];
    }

    public void put(int key, int value) {
        Integer vIdx = k2VIdxMap.get(key);
        if (Objects.nonNull(vIdx)) {
            //老数据
            int originKey = vIdx2KeyMap.get(headIdx);
            //保存数据
            swap(cacheArr, headIdx, vIdx);
            //索引一致
            //新加入的
            k2VIdxMap.put(key, headIdx);
            vIdx2KeyMap.put(headIdx, key);
            k2VIdxMap.put(originKey, vIdx);
            vIdx2KeyMap.put(vIdx, originKey);
            return;
        } else {
            if (tailIdx == cacheArr.length - 1) {
                swap(cacheArr, headIdx, tailIdx);
                cacheArr[headIdx] = value;
                k2VIdxMap.put(key, headIdx);
            } else {
                cacheArr[tailIdx++] = value;
                k2VIdxMap.put(key, tailIdx);
            }
        }
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
