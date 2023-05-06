package com.yunlong.lee.dataStructure.array.binSearch;

import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 33. 搜索旋转排序数组
 * @date 5/5/23 11:50 上午
 * @link https://leetcode.cn/problems/search-in-rotated-sorted-array/
 */
public class SearchInRotatedSortedArr {
    public int search(int[] nums, int target) {
        return doSearch(nums, target);
    }

    private int doSearch(int[] nums, int target) {
        if (Objects.isNull(nums)) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] != target) {
                return -1;
            } else {
                return 0;
            }
        }
        //1.先找旋转点
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid - left == 1) {
                break;
            }
            if (nums[mid] > nums[left] && nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (target == nums[left]) {
            return left;
        }
        if (target < nums[left] && target > nums[len - 1]) {
            return binSearchTargetIdx(nums, 0, left, target);
        } else {
            return binSearchTargetIdx(nums, left + 1, len - 1, target);
        }
    }

    private int binSearchTargetIdx(int[] nums, int startIdx, int endIdx,
                                   int target) {
        while (startIdx <= endIdx) {
            int mid = (startIdx + endIdx) / 2;
            if (target < nums[mid]) {
                endIdx = mid - 1;
            } else if (nums[mid] < target) {
                startIdx = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 8, 1, 2, 3};
        int target = 8;
        System.out.println(new SearchInRotatedSortedArr().search(nums, target));
    }
}
