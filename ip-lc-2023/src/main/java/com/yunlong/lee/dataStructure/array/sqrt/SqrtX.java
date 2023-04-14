package com.yunlong.lee.dataStructure.array.sqrt;

/**
 * @author lijie
 * @version 1.0
 * @description 69. x 的平方根
 * @date 14/4/23 11:11 上午
 * @link https://leetcode.cn/problems/sqrtx/
 */
public class SqrtX {
    public int mySqrt(int x) {
        return doSqrt2(x);
    }

    private int doSqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        Double low = new Double(1);
        Double high = (low + x) / 2;

        while (low <= high) {
            int lowInt = low.intValue();
            int highInt = high.intValue();
            int mid = (int) ((low + high) / 2);
            if (x - low * low < 1) {
                return lowInt;
            }
            if (high * high - x < 1) {
                return highInt;
            }
            if (high * high - x > 1) {
                high = new Double(mid);
            }

            if (low * low + 1 < x) {
                low = new Double(mid);
            }
        }
        return (int) ((low + high) / 2);
    }

    private int doSqrt2(int x) {
        int left = 0;
        int right = x;
        int result = -1;
        int mid = (left + right) / 2;
        while (left <= right) {
            mid = left + (right - left) / 2;
            long midMid = (long) mid * mid;
            if (midMid <= x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int x = 2147395599;
        System.out.println(new SqrtX().mySqrt(x));
    }
}
