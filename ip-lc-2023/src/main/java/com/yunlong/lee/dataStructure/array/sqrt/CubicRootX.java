package com.yunlong.lee.dataStructure.array.sqrt;

import java.math.BigDecimal;

/**
 * @author lijie
 * @version 1.0
 * @description 立方根, 控制在一定的精度内
 * @date 14/4/23 12:55 下午
 * @link
 */
public class CubicRootX {
    public double myCubicRootX(double x) {
        double jingdu = 0;
        double left = 0;
        double right = x;
        if (x < 0) {
            double tmp = left;
            left = right;
            right = tmp;
        }

        Double result = new Double(0);
        while (left <= right) {
            double mid = (left + right) / 2;
            double midMidMid = mid * mid * mid;
            if ((midMidMid + jingdu) <= x) {
                result = mid;
                left = mid + jingdu / 10;
            } else {
                right = mid - jingdu / 10;
            }
        }
        return new BigDecimal(result).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
    }

    public static void main(String[] args) {
        double x = new Double(-2);
        System.out.println(new CubicRootX().myCubicRootX(x));
    }
}
