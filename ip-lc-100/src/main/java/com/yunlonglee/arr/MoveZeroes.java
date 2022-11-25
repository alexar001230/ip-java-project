package com.yunlonglee.arr;

/**
 * @author lijie
 * @version 1.0
 * @description 283. 移动零
 * @date 9/3/22 2:39 下午
 */
public class MoveZeroes {
    //冒泡排序是一个稳定的排序
    public static void moveZeroes(int[] nums) {
        if (null == nums || nums.length == 1) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] == 0 && nums[j]!=0){
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
            }
        }
    }

}
