package com.yunlong.lee.array;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 7/3/23 3:24 下午
 * @link https://leetcode.cn/problems/kth-largest-element-in-an-array/
 */
public class KthLargest {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue(k,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });
        int res = 0;
        // List<Integer> kLargestNos = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (heap.size() < k) {
                // kLargestNos.add(nums[i]);
                heap.add(nums[i]);
                continue;
            }
            int min = heap.peek();
            if (nums[i] > min) {
                heap.poll();
                heap.add(nums[i]);
            }
        }
        res = heap.peek();
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int n = 4;
        System.out.println(new KthLargest().findKthLargest(arr, n));
    }
}
