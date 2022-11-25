package com.yunlonglee.dp.fisrt;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 494. 目标和
 * @date 18/2/22 11:26 下午
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        //有target，一定可以把数组分成left和right两组，所以，left+right = sum,left-right =
        // target,所以left = (sum+target)/2,问题转换为,在数组中求目标和为left共有多少种加法
        //1.dp[i],目标和为i，共有dp[i]种算法
        //2.转移方程,dp[i] = dp[i]+dp[i-nums[i]],
        //3.遍历顺序，先物品后背包
        //4.初始化
        //5.举例推导
        int sum = Arrays.stream(nums).sum();
        if ((target + sum) % 2 != 0) {
            return 0;
        }
        int left = Math.abs((target + sum) / 2);
        int[] dp = new int[left + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[left];
    }
}
