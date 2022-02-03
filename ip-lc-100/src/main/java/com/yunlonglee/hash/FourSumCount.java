package com.yunlonglee.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description 四数相加2
 * @date 3/2/22 4:13 下午
 */
public class FourSumCount {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //1.两个数组为一对，构建hash映射对，并记录相应和的个数
        //2.遍历两个目标和的个数，进行统计
        Map<Integer, Integer> arr1And2SumMap = sumCountMap(nums1,nums2);
        Map<Integer, Integer> arr3And4SumMap = sumCountMap(nums3,nums4);
        int result = 0;
        for (Map.Entry entry : arr1And2SumMap.entrySet()) {
            Integer sum1And2 = (Integer) entry.getKey();
            Integer sum1And2Count = (Integer) entry.getValue();
            Integer left = 0 - sum1And2;
            Integer leftCount = arr3And4SumMap.get(left);
            if (null != leftCount) {
                result = result + (sum1And2Count * leftCount);
            }
        }
        return result;

    }

    private Map<Integer,Integer> sumCountMap(int[] nums1,int[] nums2){
        Map<Integer, Integer> arr1And2SumMap = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                int sumTmp = i + j;
                if (null != arr1And2SumMap.get(sumTmp)) {
                    int count = arr1And2SumMap.get(sumTmp);
                    arr1And2SumMap.put(sumTmp, count + 1);
                } else {
                    arr1And2SumMap.put(sumTmp, 1);
                }
            }
        }
        return arr1And2SumMap;
    }
}
