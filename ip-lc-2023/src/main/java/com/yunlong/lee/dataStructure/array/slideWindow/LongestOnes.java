package com.yunlong.lee.dataStructure.array.slideWindow;

/**
 * @author lijie
 * @version 1.0
 * @description 1004. 最大连续1的个数 III
 * @date 8/5/23 3:25 下午
 * @link https://leetcode.cn/problems/max-consecutive-ones-iii/description/
 */
public class LongestOnes {
    public int longestOnes(int[] nums, int k) {
        return doLongestOnesBySlideWindow(nums, k);
    }


    //region 滑窗
    private int doLongestOnesBySlideWindow(int[] nums, int k) {
        int len = nums.length;
        int lSum = 0;
        int rSum = 0;
        int maxLength = Integer.MIN_VALUE;

        int left = 0;
        for (int right = 0; right < len; right++) {
            rSum = rSum + (1 - nums[right]);
            while (lSum < rSum - k) {
                lSum = lSum + (1 - nums[left]);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
    //endregion

    //region slideWindow/前缀和
    private int doLongestOnesByBinSearch(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSumArr = new int[len + 1];
        //1.计算前缀和数组
        for (int i = 1; i <= len; i++) {
            prefixSumArr[i] = prefixSumArr[i - 1] + (1 - nums[i - 1]);
        }
        int max = 0;
        for (int right = 0; right < len; right++) {
            int left = binSearch(prefixSumArr, prefixSumArr[right + 1] - k);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    private int binSearch(int[] prefixSumArr, int target) {
        int low = 0;
        int high = prefixSumArr.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (prefixSumArr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    //endregion


    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 3;
        System.out.println(new LongestOnes().longestOnes(nums, k));
    }
}
