package com.yunlong.lee.utils.arr;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 20/4/23 12:36 下午
 * @link
 */
public class ArrUtils {
    public static String str2Arr(String arrStr) {
        String s1 = arrStr.replaceAll("\\[", "{");
        String s2 = s1.replaceAll("]", "}");
        return s2;
    }


    public static String str2Arr1(String arrStr) {
        String s1 = arrStr.replaceAll("\\[", "{");
        String s2 = s1.replaceAll("]", "}");
        return "int[] arr = new int[]" + s2 + ";";
    }

    public static String str2Arr2(String arrStr) {
        String s1 = arrStr.replaceAll("\\[", "{");
        String s2 = s1.replaceAll("]", "}");
        return "int[][] arr = new int[][]" + s2 + ";";
    }

}
