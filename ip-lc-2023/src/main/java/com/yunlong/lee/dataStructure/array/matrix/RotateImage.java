package com.yunlong.lee.dataStructure.array.matrix;

/**
 * @author lijie
 * @version 1.0
 * @description 48. 旋转图像
 * @date 15/5/23 3:53 下午
 * @link https://leetcode.cn/problems/rotate-image/description/
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        doRotate(matrix);
    }

    private void doRotate(int[][] matrix) {
        rotate90(matrix);
    }

    private void rotate90(int[][] matrix) {
        int colCnt = matrix[0].length;
        int rowCnt = matrix.length;

        int rowStart = 0;
        int rowEnd = matrix.length - 1;

        //行水平翻转
        while (rowStart <= rowEnd) {
            int[] tmpArr = new int[matrix[0].length];
            System.arraycopy(matrix[rowStart], 0, tmpArr, 0, colCnt);
            System.arraycopy(matrix[rowEnd], 0, matrix[rowStart], 0, colCnt);
            System.arraycopy(tmpArr, 0, matrix[rowEnd], 0, colCnt);
            rowStart++;
            rowEnd--;
        }
        //对角线翻转
        for (int i = 0; i < rowCnt; i++) {
            for (int j = i; j < colCnt; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
