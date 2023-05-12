package com.yunlong.lee.algo.dp.base;

import com.yunlong.lee.utils.arr.ArrUtils;

/**
 * @author lijie
 * @version 1.0
 * @description 63. 不同路径 II
 * @date 11/5/23 1:56 下午
 * @link https://leetcode.cn/problems/unique-paths-ii/
 */
public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return doUniquePathsWithObstacles(obstacleGrid);
    }


    private int[][] obstacleGridArr;

    private int doUniquePathsWithObstacles(int[][] obstacleGrid) {
        obstacleGridArr = obstacleGrid;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int rowLen = obstacleGrid.length;
        int colLen = obstacleGrid[0].length;
        int[][] dpArr = new int[rowLen][colLen];
        int obIdx = 0;
        for (int i = 0; i < rowLen; i++) {
            if (obstacleGrid[i][0] == 1) {
                obIdx = i;
            }
            if (obIdx > 0) {
                if (i >= obIdx) {
                    dpArr[i][0] = 0;
                }
            } else {
                dpArr[i][0] = 1;
            }
        }
        obIdx = 0;
        for (int i = 0; i < colLen; i++) {
            if (obstacleGrid[0][i] == 1) {
                obIdx = i;
            }
            if (obIdx > 0) {
                if (i >= obIdx) {
                    dpArr[0][i] = 0;
                }
            } else {
                dpArr[0][i] = 1;
            }
        }
        for (int i = 1; i < rowLen; i++) {
            for (int j = 1; j < colLen; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dpArr[i][j] = select(i - 1, j) * dpArr[i - 1][j]
                            + select(i, j - 1) * dpArr[i][j - 1];
                } else {
                    dpArr[i][j] = 0;
                }
            }
        }
        return dpArr[rowLen - 1][colLen - 1];

    }

    private int select(int rowIdx, int colIdx) {
        return obstacleGridArr[rowIdx][colIdx] == 1 ? 0 : 1;
    }

    public static void main(String[] args) {
        // ArrUtils.str2Arr2Print("[[0,1],[0,0]]");
        int[][] arr = new int[][]{{0, 1}, {0, 0}};
        // int[][] arr = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(new UniquePathsWithObstacles().uniquePathsWithObstacles(arr));
    }
}
