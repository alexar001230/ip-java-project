package com.yunlong.lee.algo.dp.lis;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 523. 连续的子数组和
 * @date 4/5/23 1:18 下午
 * @link https://leetcode.cn/problems/continuous-subarray-sum/
 */
public class CheckSubArrSum {
    public boolean checkSubarraySum(int[] nums, int k) {
        return doCheckSubarraySum(nums, k);
    }

    //region 前缀和+同余定理+哈希表
    //preSum[j]-preSum[i] %k == 0 -->preSum[j]%k == preSum[i]%k==0,如果j-i>=2,
    // 返回true,所以遍历前缀和数组,如果之前算出来的余数map中已经存在,且距离至少为2,则为true
    private boolean doCheckSubarraySum(int[] nums, int k) {
        int[] preSumArr = new int[nums.length];
        preSumArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSumArr[i] = preSumArr[i - 1] + nums[i];
        }
        //维护map存余数--idx  map
        HashMap<Integer, Integer> yushu2IdxMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer yushu = preSumArr[i] % k;
            if (yushu == 0 && i >= 1) {
                return true;
            }
            Integer aIdx = yushu2IdxMap.get(yushu);
            if (Objects.isNull(aIdx)) {
                yushu2IdxMap.put(yushu, i);
            } else {
                if (i - aIdx >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
    //endregion

    //region 不是dp类问题
    private boolean doCheckSubarraySumMy(int[] nums, int k) {
        int[][] dpArr = new int[nums.length][nums.length];
        // dpArr[i][j] = 1.dp[i][j-1] == true && nums[j]%k == 0 ,true else
        // false; 2.dp[i-1][j] == true && nums[i-1]%k == 0,true,else false
        //dp[0][j],dp[i][0] = 0;

        int aSum = nums[0];
        dpArr[0][0] = aSum;
        for (int i = 1; i < nums.length; i++) {
            aSum += nums[i];
            dpArr[0][i] = aSum;
            if (aSum % k == 0) {
                return true;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                dpArr[i][j] = dpArr[i][j - 1] + nums[j];
                if (dpArr[i][j] % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    //endregion

    public static void main(String[] args) {
        int[] nums = new int[]{23, 2, 4, 6, 6};
        int k = 7;
        System.out.println(new CheckSubArrSum().checkSubarraySum(nums, k));
    }
}
