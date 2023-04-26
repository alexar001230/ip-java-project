package com.yunlong.lee.dataStructure.array;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 560. 和为 K 的子数组
 * @date 26/4/23 12:16 下午
 * @link https://leetcode.cn/problems/subarray-sum-equals-k/
 */
public class SubArrSum2KCnt {
    public int subarraySum(int[] nums, int k) {
        return subarraySumByOfficial(nums, k);
    }


    public int subarraySumByOfficial(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


    //region 前缀和做法：将时间复杂度降为n 过不了裁判机，fuck todo
    private int doSubArrSumByPrefixSum(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) {
            return nums[0] == k ? 1 : 0;
        }
        int[] prefixSumArr = new int[nums.length];
        HashMap<Integer, Integer> preJSum2CntMap = new HashMap<>();
        prefixSumArr[0] = nums[0];
        preJSum2CntMap.put(0, 1);//这里为什么要初始化为(0,1),这里是为了不忽略数组中元素值本身等于k的情况
        // preJSum2CntMap.put(nums[0], 1);
        //前缀和数组
        for (int i = 0; i < len; i++) {
            //连续前缀和数组
            if (i == 0) {
                prefixSumArr[i] = nums[i];
            } else {
                prefixSumArr[i] = prefixSumArr[i - 1] + nums[i];
            }

            //连续前缀和map
            preJSum2CntMap.put(prefixSumArr[i],
                    preJSum2CntMap.getOrDefault(prefixSumArr[i], 0) + 1);
        }

        int cnt = 0;
        for (int i = 0; i < len; i++) {
            int preJSum = prefixSumArr[i] - k;
            cnt = cnt + preJSum2CntMap.getOrDefault(preJSum, 0);
        }
        return cnt;
    }
    //endregion

    //region 双层循环
    private int doSubArrSum(int[] nums, int k) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int target = k - nums[i];
            if (target == 0) {
                cnt++;
                // continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                target = target - nums[j];
                if (target == 0) {
                    cnt++;
                    // break;
                }
                // if (target < 0) {
                //     break;
                // }
            }
        }
        if (nums[nums.length - 1] == k) {
            cnt++;
        }
        return cnt;
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{-1, -1, 1};
        int k = 0;
        System.out.println(new SubArrSum2KCnt().subarraySum(nums, k));
    }
}
