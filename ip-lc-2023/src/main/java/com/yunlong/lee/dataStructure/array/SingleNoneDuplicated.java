package com.yunlong.lee.dataStructure.array;

/**
 * @author lijie
 * @version 1.0
 * @description 540. 有序数组中的单一元素
 * @date 11/4/23 5:25 下午
 * @link https://leetcode.cn/problems/single-element-in-a-sorted-array/
 */
public class SingleNoneDuplicated {
    public int singleNonDuplicate(int[] nums) {
        return doSingleNonDuplicate(nums);
    }

    private int doSingleNonDuplicate(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int pre = 0;
        int post = pre + 1;
        while (post < nums.length) {
            if (nums[post] == nums[pre]) {
                post++;
            } else {
                if (post - pre == 1) {
                    return nums[pre];
                } else {
                    if (post == nums.length - 1) {
                        return nums[post];
                    }
                }
                pre = post;
                post = pre + 1;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 8, 8, 9};
        // int[] nums = new int[]{3,3,7,7,10,11,11};
        // int[] nums = new int[]{};
        System.out.println(new SingleNoneDuplicated().singleNonDuplicate(nums));
    }
}
