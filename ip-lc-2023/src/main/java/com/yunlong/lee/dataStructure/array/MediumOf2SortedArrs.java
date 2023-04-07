package com.yunlong.lee.dataStructure.array;

/**
 * @author lijie
 * @version 1.0
 * @description 寻找两个正序数组的中位数
 * @date 6/4/23 6:25 下午
 * @link https://leetcode.cn/problems/median-of-two-sorted-arrays/
 */
public class MediumOf2SortedArrs {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return doFindMedianSortedArrays(nums1, nums2);
    }

    private double doFindMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergedArr = mergeRecursive(nums1, nums2);
        double midTotal =
                (double) mergedArr[(mergedArr.length - 1) / 2] + (double) mergedArr[mergedArr.length / 2];
        return midTotal / 2;
    }

    private int[] mergeRecursive(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] mergedArr = new int[nums1.length + nums2.length];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                mergedArr[k] = nums1[i++];
            } else {
                mergedArr[k] = nums2[j++];
            }
            k++;
        }
        while (i < nums1.length) {
            mergedArr[k++] = nums1[i++];
        }

        while (j < nums2.length) {
            mergedArr[k++] = nums2[j++];
        }
        return mergedArr;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(new MediumOf2SortedArrs().findMedianSortedArrays(nums1, nums2));
    }
}
