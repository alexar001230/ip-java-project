package com.yunlonglee.arr.matrix;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 59. 螺旋矩阵 II
 * @date 7/3/23 11:20 上午
 * @link https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class SpiralOrder2 {
    public int[][] generateMatrix(int n) {
        return doGenerateMatrix(n);
    }


    boolean l2r = false;
    boolean u2d = false;
    boolean r2l = false;
    boolean d2u = true;


    boolean[][] visitedArr;
    List<Integer> res = new LinkedList<>();
    int i = 1;

    private int[][] doGenerateMatrix(int n) {

        int[][] matrix = new int[n][n];

        int rStart = 0;
        int rEnd = n;
        int cStart = 0;
        int cEnd = n;

        int rIdx = 0;
        int cIdx = 0;

        visitedArr = new boolean[n][n];
        while (rEnd > rStart && cEnd > cStart && i <= n * n) {
            //left --> right
            if (d2u) {
                rIdx = rStart;
                for (int col = cStart; col < cEnd; col++) {
                    checkVisitedAndSet(matrix, rIdx, col);
                }
                rStart++;
                d2u = false;
                l2r = true;
            }
            //up --> down
            if (l2r) {
                cIdx = cEnd - 1;
                for (int row = rStart; row < rEnd; row++) {
                    checkVisitedAndSet(matrix, row, cIdx);
                }
                cEnd--;
                l2r = false;
                u2d = true;
            }
            //right --> left
            if (u2d) {
                rIdx = rEnd - 1;
                for (int col = cEnd - 1; col >= cStart; col--) {
                    checkVisitedAndSet(matrix, rIdx, col);
                }
                rEnd--;
                u2d = false;
                r2l = true;
            }
            //down --> up
            if (r2l) {
                cIdx = cStart;
                for (int row = rEnd - 1; row >= rStart; row--) {
                    checkVisitedAndSet(matrix, row, cIdx);
                }
                cStart++;
                r2l = false;
                d2u = true;
            }
        }
        return matrix;
    }

    private void checkVisitedAndSet(int[][] matrix, int row, int col) {
        if (!visitedArr[row][col]) {
            matrix[row][col] = i++;
            visitedArr[row][col] = true;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(JSON.toJSONString(new SpiralOrder2().generateMatrix(n)));
    }
}
