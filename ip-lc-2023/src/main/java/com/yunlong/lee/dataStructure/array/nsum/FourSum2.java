package com.yunlong.lee.dataStructure.array.nsum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 四数相加之和
 * @date 6/4/23 6:58 下午
 * @link https://leetcode.cn/problems/4sum-ii/
 */
public class FourSum2 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // int[] sum12Arr = sumArr(nums1, nums2);
        int[] sum34Arr = sumArr(nums3, nums4);
        HashMap<Integer, Integer> sum34ToTimesMap = value2TimesMap(sum34Arr);
        int cnt = 0;
        for (Integer aNum1 : nums1) {
            for (Integer aNum2 : nums2) {
                Integer originTimes = sum34ToTimesMap.get(-aNum1 - aNum2);
                if (Objects.nonNull(originTimes) && sum34ToTimesMap.containsKey(-aNum1 - aNum2)) {
                    cnt = cnt+originTimes;
                    // if (originTimes == 1) {
                    //     sum34ToTimesMap.put(-aNum1 - aNum2, null);
                    // } else {
                    //     sum34ToTimesMap.put(-aNum1 - aNum2, originTimes--);
                    // }

                }
            }
        }
        return cnt;
    }

    private int[] sumArr(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] sum12Arr = new int[n * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum12Arr[k++] = nums1[i] + nums2[j];
            }
        }
        return sum12Arr;
    }

    private HashMap<Integer, Integer> value2TimesMap(int[] arr) {
        HashMap<Integer, Integer> value2TimesMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            value2TimesMap.put(arr[i], value2TimesMap.getOrDefault(arr[i], 0) + 1);
        }
        return value2TimesMap;
    }


    //region 这个和组合有啥关系,通过回溯怎么实现
    private int doFourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int[][] numsArr = new int[][]{nums1, nums2, nums3, nums4};
        return 0;
    }

    List<List<Integer>> allCombinations = new ArrayList<>();


    public void combineRecursive(int[][] numsArr, int left, int right) {
        if (right - left == 1) {
            combine2Arr(numsArr[left], numsArr[right]);
            return;
        }
        int mid = (left + right) / 2;
        // int[] leftCombineArr = combine2Arr(numsArr, left, mid);
        // int[] rightCombineArr = combine2Arr(numsArr, mid + 1, right);
        // combine2Arr(leftCombineArr, rightCombineArr);
        return;
    }

    public void combine2Arr(int[] num1, int[] num2) {
        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num2.length; j++) {
                // aCombine.add(num1[i]);
                // aCombine.add(num2[j]);
                // allCombinations.add(aCombine);
            }
        }
        return;
    }
    //endregion

    public static void main(String[] args) {
        // int[] nums1 = new int[]{1, 2};
        // int[] nums2 = new int[]{-2, -1};
        // int[] nums3 = new int[]{-1, 2};
        // int[] nums4 = new int[]{0, 2};
        int[] nums1 = new int[]{-1, -1};
        int[] nums2 = new int[]{-1, 1};
        int[] nums3 = new int[]{-1, 1};
        int[] nums4 = new int[]{1, -1};
        System.out.println(new FourSum2().fourSumCount(nums1, nums2, nums3, nums4));
    }
}
