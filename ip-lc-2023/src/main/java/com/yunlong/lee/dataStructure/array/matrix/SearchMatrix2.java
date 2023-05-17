package com.yunlong.lee.dataStructure.array.matrix;

/**
 * @author lijie
 * @version 1.0
 * @description 240. 搜索二维矩阵 II
 * @date 15/5/23 5:06 下午
 * @link https://leetcode.cn/problems/search-a-2d-matrix-ii/description/
 */
public class SearchMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        return doSearchMatrix(matrix, target);
    }


    private boolean doSearchMatrix(int[][] matrix, int target) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        //从右上角开始搜索，往下都比他大，往左都比他小
        int rowIdx = 0;
        int colIdx = colLen - 1;

        while (rowIdx <= rowLen - 1 && colIdx >= 0) {
            int curNo = matrix[rowIdx][colIdx];
            if (curNo == target) {
                return true;
            }else{
                if (curNo > target) {
                    //向左
                    colIdx--;
                } else {
                    //向下
                    rowIdx++;
                }
            }
        }
        return false;
    }
}
