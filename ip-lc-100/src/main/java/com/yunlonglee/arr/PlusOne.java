package com.yunlonglee.arr;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 27/1/22 4:00 上午
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return new int[0];
        }
        int lastIndex = digits.length - 1;
        digits[lastIndex] = digits[lastIndex] + 1;
        for (int i = lastIndex; i > 0; i--) {
            if (digits[i] >= 10) {
                digits[i] = digits[i] % 10;
                digits[i - 1] = digits[i - 1] + 1;
            }
        }
        if (digits[0] >= 10) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            digits[0] = digits[0] % 10;
            for (int i = 1; i <= digits.length; i++) {
                result[i] = digits[i - 1];
            }
            return result;
        } else {
            return digits;
        }

    }


}
