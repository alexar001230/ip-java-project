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
        return doSearchByPeak(nums, target);
    }


    //region
    private int doSearchByPeak(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int resIdx = -1;
        for (; left <= right; ) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                resIdx = mid;
                break;
            }
            if (mid == 0) {
                if (mid == 0 && nums[mid + 1] == target) {
                    resIdx = mid + 1;
                }
                break;
            }

            if (mid == nums.length - 1) {
                if (nums[mid] == target) {
                    resIdx = mid + 1;
                }
                break;
            }


            if (nums[0] <= nums[mid]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return resIdx;
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{5,1,3};
        int target = 5;
        System.out.println(new SearchInRotatedSortedArr().search(nums, target));
    }
}
