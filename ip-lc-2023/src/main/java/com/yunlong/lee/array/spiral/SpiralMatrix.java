package com.yunlong.lee.array.spiral;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 7/3/23 11:20 上午
 * @link https://leetcode.cn/problems/spiral-matrix/
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (null == matrix) {
            return result;
        }
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;

        if (rowLength == 0 || columnLength == 0) {
            return result;
        }
        int left = 0;
        int right = columnLength - 1;
        int top = 0;
        int bottom = rowLength - 1;
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            if (right > left && bottom > top) {
                for (int j = right - 1; j > left; j--) {
                    result.add(matrix[bottom][j]);
                }
                for (int i = bottom; i > top; i--) {
                    result.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }

    public static void main(String[] args) {
        // int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix = new int[][]{{3}, {2}};
        System.out.println(JSON.toJSONString(new SpiralMatrix().spiralOrder(matrix)));
    }
}
