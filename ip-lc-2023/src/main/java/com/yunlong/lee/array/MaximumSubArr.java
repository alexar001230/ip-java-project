package com.yunlong.lee.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 7/3/23 4:34 下午
 * @link https://leetcode.cn/problems/maximum-subarray/
 */
public class MaximumSubArr {
    public int maxSubArray(int[] nums) {
        int fi = nums[0];
        int maxRes = nums[0];
        for (int curNo : nums) {
            fi = Math.max(fi + curNo, curNo);
            maxRes = Math.max(fi, maxRes);
        }
        return maxRes;
    }

    public int myMaxSubArray(int[] nums) {
        if (Objects.isNull(nums)) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //窗口大小从1调整到size,循环统计每个窗口大小的max
        int windowSize = 1;
        List<Integer> maxList = new ArrayList<>();
        while (windowSize <= nums.length) {
            int aMax = Integer.MIN_VALUE;
            for (int i = 0; i <= nums.length - windowSize; i++) {
                int j = i + windowSize;
                int aSum = 0;
                int m = i;
                while (m < j) {
                    aSum += nums[m];
                    m++;
                }
                aMax = Math.max(aMax, aSum);
            }
            System.out.println("windowSize=" + windowSize + ",aMax=" + aMax);
            maxList.add(aMax);
            windowSize++;
        }
        return maxList.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }).get();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{};
        System.out.println(new MaximumSubArr().maxSubArray(nums));
    }


}
