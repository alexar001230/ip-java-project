package com.yunlonglee.arr;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 17. 电话号码的字母组合
 * @date 24/2/22 5:39 上午
 */
public class LetterCombination {

    public List<String> letterCombinations(String digits) {
        Map<Integer,String> num2LettersMap = new HashMap<>();
        num2LettersMap.put(2,"abc");
        num2LettersMap.put(3,"def");
        num2LettersMap.put(4,"ghi");
        num2LettersMap.put(5,"jkl");
        num2LettersMap.put(6,"mno");
        num2LettersMap.put(7,"pqrs");
        num2LettersMap.put(8,"tuv");
        num2LettersMap.put(9,"wxyz");

        char[] chars = digits.toCharArray();

        List<List<Character>> charsList = new ArrayList<>();

        for(char aChar:chars){
            int aInt = Integer.parseInt(String.valueOf(aChar));
            String letters = num2LettersMap.get(aInt);
            char[] chars1 = letters.toCharArray();
            List<Character> aList = new ArrayList<>();
            for(char c:chars1){
                aList.add(c);
            }
            charsList.add(aList);
        }

        for(List<Character> characterList:charsList){

        }

        return null;

    }
}
