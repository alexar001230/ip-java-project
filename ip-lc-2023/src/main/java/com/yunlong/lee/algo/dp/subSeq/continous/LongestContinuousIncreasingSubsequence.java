package com.yunlong.lee.algo.dp.subSeq.continous;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 674. 最长连续递增序列
 * @date 11/5/23 6:51 下午
 * @link https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
 */
public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        return doFindLengthOfLCIS(nums);
    }

    private int doFindLengthOfLCIS(int[] nums) {
        if(Objects.isNull(nums)){
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,4,7};
        System.out.println(new LongestContinuousIncreasingSubsequence().findLengthOfLCIS(nums));
    }
}
