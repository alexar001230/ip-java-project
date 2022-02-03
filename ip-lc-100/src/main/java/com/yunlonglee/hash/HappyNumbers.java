package com.yunlonglee.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 17/1/22 11:00 下午
 */
public class HappyNumbers {
    public boolean isHappy(int n) {
        String nStr = String.valueOf(n);
        Map<String,Integer> map = new HashMap<>();
        map.put(nStr,n);
        List<Integer> nList = new ArrayList<>();
        int i = 1;
        nList.add(n);
        while(true){
            int curNum = genSum(nList.get(i-1));
            if(curNum == 1){
                return true;
            }
            if(null!=map.get(String.valueOf(curNum))){
                return false;
            }
            nList.add(curNum);
            i++;
        }
    }

    private int genSum(int n){
        int sum = 0;
        String nStr = String.valueOf(n);
        char[] nArr = nStr.toCharArray();
        for(int i=0;i<nArr.length;i++){
            int num = Integer.parseInt(String.valueOf(nArr[i]));
            sum = sum+num*num;
        }
        return sum;
    }
}
