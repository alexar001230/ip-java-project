package com.yunlonglee.rotateMatrix;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 9/11/21 2:26 下午
 */
public class Solution {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(matrix);
    }


    public static void rotate(int[][] matrix) {
        System.out.println(JSON.toJSONString(matrix));
        final int[][] example = matrix;
        // 1.转换成列表
        List<List<Integer>> intsList = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            List<Integer> intsRow = new ArrayList<>();
            for (int j = 0; j < matrix[i].length; j++) {
                intsRow.add(matrix[i][j]);
            }
            intsList.add(intsRow);
        }
        // 2.倒序遍历列表
        List<List<Integer>> rotatedList = new ArrayList<>();
        IntStream.range(0, intsList.get(0).size()).forEach(i -> {
            List<Integer> newInts = new ArrayList<>();
            for (int j = intsList.get(i).size() - 1; j >= 0; j--) {
                newInts.add(example[j][i]);
            }
            rotatedList.add(newInts);
        });
        // 3.列表转矩阵
        int[][] arr = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < rotatedList.size(); i++) {
            for (int j = 0; j < rotatedList.get(i).size(); j++) {
                arr[i][j] = rotatedList.get(i).get(j);
            }
        }
        matrix = arr;
        System.out.println(JSON.toJSONString(matrix));
    }
}
