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

    //region 利用规律,数字个数为x，则范围在 10^(x-1)~10^x-1
    private int doFindNthDigit(int n) {
        int weishu = 1, count = 9;
        long weishuLen = weishu * count;
        while (n >  weishuLen) {
            n -= weishu * count;
            weishu++;
            count *= 10;
            weishuLen = (long)weishu * count;
        }
        int index = n - 1;
        int start = (int) Math.pow(10, weishu - 1);
        int num = start + index / weishu;
        int digitIndex = index % weishu;
        int digit = (num / (int) (Math.pow(10, weishu - digitIndex - 1))) % 10;
        return digit;
    }

    private int doFindNthDigit1(int n) {
        int d = 1, count = 9;
        while (n > (long) d * count) {
            n -= d * count;
            d++;
            count *= 10;
        }
        int index = n - 1;
        int start = (int) Math.pow(10, d - 1);
        int num = start + index / d;
        int digitIndex = index % d;
        int digit = (num / (int) (Math.pow(10, d - digitIndex - 1))) % 10;
        return digit;
    }
    //endregion

    //region 超出时间范围
    private int doFindNthDigitMy(int n) {
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
    //endregion

    public static void main(String[] args) {
        int n = 1000000000;
        System.out.println(new NthNo().findNthDigit(n));
    }
}
