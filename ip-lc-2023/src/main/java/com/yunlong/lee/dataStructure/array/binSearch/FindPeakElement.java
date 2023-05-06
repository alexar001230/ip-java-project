package com.yunlong.lee.dataStructure.array.binSearch;

import javafx.util.Pair;

/**
 * @author lijie
 * @version 1.0
 * @description 162. 寻找峰值
 * @date 4/5/23 2:41 下午
 * @link https://leetcode.cn/problems/find-peak-element/
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        return doFindPeakElementByBinSearch(nums);
    }


    //region 爬坡法
    private int doFindPeakElementByBinSearch(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        if (len == 2) {
            if (nums[0] > nums[1]) {
                return 0;
            } else {
                return 1;
            }
        }
        int left = 0;
        int right = len - 1;
        int resIdx = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            //注意边界
            if (mid == 0) {
                if (nums[mid] > nums[mid + 1]) {
                    return mid;
                }

            }
            if (mid == len - 1) {
                if (nums[mid] > nums[mid - 1]) {
                    return mid;
                }
            }
            //如果是峰值,返回idx
            if (isLess(nums, mid - 1, mid) && !isLess(nums, mid,
                    mid + 1)) {
                resIdx = mid;
                break;
            }
            //爬坡中,跳跃到当前峰值右边
            if (isLess(nums, mid, mid + 1)) {
                left = mid + 1;
            } else {
                //下坡中,跳跃到峰值左边
                right = mid;
            }
        }
        return resIdx;
    }


    private boolean isLess(int[] nums, int idx1, int idx2) {
        return nums[idx1] < nums[idx2];
    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[idx]};
    }

    public boolean compare(int[] nums, int idx1, int idx2) {
        return nums[idx1] < nums[idx2];
        // int[] num1 = get(nums, idx1);
        // int[] num2 = get(nums, idx2);
        // if (num1[0] != num2[0]) {
        //     return num1[0] > num2[0] ? 1 : -1;
        // }
        // if (num1[1] == num2[1]) {
        //     return 0;
        // }
        // return num1[1] > num2[1] ? 1 : -1;
    }

    //endregion

    //region 题目包含nums[i]!=nums[i+1]
    // 未排序的数组能在logN复杂度找到最大或最小么？
    private int doFindPeakElementByMax(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        return findPeakElementRecursion(nums, low, high).getValue();
    }

    Pair<Integer, Integer> max2IdxPair = new Pair<>(Integer.MIN_VALUE, -1);

    public Pair<Integer, Integer> findPeakElementRecursion(int[] nums, int low, int high) {
        if (high - low <= 1) {
            if (nums[high] > nums[low]) {
                max2IdxPair = new Pair<>(nums[high], high);
            } else {
                max2IdxPair = new Pair<>(nums[low], low);
            }
        } else {
            int mid = (high + low) / 2;
            Pair<Integer, Integer> lMaxPair = findPeakElementRecursion(nums, low, mid);
            Pair<Integer, Integer> rMaxPair = findPeakElementRecursion(nums, mid, high);
            if (lMaxPair.getKey() > rMaxPair.getKey()) {
                max2IdxPair = lMaxPair;
            } else {
                max2IdxPair = rMaxPair;
            }
        }
        return max2IdxPair;
    }
    //region

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 2};
        System.out.println(new FindPeakElement().findPeakElement(nums));
    }
}
