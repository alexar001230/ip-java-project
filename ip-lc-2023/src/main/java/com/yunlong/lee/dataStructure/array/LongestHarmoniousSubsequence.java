package com.yunlong.lee.dataStructure.array;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 最长和谐子序列
 * @date 6/4/23 11:20 上午
 * @link https://leetcode.cn/problems/longest-harmonious-subsequence/
 */
public class LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {
        return doFindHsByTwoPointer(nums);
    }


    //region 双指针(单纯双指针,操作优秀)
    private int doFindHsByTwoPointer(int[] nums) {
        if (Objects.isNull(nums) || nums.length <= 1) {
            return 0;
        }

        int maxLen = 0;

        Arrays.sort(nums);

        for (int left = 0, right = 0; right < nums.length; right++) {
            if (nums[right] - nums[left] > 1) {
                left++;
            }
            if (nums[right] - nums[left] == 1) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }
    //endregion

    private int doFindHsByMap(int[] nums) {
        if (Objects.isNull(nums) || nums.length <= 1) {
            return 0;
        }

        int maxLen = 0;
        HashMap<Integer, Integer> nums2CntMap = new HashMap<>();
        for (Integer num : nums) {
            nums2CntMap.put(num, nums2CntMap.getOrDefault(num, 0) + 1);
        }
        for (Integer num : nums2CntMap.keySet()) {
            maxLen = Math.max(maxLen,
                    nums2CntMap.get(num) + nums2CntMap.getOrDefault(num + 1, 0));
        }
        return maxLen;
    }


    //region 自己的问题是一直在纠结等于和+1的问题怎么规避,两个的边界问题一直处理不了,这个完全可以通过排序的手段来解决
    private int myFindHS(int[] nums) {
        if (Objects.isNull(nums) || nums.length <= 1) {
            return 0;
        }

        int maxLen = 0;
        int left = 0;

        for (; left < nums.length; left++) {
            List<Integer> slideWindow = new ArrayList<>();
            boolean existMinMax = false;
            int aMax = Integer.MIN_VALUE;
            int aMin = Integer.MAX_VALUE;
            for (int right = left; right < nums.length; right++) {
                int aDiff = Math.abs(nums[right] - nums[left]);
                if (aDiff == 1 || aDiff == 0) {
                    if (aDiff == 1) {
                        existMinMax = true;
                    }
                    //加入扰动元素后仍能保持和谐
                    aMax = Math.max(aMax, nums[right]);
                    aMin = Math.min(aMin, nums[right]);
                    int diff = Math.abs(aMax - aMin);
                    if (nums[left] == nums[right] || diff == 1) {
                        slideWindow.add(nums[right]);
                    }
                }
            }
            if (existMinMax) {
                maxLen = Math.max(maxLen, slideWindow.size());
            }

        }
        return maxLen;
    }
    //endregion

    public static void main(String[] args) {
        // int[] nums = new int[]{1, 3, 2, 2, 5, 2, 3, 7};
        int[] nums = new int[]{1, 2, 2, 1};
        // int[] nums = new int[]{1,2,3,4};
        // int[] nums = new int[]{1, 1, 1, 1};
        System.out.println(new LongestHarmoniousSubsequence().findLHS(nums));
    }
}
