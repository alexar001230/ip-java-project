package com.yunlong.lee.dataStructure.str;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author lijie
 * @version 1.0
 * @description 字符串解码
 * @date 16/3/23 4:38 下午
 * @link https://leetcode.cn/problems/decode-string/
 */
public class DecodeString {
    private int cellCnt = 0;
    private char sep_tag = '|';

    int ptr;

    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }



    private LinkedList<String> codeStack = new LinkedList<>();
    private LinkedList<String> cntStack = new LinkedList<>();

    public String myDecodeString(String s) {
        StringBuilder res = new StringBuilder();
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] != ']') {
                if (charArr[i] >= '0' && charArr[i] <= '9') {
                    if (i > 0) {
                        //前一个是数字,需要重新组合成新的数字,再次压栈
                        if (charArr[i - 1] >= '0' && charArr[i - 1] <= '9') {
                            String oldCnt = cntStack.poll();
                            cntStack.push(oldCnt + charArr[i]);
                        } else {
                            cntStack.push(String.valueOf(charArr[i]));
                        }
                    } else {
                        cntStack.push(String.valueOf(charArr[i]));
                    }

                } else {
                    codeStack.push(String.valueOf(charArr[i]));
                }
            } else {
                int curCellCnt = Integer.parseInt(cntStack.poll());
                String cellUnit = "";
                while (!codeStack.peek().equals("[")) {
                    cellUnit = codeStack.pop() + cellUnit;
                }
                codeStack.poll();//左括号抛栈
                String curSegment = "";
                for (int j = 0; j < curCellCnt; j++) {
                    curSegment += cellUnit;
                }
                if (!codeStack.isEmpty()) {
                    String oldTop = codeStack.pop();
                    codeStack.push(oldTop + curSegment);//这里合并后可能会导致结果为空
                } else {
                    res.append(curSegment);
                }
            }
        }
        if (!codeStack.isEmpty()) {
            StringBuilder newRes = new StringBuilder();
            while (!codeStack.isEmpty()) {
                newRes.append(codeStack.pollLast());
            }
            if (res.length() == 0) {
                return newRes.toString();
            } else {
                return res.toString() + newRes.toString();
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        System.out.println(new DecodeString().decodeString(s));
    }
}
