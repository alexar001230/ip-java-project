package com.yunlong.lee.dataStructure.array.convert;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 189. 轮转数组
 * @date 15/5/23 2:26 下午
 * @link https://leetcode.cn/problems/rotate-array/description/
 */
public class RotateArr {
    public void rotate(int[] nums, int k) {
        doRotate2(nums, k);
    }


    //region  双指针+区间翻转
    private void doRotate2(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] arr, int fromIdx, int endIdx) {
        while (fromIdx <= endIdx) {
            int tmp = arr[fromIdx];
            arr[fromIdx] = arr[endIdx];
            arr[endIdx] = tmp;
            fromIdx++;
            endIdx--;
        }
    }


    //endregion

    //region 取余法
    private void doRotate1(int[] nums, int k) {
        int[] newArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newArr[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, nums.length);
    }
    //endregion

    //region 会超时
    private void doRotate0(int[] nums, int k) {
        int len = nums.length;
        while (k > 0) {
            int toMoveTmp = nums[len - 1];
            int rIdx = len - 1;
            int lIdx = rIdx - 1;
            while (lIdx >= 0) {
                nums[rIdx] = nums[lIdx];
                rIdx--;
                lIdx--;
            }
            nums[0] = toMoveTmp;
            k--;
        }
    }
    //region

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        new RotateArr().rotate(nums, k);
        System.out.println(JSON.toJSONString(nums));
    }
}
