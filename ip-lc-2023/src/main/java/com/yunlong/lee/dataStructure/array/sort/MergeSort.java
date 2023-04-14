package com.yunlong.lee.dataStructure.array.sort;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 912. 排序数组  归并排序
 * @date 14/4/23 5:07 下午
 * @link https://leetcode.cn/problems/sort-an-array/
 */
public class MergeSort {
    public int[] sortArray(int[] nums) {
        return doMergeSort(nums);
    }

    public int[] doMergeSort(int[] nums) {
        mergeSortRecursion(nums, 0, nums.length - 1);
        return nums;
    }


    //region 归并排序
    private int[] tmpArr;

    private void mergeSortRecursion(int[] nums, int startIdx, int endIdx) {
        //1.递归出口,左idx >= 右Idx
        if (startIdx >= endIdx) {
            return;
        }
        //2.处理数据 划分归并集
        int mid = (startIdx + endIdx) / 2;
        mergeSortRecursion(nums, startIdx, mid);
        mergeSortRecursion(nums, mid + 1, endIdx);
        //3.结果集合归并(必须借助辅助数组)
        int cnt = 0;
        int i = startIdx;
        int j = mid + 1;
        tmpArr = new int[endIdx - startIdx + 1];
        while (i <= mid && j <= endIdx) {
            if (nums[i] < nums[j]) {
                tmpArr[cnt++] = nums[i];
                i++;
            } else {
                tmpArr[cnt++] = nums[j];
                j++;
            }
        }
        while (i <= mid) {
            tmpArr[cnt++] = nums[i++];
        }
        while (j <= endIdx) {
            tmpArr[cnt++] = nums[j++];
        }

        for (int k = 0; k < cnt; k++) {
            nums[startIdx + k] = tmpArr[k];
        }
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{5, 1, 1, 2, 0, 0};
        System.out.println(JSON.toJSONString(new MergeSort().sortArray(nums)));
    }
}
