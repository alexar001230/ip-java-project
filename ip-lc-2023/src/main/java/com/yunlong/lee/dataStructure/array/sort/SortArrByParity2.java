package com.yunlong.lee.dataStructure.array.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 922. 按奇偶排序数组 II
 * @date 26/4/23 5:57 下午
 * @link https://leetcode.cn/problems/sort-array-by-parity-ii/
 */
public class SortArrByParity2 {
    public int[] sortArrayByParityII(int[] nums) {
        return doSortArrayByParityII(nums);
    }

    private int[] doSortArrayByParityII(int[] nums) {
        int i = 0;
        int j = 1;
        while (i < nums.length && j < nums.length) {
            if (nums[i] % 2 == 0) {
                i = i + 2;
                continue;
            }
            if (nums[j] % 2 == 1) {
                j = j + 2;
                continue;
            }
            if (i < nums.length && j < nums.length) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
            i = i + 2;
            j = j + 2;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, 2, 1};
        System.out.println(JSON.toJSONString(new SortArrByParity2().sortArrayByParityII(nums)));
    }
}
