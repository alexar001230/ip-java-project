package com.yunlonglee.arr;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 189.轮转数组
 * @link https://leetcode.cn/problems/rotate-array/
 * @date 25/11/22 5:49 下午
 */
public class RotateArr {
    public static void rotate(int[] nums, int k) {
        if (nums.length == 1) {
            return;
        }

        int[] copyArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == k) {
                break;
            }
            if (i < k) {
                copyArr[i] = nums[nums.length - k + i];
            }
        }
        int index = k;
        for (int i = 0; i < nums.length - k; i++) {
            copyArr[index] = nums[i];
            index++;
        }
        System.arraycopy(copyArr, 0, nums, 0, nums.length);
        System.out.println(JSON.toJSONString(nums));
        return;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate(nums, k);
    }
}
