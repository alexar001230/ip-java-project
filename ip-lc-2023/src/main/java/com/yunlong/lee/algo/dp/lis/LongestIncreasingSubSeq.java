package com.yunlong.lee.algo.dp.lis;

import java.util.HashMap;

/**
 * @author lijie
 * @version 1.0
 * @description 673. 最长递增子序列的个数
 * @date 10/4/23 12:03 下午
 * @link https://leetcode.cn/problems/number-of-longest-increasing-subsequence/
 */
public class LongestIncreasingSubSeq {
    public int findNumberOfLIS(int[] nums) {
        return doFindNumberOfLIS(nums);
    }

    private int doFindNumberOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        //cnt[i]：以nums[i]为结尾的最长上升子序列的个数
        int[] cntArr = new int[nums.length];
        //最长上升子序列总个数
        int totalCnt = 0;
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            cntArr[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cntArr[i] = cntArr[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cntArr[i] += cntArr[j];
                    }
                }
            }
            // maxLen = Math.max(maxLen, dp[i]);
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                totalCnt = cntArr[i];
            } else if (dp[i] == maxLen) {
                totalCnt = totalCnt + cntArr[i];
            }
        }
        return totalCnt;
    }

    //region 自己分析的，动规的套路太少了,现在看到的都只想着按照数组覆盖的方式去分析
    private int myDoFindNumberOfLIS(int[] nums) {
        //dp 5 步走
        //1.确定dp含义,到第i下标，最长子序列的个数dp[i]
        //2.转移方程,num[i] > num[i]-1, dp[i] = dp[i-1],num[i]<num[i-1] &&
        // num[i]>num[i-2],dp[i] = dp[i-2]+dp[i-1]
        //3.初始化，dp[0] = 1,dp[1] = ,dp[2]=
        //4.遍历顺序
        //5.举例验证
        int[] dp = new int[nums.length];
        dp[0] = 1;
        if (nums[1] > nums[0]) {
            dp[1] = 1;
        } else {
            dp[1] = 2;
        }

        if (nums[2] > nums[1] && nums[1] > nums[0]) {
            dp[2] = 1;
        } else if (nums[2] < nums[1] && nums[1] > nums[0] && nums[2] > nums[0]) {
            dp[2] = 2;
        } else {
            dp[2] = Math.max(dp[1], dp[1]);
        }

        // if (nums[2] < nums[1] && nums[2] > nums[0]) {
        //     dp[2] = dp[1] + dp[0];
        // } else {
        //     dp[2] = dp[1];
        // }

        for (int i = 3; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1];
            } else {
                if (nums[i] > nums[i - 2]) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[nums.length - 1];
    }
    //endregion

    //    [1,3,5,4,7]
//     dp[0] = 1,dp[1] = 1,dp[2] = 1
//     if nums[i] > nums[i-1],dp[i] = dp[i-1]
//             if nums[i] < nums[i-1] && nums[i] > nums[i-2],dp[i] = dp[i-1]+dp[i-2]
//             if nums[i] < nums[i-1] && nums[i] < nums[i-2],dp[i] = dp[i-1]
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 4, 7};
        // int[] nums = new int[]{2, 2, 2, 2, 2};
        System.out.println(new LongestIncreasingSubSeq().findNumberOfLIS(nums));
    }
}
