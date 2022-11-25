package com.yunlonglee.arr;

/**
 * @author lijie
 * @version 1.0
 * @description 27.移除元素
 * @date 9/2/22 1:57 下午
 */
public class RemoveElement {
    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] == val) {
                return 0;
            } else {
                return 1;
            }
        }
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (nums[i] == val) {
                if (nums[j] != val) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                } else {
                    j--;
                }
            } else {
                i++;
            }
        }
        int m = 0;
        while (m < nums.length) {
            if (nums[m] != val) {
                m++;
            } else {
                break;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5};
        int val = 4;
        System.out.println(removeElement(nums, val));
    }
}
