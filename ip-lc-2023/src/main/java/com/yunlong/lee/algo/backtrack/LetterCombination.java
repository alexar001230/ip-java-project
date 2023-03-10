package com.yunlong.lee.algo.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 3/3/23 6:37 下午
 * @link https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombination {
    //保存结果集
    static List<String> result = new ArrayList<>();
    //保存临时遍历结果
    static StringBuilder aResultSb = new StringBuilder("");

    static Map<Integer, String> No2StrMap = new HashMap<>();

    public static void initMap() {
        No2StrMap.put(2, "abc");
        No2StrMap.put(3, "def");
        No2StrMap.put(4, "ghi");
        No2StrMap.put(5, "jkl");
        No2StrMap.put(6, "mno");
        No2StrMap.put(7, "pqrs");
        No2StrMap.put(8, "tuv");
        No2StrMap.put(9, "wxyz");
    }



    public static List<String> letterCombinations(String digits) {
        //回溯三部曲(想办法将问题转换成树形结构)
        //1.确定回溯函数模版，返回值和参数，一般为void
        //2.回溯函数终止条件
        //3.回溯函数遍历搜索
        if (null == digits || digits.length() == 0) {
            return result;
        }
        initMap();
        backTracking(digits, 0);
        return result;
    }



    public static void backTracking(
            //确定参数
            String digits, int curBackTrackingTreeHeight) {
        //找回溯终止条件
        if (digits.length() == curBackTrackingTreeHeight) {
            result.add(aResultSb.toString());
            return;
        }
        String layerStr = No2StrMap.get(Integer.parseInt(String.valueOf(
                digits.charAt(curBackTrackingTreeHeight))));
        //单层遍历并回溯
        for (int i = 0; i < layerStr.length(); i++) {
            aResultSb.append(layerStr.charAt(i));
            //向下搜索
            backTracking(digits, curBackTrackingTreeHeight + 1);
            //回溯完结,将临时结果中当前层的字符删掉
            aResultSb.deleteCharAt(aResultSb.length() - 1);
        }
    }



    public static void main(String[] args) {
        String digits = "";
        System.out.println(JSON.toJSONString(letterCombinations(digits)));
    }

}
