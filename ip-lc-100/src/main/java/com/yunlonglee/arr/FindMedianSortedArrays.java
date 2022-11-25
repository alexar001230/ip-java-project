package com.yunlonglee.arr;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 4. 寻找两个正序数组的中位数
 * @date 13/3/22 11:15 下午
 */
public class FindMedianSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] mergeArr = new int[m + n];
        for (int i = 0; i < m; i++) {
            mergeArr[i] = nums1[i];
        }
        for (int i = m; i < m + n; i++) {
            mergeArr[i] = nums2[i - m];
        }
        Arrays.sort(mergeArr);
        double result = 0d;
        if ((m + n) % 2 != 0) {
            result = mergeArr[(m + n) / 2];
        } else {
            result = (mergeArr[(m + n) / 2 - 1] + mergeArr[(m + n) / 2]) * 1.0d / 2;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        findMedianSortedArrays(nums1, nums2);
    }
}
