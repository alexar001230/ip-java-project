package com.yunlonglee.util;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 29/1/22 2:08 上午
 */
public class PrintUtils {
    public static void print(Object... toPrint) {
        for (int i = 0; i < toPrint.length; i++) {
            System.out.println(toPrint[i]);
        }
    }
}
