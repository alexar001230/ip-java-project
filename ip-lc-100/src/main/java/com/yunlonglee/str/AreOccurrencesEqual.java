package com.yunlonglee.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lijie
 * @version 1.0
 * @description 1941. 检查是否所有字符出现次数相同
 * @date 13/2/22 8:20 下午
 */
public class AreOccurrencesEqual {
    public boolean areOccurrencesEqual(String s) {
        char[] sArr = s.toCharArray();
        Map<Character, Integer> char2NumsMap = new HashMap<>();
        for (char aChar : sArr) {
            if (null == char2NumsMap.get(aChar)) {
                char2NumsMap.put(aChar, 1);
            } else {
                char2NumsMap.put(aChar, char2NumsMap.get(aChar) + 1);
            }
        }
        Set<Integer> numsSet = new HashSet<>();
        for (Map.Entry entry : char2NumsMap.entrySet()) {
            numsSet.add((Integer) entry.getValue());
            if (numsSet.size() > 1) {
                return false;
            }
        }
        return true;
    }
}
