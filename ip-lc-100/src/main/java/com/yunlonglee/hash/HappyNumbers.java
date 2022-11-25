package com.yunlonglee.hash;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 17/1/22 11:00 下午
 */
public class HappyNumbers {

    public static boolean isHappy2(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = genSum(n);
        }
        return n == 1;
    }


    public static boolean isHappy(int n) {
        String nStr = String.valueOf(n);
        Map<String, Integer> map = new HashMap<>();
        map.put(nStr, n);
        HashSet<Integer> set = new HashSet<>();
        List<Integer> nList = new ArrayList<>();
        int i = 1;
        nList.add(n);
        int curNum = n;
        while (true) {
            if (curNum == 1) {
                return true;
            }
            if (!set.contains(curNum)) {
                set.add(curNum);
            } else {
                break;
            }
            curNum = genSum(nList.get(i - 1));
            nList.add(curNum);
        }
        return false;
    }

    private static int genSum(int n) {
        int sum = 0;
        String nStr = String.valueOf(n);
        char[] nArr = nStr.toCharArray();
        for (int i = 0; i < nArr.length; i++) {
            int num = Integer.parseInt(String.valueOf(nArr[i]));
            sum = sum + num * num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 2;
        int a = 2;
        String nStr = String.valueOf(n);
        String aStr = String.valueOf(a);
        Map<String,Integer> map = new HashMap<>();
        map.put(nStr,n);
        map.put(aStr,a);
        System.out.println(JSON.toJSONString(map));
        System.out.println(nStr.equals(aStr));
        System.out.println(isHappy2(n));
    }
}
