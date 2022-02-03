package com.yunlonglee.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description 有效的字母异位词
 * @date 3/2/22 3:31 下午
 */
public class IsAnagram {
    public static boolean isAnagram(String s, String t) {
        if(null == s && null != t){
            return false;
        }else if(null == s && null == t){
            return true;
        }

        if(s.length()!=t.length()){
            return false;
        }

        char[] sArr = s.toCharArray();
        Map<Character,Integer> sChart2NumsMap = new HashMap<>();
        for(int i=0;i<sArr.length;i++){
            Integer charNums = sChart2NumsMap.get(sArr[i]);
            if(null!=charNums && charNums>0){
                sChart2NumsMap.put(sArr[i],charNums+1);
            }else{
                sChart2NumsMap.put(sArr[i],1);
            }
        }

        char[] tArr = t.toCharArray();

        for(int i=0;i<tArr.length;i++){
            if(null == sChart2NumsMap.get(tArr[i])){
                return false;
            }else{
                Integer charNums = sChart2NumsMap.get(tArr[i]);
                sChart2NumsMap.put(tArr[i],charNums-1);
            }
        }

        for(Map.Entry entry:sChart2NumsMap.entrySet()){
            Integer value = (Integer) entry.getValue();
            if(value!=0){
                return false;
            }
        }

        return true;

    }


    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s,t));
    }
}
