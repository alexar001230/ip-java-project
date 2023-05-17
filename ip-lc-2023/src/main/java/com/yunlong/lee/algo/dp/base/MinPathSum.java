package com.yunlong.lee.algo.dp.base;

import com.sun.tools.javac.util.ArrayUtils;
import com.yunlong.lee.utils.arr.ArrUtils;

/**
 * @author lijie
 * @version 1.0
 * @description 64. 最小路径和
 * @date 13/5/23 7:20 下午
 * @link https://leetcode.cn/problems/minimum-path-sum/
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        return doMinPathSum(grid);
    }

    private int doMinPathSum(int[][] grid) {
        int rLen = grid.length;
        int cLen = grid[0].length;

        int[][] dp = new int[rLen][cLen];

        int cPrefixSum = 0;
        for (int i = 0; i < cLen; i++) {
            cPrefixSum = cPrefixSum + grid[0][i];
            dp[0][i] = cPrefixSum;
        }

        int rPrefixSum = 0;
        for (int i = 0; i < rLen; i++) {
            rPrefixSum = rPrefixSum + grid[i][0];
            dp[i][0] = rPrefixSum;
        }

        for (int i = 1; i < rLen; i++) {
            for (int j = 1; j < cLen; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[rLen - 1][cLen - 1];
    }

    public static void main(String[] args) {
        // ArrUtils.str2Arr2Print("[[1,3,1],[1,5,1],[4,2,1]]");
        int[][] arr = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(new MinPathSum().minPathSum(arr));
    }
}
