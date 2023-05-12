package com.yunlong.lee.algo.dp.subSeq.continous;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author lijie
 * @version 1.0
 * @description 53. 最大子数组和
 * @date 11/5/23 6:06 下午
 * @link https://leetcode.cn/problems/maximum-subarray/description/
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        return doMaxSubArray(nums);
    }

    private int doMaxSubArray(int[] nums) {
        // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaxSubArray().maxSubArray(nums));
    }
}
