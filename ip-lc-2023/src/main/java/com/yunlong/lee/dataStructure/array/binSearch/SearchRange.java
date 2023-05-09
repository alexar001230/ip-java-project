package com.yunlong.lee.dataStructure.array.binSearch;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 34. 在排序数组中查找元素的第一个和最后一个位置
 * @date 9/5/23 12:18 下午
 * @link https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        return doSearchRange(nums, target);
    }

    private int[] doSearchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int[] res = new int[]{-1, -1};
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] < target) {
                low = mid + 1;
                continue;
            }
            if (nums[mid] > target) {
                high = mid - 1;
                continue;
            }

            if (nums[mid] == target) {
                int left = mid;
                while (left >= 0 && nums[left] == target) {
                    left--;
                }
                if (left >= 0) {
                    res[0] = left + 1;
                } else {
                    res[0] = 0;
                }
                int right = mid;
                while (right <= nums.length - 1 && nums[right] == target) {
                    right++;
                }
                if (right <= nums.length - 1) {
                    res[1] = right - 1;
                } else {
                    res[1] = nums.length - 1;
                }
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 5;
        System.out.println(JSON.toJSONString(new SearchRange().searchRange(nums, target)));
    }
}
