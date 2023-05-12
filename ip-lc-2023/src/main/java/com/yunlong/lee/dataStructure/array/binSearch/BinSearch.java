package com.yunlong.lee.dataStructure.array.binSearch;

/**
 * @author lijie
 * @version 1.0
 * @description 二分查找
 * @date 31/3/23 2:04 下午
 * @link https://leetcode.cn/problems/binary-search/
 */
public class BinSearch {
    private int retIdx = -1;

    public int search(int[] nums, int target) {
        searchRecursive(nums, target, 0, nums.length - 1);
        return retIdx;
    }

    private boolean searchRecursive(int[] nums, int target, int low, int high) {
        if (low > high) {
            return false;
        }
        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            retIdx = mid;
            return true;
        }
        boolean lowFind = searchRecursive(nums, target, low, mid - 1);
        if (!lowFind) {
            return searchRecursive(nums, target, mid + 1, high);
        }
        return lowFind;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        System.out.println(new BinSearch().search(nums, 9));
    }
}
