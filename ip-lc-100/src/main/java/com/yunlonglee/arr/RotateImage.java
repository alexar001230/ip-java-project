package com.yunlonglee.arr;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 48. 旋转图像
 * @date 19/2/22 10:21 下午
 */
public class RotateImage {
    //
    public static void rotateByReverse(int[][] matrix) {
        //先水平反转,再对角线反转
        int n = matrix.length;
        int i = 0;
        int j = n - 1;
        while (i < n / 2) {
            for (int k = 0; k < n; k++) {
                int tmp = matrix[i][k];
                matrix[i][k] = matrix[j][k];
                matrix[j][k] = tmp;
            }
            i++;
            j--;
        }
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < a; b++) {
                int tmp = matrix[a][b];
                matrix[a][b] = matrix[b][a];
                matrix[b][a] = tmp;
            }
        }
    }

    //借用另一个二维数组旋转,关键点在于matrix[j][matrix.length - i - 1] = matrixNew[i][j];
    public static void rotateByAnotherArray(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return;
        }
        int n = matrix.length;
        int[][] matrixNew = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixNew[i][j] = matrix[i][j];
            }
        }


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][matrix.length - i - 1] = matrixNew[i][j];
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3,4}, { 5, 6,7,8}, { 9,10,11,12},{13,14,15,
                16}};
        rotateByReverse(matrix);
        System.out.println(JSON.toJSONString(matrix));
    }
}
