package com.yunlonglee.nsum;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 22/7/21 6:13 下午
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7};
        int target = 9;
        int[] result = twoSumN(nums, target);
        System.out.println(JSON.toJSONString(result));
    }

    public static int[] twoSum2N(int[] nums, int target) {
        int[] resultArr = new int[2];
        Map<Integer, Integer> numsIndexMap = Maps.newConcurrentMap();
        for (int i = 0; i < nums.length; i++) {
            numsIndexMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int matched = target - nums[i];
            if (Objects.nonNull(numsIndexMap.get(matched))) {
                resultArr[0] = i;
                resultArr[1] = numsIndexMap.get(matched);
                return resultArr;
            }
        }
        return null;
    }

    public static int[] twoSumN(int[] nums, int target) {
        int[] resultArr = new int[2];
        Map<Integer, Integer> numsIndexMap = Maps.newConcurrentMap();
        for (int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            if (numsIndexMap.containsKey(left)) {
                resultArr[0] = numsIndexMap.get(left);
                resultArr[1] = i;
                return resultArr;
            }
            numsIndexMap.put(nums[i], i);
        }
        return null;
    }
}
