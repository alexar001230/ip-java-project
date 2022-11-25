package com.yunlonglee.arr;

/**
 * @author lijie
 * @version 1.0
 * @description 剑指 Offer 04. 二维数组中的查找
 * @date 2/3/22 1:16 上午
 */
public class FindNumberIn2DArray {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int rowSize = matrix.length;
        int columnSize = matrix[0].length;
        for (int i = 0; i < rowSize; i++) {
            if (columnSize == 0) {
                continue;
            }
            if(matrix[i][0] > target || matrix[i][columnSize-1] < target){
                continue;
            }
            for (int j = 0; j < columnSize; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
