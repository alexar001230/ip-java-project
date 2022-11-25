package com.yunlonglee.dataStructure;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 347. 前 K 个高频元素
 * @date 9/3/22 12:17 上午
 */
public class TopKFrequent {

    /**
     * 利用小顶堆实现(priorityQueue是一个披着对列外衣的大顶堆)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k > nums.length) {
            return null;
        }

        HashMap<Integer, Integer> num2FrequentsMap = new HashMap<>();
        for (int i : nums) {
            num2FrequentsMap.put(i, num2FrequentsMap.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue =
                new PriorityQueue<>(((o1, o2) -> o1.getValue() - o2.getValue()));

        for (Map.Entry<Integer, Integer> entry : num2FrequentsMap.entrySet()) {
            priorityQueue.offer(entry);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = priorityQueue.poll().getKey();
        }

        return result;
    }


    static class NumFrequent {
        public Integer num;
        public Integer frequents;
    }

    /**
     * 利用排序算出
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent1(int[] nums, int k) {
        if (null == nums || nums.length == 0 || k > nums.length) {
            return null;
        }
        HashMap<Integer, Integer> num2FrequentsMap = new HashMap<>();
        for (int i : nums) {
            if (null == num2FrequentsMap.get(i)) {
                num2FrequentsMap.put(i, 1);
            } else {
                num2FrequentsMap.put(i, num2FrequentsMap.get(i) + 1);
            }
        }
        List<NumFrequent> numFrequents = new ArrayList<>();
        for (Map.Entry entry : num2FrequentsMap.entrySet()) {
            Integer num = (Integer) entry.getKey();
            Integer frequents = (Integer) entry.getValue();
            NumFrequent numFrequent = new NumFrequent();
            numFrequent.num = num;
            numFrequent.frequents = frequents;
            numFrequents.add(numFrequent);
        }
        sortByFrequent(numFrequents);
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = numFrequents.get(i).num;
        }
        return arr;
    }

    private static void sortByFrequent(List<NumFrequent> numFrequents) {
        for (int i = 0; i < numFrequents.size(); i++) {
            for (int j = i + 1; j < numFrequents.size(); j++) {
                NumFrequent numFrequentsI = numFrequents.get(i);
                NumFrequent numFrequentsJ = numFrequents.get(j);
                if (numFrequentsI.frequents < numFrequentsJ.frequents) {
                    int numTmp = numFrequentsI.num;
                    int frequentTmp = numFrequentsI.frequents;

                    numFrequents.get(i).num = numFrequentsJ.num;
                    numFrequents.get(i).frequents = numFrequentsJ.frequents;

                    numFrequents.get(j).num = numTmp;
                    numFrequents.get(j).frequents = frequentTmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6};
        int k = 5;
        topKFrequent(arr, k);
    }
}
