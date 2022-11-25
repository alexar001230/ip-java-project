package com.yunlonglee.other;

import com.alibaba.fastjson.JSON;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 23/2/22 12:47 上午
 */
public class TimesTable {
    public static void timesTablePrint() {
        String[][] timesTable = new String[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j >= i) {
                    timesTable[i][j] =
                            (i+1) + "*" + (j+1) + "=" + (i+1) * (j+1);
                } else {
                    timesTable[i][j] = "    ";
                }
            }
        }
        System.out.println(JSON.toJSONString(timesTable));
    }

    public static void main(String[] args) {
        timesTablePrint();
    }
}
