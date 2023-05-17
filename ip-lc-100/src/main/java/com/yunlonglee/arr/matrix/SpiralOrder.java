package com.yunlonglee.arr.matrix;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 54. 螺旋矩阵
 * @date 7/3/23 11:20 上午
 * @link https://leetcode.cn/problems/spiral-matrix/
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        return doSpiralOrder(matrix);
    }


    boolean l2r = false;
    boolean u2d = false;
    boolean r2l = false;
    boolean d2u = true;


    boolean[][] visitedArr;
    List<Integer> res = new LinkedList<>();

    private List<Integer> doSpiralOrder(int[][] matrix) {
        int rStart = 0;
        int rEnd = matrix.length;
        int cStart = 0;
        int cEnd = matrix[0].length;

        int rIdx = 0;
        int cIdx = 0;

        visitedArr = new boolean[matrix.length][matrix[0].length];

        while (rEnd > rStart && cEnd > cStart) {
            //left --> right
            if (d2u) {
                rIdx = rStart;
                for (int col = cStart; col < cEnd; col++) {
                    checkVisitedAndAdd(matrix, rIdx, col);
                }
                rStart++;
                d2u = false;
                l2r = true;
            }
            //up --> down
            if (l2r) {
                cIdx = cEnd - 1;
                for (int row = rStart; row < rEnd; row++) {
                    checkVisitedAndAdd(matrix, row, cIdx);
                }
                cEnd--;
                l2r = false;
                u2d = true;
            }
            //right --> left
            if (u2d) {
                rIdx = rEnd - 1;
                for (int col = cEnd - 1; col >= cStart; col--) {
                    checkVisitedAndAdd(matrix, rIdx, col);
                }
                rEnd--;
                u2d = false;
                r2l = true;
            }
            //down --> up
            if (r2l) {
                cIdx = cStart;
                for (int row = rEnd - 1; row >= rStart; row--) {
                    checkVisitedAndAdd(matrix, row, cIdx);
                }
                cStart++;
                r2l = false;
                d2u = true;
                // refreshSwitch();
            }
        }
        return res;
    }

    private void checkVisitedAndAdd(int[][] matrix, int row, int col) {
        if (!visitedArr[row][col]) {
            res.add(matrix[row][col]);
            visitedArr[row][col] = true;
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(JSON.toJSONString(new SpiralOrder().spiralOrder(arr)));
    }
}
