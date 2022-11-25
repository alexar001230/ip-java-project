package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description 53. 最大子数组和
 * @date 10/2/22 6:04 上午
 */
public class MaximumSumSubArray {
    public int maxSubArray(int[] nums) {
        //动态规划5步曲
        //1.dp数组含义，i下标含义,dp[i]代表以第i个元素结尾的连续子数组最大和
        //2.转移方程  dp[i] = max(dp[i-1]+nums[i],nums[i])
        //3.初始化 i=1,dp[1] = max(dp[0]+nums[1],nums[1]);
        //4.遍历顺序
        //5.举例推演(过程打出来)
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0] + nums[1], nums[1]);
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i : dp) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}
