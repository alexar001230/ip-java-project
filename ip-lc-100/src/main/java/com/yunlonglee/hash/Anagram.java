package com.yunlonglee.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 17/1/22 10:24 下午
 */
public class Anagram {
    public boolean isAnagram(String s, String t) {
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Map<Character,Integer> sCharNumsMap = new HashMap<>();
        for(int i=0;i<sCharArray.length;i++){
            Character charInS = sCharArray[i];
            Integer charNums = sCharNumsMap.get(charInS);
            if(null != charNums){
                sCharNumsMap.put(charInS,charNums+1);
            }else{
                sCharNumsMap.put(charInS,1);
            }
        }
        for(int i=0;i<tCharArray.length;i++){
            Character charInT = tCharArray[i];
            Integer charNums = sCharNumsMap.get(charInT);
            if(null == charNums || charNums == 0){
                return false;
            }
            sCharNumsMap.put(charInT,charNums-1);
        }
        for(Map.Entry entry:sCharNumsMap.entrySet()){
            Character character = (Character)entry.getKey();
            Integer nums = (Integer) entry.getValue();
            if(nums >0){
                return false;
            }
        }
        return true;
    }
}
