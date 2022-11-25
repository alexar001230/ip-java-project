package com.yunlonglee.arr;

/**
 * @author lijie
 * @version 1.0
 * @description 414.第三大的数
 * @date 10/2/22 5:16 上午
 */
public class ThirdMaximumNumber {
    public static int thirdMax(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i > max1) {
                max1 = i;
            }
        }
        int max2 = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i < max1 && i >= max2) {
                max2 = i;
            }
        }
        int max3 = Integer.MIN_VALUE;
        boolean hasMax3 = false;
        for (int i : nums) {
            if (i < max2 && i >= max3) {
                max3 = i;
                hasMax3 = true;
            }
        }
        if(hasMax3){
            return max3;
        }else {
            return max1;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{ 3, 1};
        System.out.println(thirdMax(arr));
    }
}
