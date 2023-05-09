package com.yunlong.lee.algo.greedy;

/**
 * @author lijie
 * @version 1.0
 * @description 55. 跳跃游戏
 * @date 9/5/23 12:52 下午
 * @link https://leetcode.cn/problems/jump-game/
 */
public class CanJump {
    public boolean canJump(int[] nums) {
        return doCanJump(nums);
    }

    private boolean doCanJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        int[] dpArr = new int[nums.length];
        dpArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpArr[i] = Math.max(dpArr[i - 1], i + nums[i]);
            if (dpArr[i] >= nums.length - 1) {
                return true;
            }
            if (dpArr[i] == i) {
                return false;
            }
        }
        return true;
    }
}
