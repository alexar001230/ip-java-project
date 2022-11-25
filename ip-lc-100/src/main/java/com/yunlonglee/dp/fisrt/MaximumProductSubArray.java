package com.yunlonglee.dp.fisrt;

/**
 * @author lijie
 * @version 1.0
 * @description 152. 乘积最大子数组
 * @date 10/2/22 6:03 上午
 */
public class MaximumProductSubArray {
    public int maxProduct(int[] nums) {
        //动态规划5步曲
        //1.dp[i]表示以第i个数结尾的最大乘积
        //2.递推公式：dp[i] = max(dp[i-1]*nums[i],nums[i])
        //3.初始化 dp[0]=nums[0]
        //4.遍历顺序 顺序遍历
        //5.举例推倒
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] * nums[i], nums[i]);
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
