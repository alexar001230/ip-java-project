package com.yunlong.lee.dataStructure.array.twoDimensional.spiral;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 59. 螺旋矩阵 II
 * @date 7/3/23 11:38 上午
 * @link https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        //1.方法是左闭右开，遍历每条边
        //2.循环终止条件,顺时针可转的圈数,由于是方阵，所以是loop<N/2
        //
        int[][] res = new int[n][n];

        int start = 0;//每个loop边开始遍历的地方

        int loop = 0;
        int totalLoop = n / 2;
        int cnt = 1;
        int i, j;

        while (loop++ < totalLoop) {//边界判断后，转圈数+1，初始状态表示已经开始一圈，其他情况表示往内走进一圈
            //遍历左到右，左闭右开
            //开始位置为转圈的开始位置，末端为已经转圈的位置
            for (j = start; j < n - loop; j++) {
                res[start][j] = cnt++;
            }
            //遍历上到下,左闭右开
            for (i = start; i < n - loop; i++) {
                res[i][j] = cnt++;
            }
            //遍历右到左,左闭右开
            for (; j >= loop; j--) {//因为loop++,所以这里保持右开的时候可以使用>=
                res[i][j] = cnt++;
            }
            for (; i >= loop; i--) {
                res[i][j] = cnt++;
            }
            start++;
        }
        if (n % 2 == 1) {
            res[start][start] = cnt;
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 3;
        System.out.println(JSON.toJSONString(new SpiralMatrix2().generateMatrix(n)));
    }
}
