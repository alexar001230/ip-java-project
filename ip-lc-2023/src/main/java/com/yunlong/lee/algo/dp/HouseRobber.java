package com.yunlong.lee.algo.dp;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 198. 打家劫舍
 * @date 20/4/23 10:53 上午
 * @link https://leetcode.cn/problems/house-robber/
 */
public class HouseRobber {
    public int rob(int[] nums) {
        return doRob(nums);
    }

    private int doRob(int[] nums) {
        //1.dp[i]含义,到第i个家能抢劫的最多的金额数
        //2.转移方程  dp[i] = dp[i-2]+nums[i]
        //3.遍历顺序,从小到大
        //4.初始化
        //5.举例验证
        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dpArr = new int[nums.length + 1];
        dpArr[0] = 0;
        dpArr[1] = nums[0];
        dpArr[2] = Math.max(nums[0], nums[1]);
        // dpArr[3] = Math.max(dpArr[2], dpArr[1] + nums[3]);
        // int max = Integer.MIN_VALUE;
        for (int i = 3; i < nums.length + 1; i++) {
            dpArr[i] = Math.max(dpArr[i - 1], dpArr[i - 2] + nums[i - 1]);
        }
        return dpArr[nums.length];
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{1, 2, 3, 1};
        int[] nums = new int[]{2, 7, 9, 3, 1};

        System.out.println(new HouseRobber().rob(nums));
    }
}
