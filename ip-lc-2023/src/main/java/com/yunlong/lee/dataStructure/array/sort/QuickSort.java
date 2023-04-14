package com.yunlong.lee.dataStructure.array.sort;

import com.alibaba.fastjson.JSON;

import java.util.Random;

/**
 * @author lijie
 * @version 1.0
 * @description 912. 排序数组  快排
 * @date 14/4/23 5:05 下午
 * @link https://leetcode.cn/problems/sort-an-array/
 */
public class QuickSort {
    public int[] sortArray(int[] nums) {
        doQuickSortRecursion(nums, 0, nums.length - 1);
        return nums;
    }

    //region 快排
    public void doQuickSortRecursion(int[] nums, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return;
        }
        //1.哨兵下标
        int pivotIdx = new Random().nextInt(endIdx - startIdx + 1) + startIdx;
        //2.哨兵值
        int pivot = nums[pivotIdx];
        //3.哨兵放到待快排的第一个
        swap(nums, startIdx, pivotIdx);
        int i = startIdx;
        int j = endIdx;
        while (j > i) {
            //4.从后向前找比哨兵小的值
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            //5.从前往后找比哨兵大的值
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i != j) {
                swap(nums, i, j);
            }
        }
        //6.哨兵在放回中间
        swap(nums, startIdx, i);
        //7.递归快排前半部分
        doQuickSortRecursion(nums, startIdx, i - 1);
        //8.递归快排后半部分
        doQuickSortRecursion(nums, i + 1, endIdx);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{5, 1, 1, 2, 0, 0};
        System.out.println(JSON.toJSONString(new QuickSort().sortArray(nums)));
    }
}
