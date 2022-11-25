package com.yunlonglee.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description 383. 赎金信
 * @date 4/2/22 6:19 上午
 */
public class CanConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(null==ransomNote || ransomNote.length() == 0){
            return false;
        }
        Map<Character, Integer> charStr2NumsMap = new HashMap<>();
        char[] mArr = magazine.toCharArray();
        for (char aChar : mArr) {
            if (null != charStr2NumsMap.get(aChar)) {
                charStr2NumsMap.put(aChar, charStr2NumsMap.get(aChar) + 1);
            } else {
                charStr2NumsMap.put(aChar, 1);
            }
        }
        char[] rArr = ransomNote.toCharArray();
        for (char aCh : rArr) {
            if (null == charStr2NumsMap.get(aCh)) {
                return false;
            } else {
                if (charStr2NumsMap.get(aCh) - 1 < 0) {
                    return false;
                }
                charStr2NumsMap.put(aCh, charStr2NumsMap.get(aCh) - 1);
            }
        }
        return true;
    }
}
