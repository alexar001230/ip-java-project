package com.yunlonglee.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 16/1/22 1:15 上午
 */
public class ReverseWords {

    public static void main(String[] args) {
        String str = "  hello world  ";
        System.out.println(reverseWords(str));
    }


    public static String reverseWords(String s) {
        String[] strArr = s.split(" ");
        List<String> result = new ArrayList<>();
        for (int i = strArr.length - 1; i >= 0; i--) {
            if (!strArr[i].equals(" ") && !strArr[i].equals("")) {
                result.add(strArr[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if(i!= result.size()-1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
