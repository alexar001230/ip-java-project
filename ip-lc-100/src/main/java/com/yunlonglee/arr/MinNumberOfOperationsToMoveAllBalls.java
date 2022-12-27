package com.yunlonglee.arr;

import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 1769.移动所有球到每个盒子所需的最小操作数
 * @date 2/12/22 3:27 下午
 * @link https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 */
public class MinNumberOfOperationsToMoveAllBalls {
    public static void main(String[] args) {
        String boxes = "001011";
        int[] minOpArr = new int[]{11, 8, 5, 4, 3, 4};
        Assert.check(Arrays.equals(minOpCalculate(boxes), minOpArr));
    }


    private static int[] minOpCalculate(String boxes) {
        int[] balls = new int[boxes.length()];
        char[] boxesChars = boxes.toCharArray();
        int rows = 0;
        for (int i = 0; i < boxesChars.length; i++) {
            balls[i] = Integer.parseInt(String.valueOf(boxesChars[i]));
            if (balls[i] == 1) {
                rows++;
            }
        }


        //1.计算数组中有多少个1

        //2.对于每个1,计算到每个格子中的步数
        List<Integer[]> min1Arrs = new ArrayList<>();
        for (int i = 0; i < balls.length; i++) {
            if (balls[i] == 1) {
                Integer[] newArr = new Integer[balls.length];
                for (int j = 0; j < balls.length; j++) {
                    newArr[j] = Math.abs(i - j);
                }
                min1Arrs.add(newArr);
            }
        }
        //3.矩阵列相加
        int[] resultArr = new int[balls.length];
        while (true) {
            int i = 0;
            if (i < min1Arrs.size()) {
                for (int j = 0; j < min1Arrs.get(i).length; j++) {
                    if (i < j) {
                        resultArr[i] += resultArr[i];
                    } else {
                        break;
                    }

                }
            }
        }
    }
}
