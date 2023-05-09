package com.yunlong.lee.dataStructure.array.twoPointers;

/**
 * @author lijie
 * @version 1.0
 * @description 26. 删除有序数组中的重复项
 * @date 9/5/23 12:00 上午
 * @link https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class RemoveDuplicatesFromSortedArr {
    public int removeDuplicates(int[] nums) {
        return doRemoveDuplicatesBy2Pointers(nums);
    }

    private int doRemoveDuplicatesBy2Pointers(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int left = 0;
        int right = left + 1;
        while (right < nums.length) {
            if (nums[left] == nums[right]) {
                right++;
                continue;
            }
            nums[left + 1] = nums[right];
            left++;
        }
        return left + 1;
    }

    //region u r sb 双指针都没想到么？
    private int doRemoveDuplicatesMy(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; ) {
            for (int j = i + 1; j < nums.length && nums[j] != Integer.MIN_VALUE; j++) {
                if (nums[i] == nums[j]) {
                    int m = j;
                    while (m < nums.length - 1) {
                        nums[m] = nums[m + 1];
                        m++;
                    }
                    nums[m] = Integer.MIN_VALUE;
                } else {
                    break;
                }
            }
            if (nums[i] == Integer.MIN_VALUE) {
                res = i;
                break;
            }
            if (nums[i + 1] == Integer.MIN_VALUE) {
                res = i + 1;
                break;
            }
            if (nums[i] != nums[i + 1]) {
                i++;
            }

        }
        return res;
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(new RemoveDuplicatesFromSortedArr().removeDuplicates(nums));
    }
}
