package com.yunlonglee.arr;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 49. 字母异位词分组
 * @date 13/3/22 10:26 下午
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> orderStr2StrsMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String orderedStr = "";
            for (char aChar : chars) {
                orderedStr = orderedStr + aChar;
            }
            List<String> inMap = orderStr2StrsMap.get(orderedStr);
            if (null == inMap) {
                List<String> aList = new ArrayList<>();
                aList.add(str);
                orderStr2StrsMap.put(orderedStr, aList);
            } else {
                inMap.add(str);
                orderStr2StrsMap.put(orderedStr,inMap);
            }
        }

        List<List<String>> lists = new ArrayList<>();
        orderStr2StrsMap.forEach((str, list) -> lists.add(list));
        return lists;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strs);
    }
}
