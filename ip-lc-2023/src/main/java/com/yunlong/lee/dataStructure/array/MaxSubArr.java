package com.yunlong.lee.dataStructure.array;

/**
 * @author lijie
 * @version 1.0
 * @description 连续子数组最大和
 * @date 16/3/23 7:47 下午
 * @link https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 */
public class MaxSubArr {
    public int maxSubArray(int[] nums) {
        //dp 5步走
        //1.dp[i]含义，到下标i的连续最大子数组和为dp[i]
        //2.转移方程,dp[i] = max(dp[i-1]+nums[i],nums[i]),这里nums[i]可单独成一段
        //3.初始化 dp[0] = nums[0],
        //4.遍历，顺序遍历即可
        //5.举例验证
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int maxRes = nums[0];
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > maxRes) {
                maxRes = dp[i];
            }
        }
        return maxRes;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaxSubArr().maxSubArray(nums));
    }
}
