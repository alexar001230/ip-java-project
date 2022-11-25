package com.yunlonglee.arr;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 88. 合并两个有序数组
 * @date 9/2/22 3:26 下午
 */
public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0){
            return;
        }
        int pivotKey = nums2[0];
        ArrayList<Integer> sList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();
        for (int i=0;i<m ; i++) {
            if (nums1[i] > pivotKey) {
                bList.add(nums1[i]);
            } else {
                sList.add(nums1[i]);
            }
        }

        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] > pivotKey) {
                bList.add(nums2[i]);
            } else {
                sList.add(nums2[i]);
            }
        }

        sList.add(pivotKey);
        Integer[] sArr = sList.toArray(new Integer[sList.size()]);
        Arrays.sort(sArr);

        Integer[] bArr = bList.toArray(new Integer[bList.size()]);
        Arrays.sort(bArr);

        int i = 0;
        while (i < sArr.length) {
            nums1[i] = sArr[i];
            i++;
        }
        int j = 0;
        while (j < bArr.length) {
            nums1[i + j] = bArr[j];
            j++;
        }
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        merge(nums1, 3, nums2, 3);
        System.out.println(JSON.toJSONString(nums1));
    }
}
