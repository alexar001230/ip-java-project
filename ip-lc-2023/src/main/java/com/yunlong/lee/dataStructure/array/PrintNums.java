package com.yunlong.lee.dataStructure.array;

/**
 * @author lijie
 * @version 1.0
 * @description 剑指 Offer 17. 打印从1到最大的n位数
 * @date 6/4/23 1:40 下午
 * @link https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 */
public class PrintNums {
    public int[] printNumbers(int n) {
        return doPrintNumbers(n);
    }

    private int[] doPrintNumbers(int n) {
        if (n == 0) {
            return null;
        }
        //1.先求最大值
        int base = 1;
        int nMax = -1;
        for (int i = 0; i < n; i++) {
            base = base * 10;
        }
        nMax = base + nMax;
        //2.存起来
        int[] arrN = new int[nMax];
        for (int i = 1; i <= nMax; i++) {
            arrN[i - 1] = i;
        }
        return arrN;
    }
}
