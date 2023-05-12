package com.yunlong.lee.algo.dp.knapsack.knapsack01;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 1049. 最后一块石头的重量 II
 * @date 12/5/23 10:39 上午
 * @link https://leetcode.cn/problems/last-stone-weight-ii/description/
 */
public class LastStoneWeight2 {
    public int lastStoneWeightII(int[] stones) {
        return doLastStoneWeightII(stones);
    }

    private int doLastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        double sizeD = sum / 2.0;
        int bagSize = new Double(Math.ceil(sizeD)).intValue();
        int len = stones.length;

        int[][] dp = new int[len + 1][bagSize + 1];

        dp[0][0] = 0;

        for (int i = 1; i <= len; i++) {
            int stoneIdx = i - 1;
            int iStoneW = stones[stoneIdx];
            for (int j = 0; j <= bagSize; j++) {
                if (j >= iStoneW) {
                    dp[i][j] = Math.max(dp[i - 1][j - iStoneW] + iStoneW, dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return Math.abs(sum - dp[len][bagSize] * 2);
    }

    public static void main(String[] args) {
        int[] stones = new int[]{2, 7, 4, 1, 8, 1};
        System.out.println(new LastStoneWeight2().lastStoneWeightII(stones));
    }
}
