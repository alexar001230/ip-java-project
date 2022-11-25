package com.yunlonglee.dp.fisrt;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 416. 分割等和子集
 * @date 18/2/22 7:52 下午
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (null == nums || nums.length == 0 || nums.length % 2 != 0) {
            return false;
        }
        int sum = 0;
        for (int i : nums) {
            sum = sum + i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        Arrays.sort(nums);
        int target = sum / 2;
        //1.dp[i]拿到第i数时最大和
        //2.转移方程,dp[i] = max(dp[j],dp[j-nums[i]]+nums[i])
        //3.遍历顺序，i in (0,length-1)
        //4.初始化,dp[0] = 0;dp[1] = num[1]
        //5.举例推导
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                //容量为j的背包，可放物品价值为max(背包最大容量dp[j],
                // 容量加入下一个物品重量后的最大价值dp[j-nums[i]]+nums[i])
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;

    }
}
