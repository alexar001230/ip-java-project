package com.yunlonglee.binTree;

import java.util.Arrays;

/**
 * @author lijie
 * @version 1.0
 * @description 不同二叉搜索树
 * @date 23/7/21 10:40 上午
 */
public class DiffBinSearchTree {
    //1.给定数字的全排列
    //2.生成二叉树
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        int[] result = new int[6];
        arrangementSort(arr, result, 0);
    }

    public static void arrangementSort(int[] a, int[] result, int resultIndex) {
        int result_length = result.length;
        if (resultIndex >= result_length) {
            System.out.println(Arrays.toString(result));  // 输出排列结果
            return;
        }
        for (int i = 0; i < a.length; i++) {
            // 判断待选的数是否存在于排列的结果中
            boolean exist = false;
            for (int j = 0; j < resultIndex; j++) {
                if (a[i] == result[j]) {  // 若已存在，则不能重复选
                    exist = true;
                    break;
                }
            }
            if (!exist) {  // 若不存在，则可以选择
                result[resultIndex] = a[i];
                arrangementSort(a, result, resultIndex + 1);
            }
        }
    }

}
