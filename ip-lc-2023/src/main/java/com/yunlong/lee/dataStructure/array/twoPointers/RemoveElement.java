package com.yunlong.lee.dataStructure.array.twoPointers;

/**
 * @author lijie
 * @version 1.0
 * @description 27. 移除元素
 * @date 9/5/23 1:04 上午
 * @link https://leetcode.cn/problems/remove-element/
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        return doRemoveElement(nums,val);
    }

    private int doRemoveElement(int[] nums, int val) {
        int left = 0;
        int right = left + 1;
        while (right < nums.length && left < nums.length) {
            if (nums[left] != val) {
                left++;
                continue;
            }
            right = left + 1;
            if (nums[right] != nums[left]) {
                nums[left] = nums[right];
            } else {
                right++;
            }
        }
        return left + 1;
    }
}
