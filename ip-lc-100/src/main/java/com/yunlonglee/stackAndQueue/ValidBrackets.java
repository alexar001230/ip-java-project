package com.yunlonglee.stackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 有效的括号
 * @date 29/1/22 2:39 上午
 */
public class ValidBrackets {

    private static Map<Character,Character> VALID_BRACKETS_MAP = new HashMap<>();

    public static boolean isValid(String s) {
        VALID_BRACKETS_MAP.put(')','(');
        VALID_BRACKETS_MAP.put(']','[');
        VALID_BRACKETS_MAP.put('}','{');
        if (null == s) {
            return false;
        }
        if (s.trim().length() == 0) {
            return true;
        }
        char[] charArr = s.toCharArray();
        Stack<Character> charStack = new Stack<>();
        for (int i = 0; i < charArr.length; i++) {
            if(charArr[i] == '(' || charArr[i] == '[' || charArr[i] == '{'){
                charStack.push(charArr[i]);
                continue;
            }
            if(charArr[i] == ')' || charArr[i] == ']' || charArr[i] == '}'){
                if(!charStack.isEmpty() ){
                    if(VALID_BRACKETS_MAP.get(charArr[i]).equals(charStack.peek())){
                        charStack.pop();
                        continue;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        if(charStack.isEmpty()){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        String s = "()]]{}";
        System.out.println(isValid(s));
    }
}
