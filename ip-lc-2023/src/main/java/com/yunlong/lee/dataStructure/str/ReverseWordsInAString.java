package com.yunlong.lee.dataStructure.str;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 151. 反转字符串中的单词
 * @date 18/4/23 2:59 下午
 * @link https://leetcode.cn/problems/reverse-words-in-a-string/
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        return doReverseWordByStack(s);
    }

    //region O(1)做法,整体求逆,在单个求逆
    private String doReverseWordByO1(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) == ' ') {
                left++;
                continue;
            }
            if (s.charAt(right) == ' ') {
                right--;
                continue;
            }
            if (s.charAt(left) != ' ' && s.charAt(right) != ' ') {
                break;
            }
        }
        //1.str整体求逆
        // while(left <= right){
        //     char tmp = s.charAt(left);
        //     s.re = s.charAt(right);
        // }
        return "";

    }
    //


    //region 借助栈做,逻辑简单很多，借助StringBuilder记忆单词，left一直右移，到空时停止，不用两层循环,
    private String doReverseWordByStack(String s) {
        Deque<String> stack = new ArrayDeque<>();
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) == ' ') {
                left++;
                continue;
            }
            if (s.charAt(right) == ' ') {
                right--;
                continue;
            }
            if (s.charAt(left) != ' ' && s.charAt(right) != ' ') {
                break;
            }
        }
        // for (int i = left; i <= right - 1; ) {
        //     if (s.charAt(left) == ' ') {
        //         continue;
        //     }
        //     for (int j = i + 1; j <= right; j++) {
        //         if (s.charAt(j) == ' ' || j == right) {
        //             String aWord = s.substring(i, j);
        //             stack.push(aWord);
        //             i = j;
        //             break;
        //         }
        //     }
        // }
        StringBuilder aWord = new StringBuilder();
        while (left <= right) {
            char curChar = s.charAt(left);
            if (curChar == ' ' && aWord.length() != 0) {
                stack.push(aWord.toString());
                aWord.setLength(0);
            } else if (curChar != ' ') {
                aWord.append(curChar);
            }
            left++;
        }
        stack.push(aWord.toString());
        return String.join(" ", stack);
    }
    //endregion


    //region 不借助数据结构边界问题考虑
    private String doReverseWords(String s) {
        if (Objects.isNull(s) || s.length() == 0 || s.length() == 1) {
            return s;
        }
        //1.先去除前后空格
        int leftMax = 0;
        int rightMin = s.length() - 1;
        while (leftMax <= rightMin && s.charAt(leftMax) == ' ') {
            leftMax++;
        }

        while (leftMax <= rightMin && s.charAt(rightMin) == ' ') {
            rightMin--;
        }

        int left = leftMax;
        int right = leftMax + 1;
        String sb = "";
        String sep = " ";
        while (right <= rightMin) {
            if (s.charAt(left) == ' ') {
                left++;
                continue;
            }
            right = left + 1;
            while (right <= rightMin && s.charAt(right) != ' ') {
                right++;
                if (right > rightMin) {
                    break;
                }
            }
            String aWord = s.substring(left, right);
            if (right == rightMin + 1) {
                sb = aWord + sb;
            } else {
                sb = sep + aWord + sb;
            }
            left = right;
        }
        return sb;
    }
    //endregion

    public static void main(String[] args) {
        // String s = "the sky is blue";
        String s = "a good   example";

        System.out.println(new ReverseWordsInAString().reverseWords(s));
    }
}
