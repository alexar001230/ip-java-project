package com.yunlonglee.arr;

/**
 * @author lijie
 * @version 1.0
 * @description 739. 每日温度
 * @date 9/3/22 2:01 下午
 */
public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length - 1; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
                if (j == temperatures.length - 1) {
                    result[i] = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        dailyTemperatures(temperatures);
    }
}
