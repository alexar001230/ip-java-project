package com.yunlonglee.arr;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 1985. 找出数组中的第 K 大整数
 * @date 14/2/22 3:47 上午
 */
public class kthLargestNumber {

    public static String kthLargestNumber(String[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (isBigger(nums[i], nums[j])) {
                    String tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        int maxIndex = nums.length - 1;
        int minIndex = nums.length - k;
        return String.valueOf(nums[minIndex]);
    }

    private static boolean isBigger(String left, String right) {
        if (left.length() > right.length()) {
            return true;
        }
        if (left.length() == right.length()) {
            return left.compareTo(right) < 0;
        }
        return false;

    }


    public static void main(String[] args) {
        String[] arr = new String[]{"3", "6", "7", "10"
        };

        String a = "43";
        String b = "42";
        System.out.println(a.compareTo(b));

        //kthLargestNumber(arr, 4);
    }
}
