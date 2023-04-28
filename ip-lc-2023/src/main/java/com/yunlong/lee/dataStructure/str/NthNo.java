package com.yunlong.lee.dataStructure.str;

/**
 * @author lijie
 * @version 1.0
 * @description 400. 第 N 位数字
 * @date 27/4/23 8:16 下午
 * @link https://leetcode.cn/problems/nth-digit/
 */
public class NthNo {
    public int findNthDigit(int n) {
        return doFindNthDigit(n);
    }

    private int doFindNthDigit(int n) {
        int nCopy = n;
        int result = 0;
        for (int i = 1; i <= n; i++) {
            String iStr = String.valueOf(i);
            int len = iStr.length();
            nCopy -= len;
            if (nCopy > 0) {
                continue;
            } else {
                if (nCopy == 0) {
                    result =
                            Integer.parseInt(String.valueOf(iStr.charAt(len - 1)));
                } else {
                    nCopy = Math.abs(nCopy);
                    result = Integer.parseInt(iStr.substring(nCopy - 1, nCopy));
                }
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 11;
        System.out.println(new NthNo().findNthDigit(n));
    }
}
