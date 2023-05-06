package com.yunlong.lee.dataStructure.array;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description 1089. 复写零
 * @date 4/5/23 2:20 下午
 * @link https://leetcode.cn/problems/duplicate-zeros/
 */
public class DuplicateZeros {
    public void duplicateZeros(int[] arr) {
        doDuplicateZeros(arr);
    }

    private void doDuplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; ) {
            if (arr[i] == 0) {
                for (int j = arr.length - 1; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[i + 1] = 0;
                i = i + 2;
            } else {
                i++;
            }
        }
        System.out.println(JSON.toJSONString(arr));
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        new DuplicateZeros().duplicateZeros(arr);
    }
}
