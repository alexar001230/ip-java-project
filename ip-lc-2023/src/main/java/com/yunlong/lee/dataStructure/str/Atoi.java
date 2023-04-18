package com.yunlong.lee.dataStructure.str;

import java.util.*;

/**
 * @author lijie
 * @version 1.0
 * @description 8. 字符串转换整数 (atoi)
 * @date 14/4/23 7:50 下午
 * @link https://leetcode.cn/problems/string-to-integer-atoi/
 */
public class Atoi {


    public int myAtoi(String s) {
        return doMyAtoiByAutomaton(s);
    }


    //region 采用自动机/状态机原理做转换，更聚合清晰

    public int doMyAtoiByAutomaton(String s) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < s.length(); i++) {
            if (!automaton.isEnd()) {
                automaton.migrate(s.charAt(i));
            } else {
                break;
            }
        }
        return automaton.sign * automaton.value.intValue();
    }

    class Automaton {
        private String Start = "start";
        private String Signed = "signed";
        private String InNumber = "inNumber";
        private String End = "end";


        public int sign;
        public Long value = 0L;
        private String state = "start";

        //dfa 状态机迁移表
        private Map<String, String[]> stateMigratingMap =
                new HashMap<String, String[]>() {{
                    put(Start, new String[]{Start, Signed, InNumber, End});
                    put(Signed, new String[]{End, End, InNumber, End});
                    put(InNumber, new String[]{End, End, InNumber, End});
                    put(End, new String[]{End, End, End, End});
                }};

        //自动机是否已经到达终态
        private boolean isEnd() {
            return state.equals(End);
        }

        //自动机状态迁移
        private void migrate(char curChar) {
            String curState = this.state;
            String[] nextStateArr = stateMigratingMap.get(curState);
            String nextState = nextStateArr[getNextStateIdxByCurChar(curChar)];
            if (nextState.equals(InNumber)) {
                this.value = this.value * 10 + curChar - '0';
                this.value = this.sign == 1 ?
                        Math.min(this.value, Integer.MAX_VALUE)
                        : Math.min(this.value, -(long) Integer.MIN_VALUE);
            } else if (nextState.equals(Signed)) {
                this.sign = curChar == '+' ? 1 : -1;
            }
            this.state = nextState;
        }

        //在迁移表中寻找下一个状态,这里将不可达状态和可达状态统一编码,很好
        private int getNextStateIdxByCurChar(char curChar) {
            if (curChar == ' ') {
                return 0;
            }

            if (curChar == '+' || curChar == '-') {
                return 1;
            }

            if (Character.isDigit(curChar)) {
                return 2;
            }
            return 3;
        }
    }
    //endregion


    //region 字符串转换问题，明确转换规则，尽量根据规则编写对应转换子函数
    //直接上手写程序，容易写出如下及其臃肿的代码


    private LinkedList<Character> validNoChars = new LinkedList<>();
    private Set<Character> fuhaoSet = new HashSet<>();
    private Set<Character> numSet = new HashSet<>();

    private int doMyAtoi(String s) {
        fuhaoSet.add('-');
        fuhaoSet.add('+');
        boolean isNegative = false;
        int i = 0;
        while (true) {
            if (i > s.length() - 1) {
                break;
            }
            char charOfI = s.charAt(i);
            //非数字字符
            if (!validNoChars.isEmpty() && charOfI < '0' && charOfI > '9') {
                break;
            }
            //读取有效字符
            if (fuhaoSet.contains(charOfI) || (charOfI >= '0' && charOfI <= '9')) {
                if (charOfI == '-') {
                    isNegative = true;
                    i++;
                    continue;
                }
                //去掉前导0
                if (validNoChars.isEmpty() && charOfI == '0') {
                    i++;
                    continue;
                }
                validNoChars.add(charOfI);
            }
            i++;
        }
        Long numRes = new Long(0);
        int j = validNoChars.size() - 1;
        while (j >= 0) {
            numRes += (validNoChars.get(j) - '0') * tenTimes(validNoChars.size() - 1 - j);
            if (numRes > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            j--;
        }
        if (isNegative) {
            if (-numRes < new Long(Integer.MIN_VALUE)) {
                return Integer.MIN_VALUE;
            }
            return -numRes.intValue();
        }
        return numRes.intValue();
    }

    private int tenTimes(int times) {
        if (times == 0) {
            return 1;
        }
        int res = 1;
        for (int i = 1; i <= times; i++) {
            res = res * 10;
        }
        return res;
    }
    //endregion

    public static void main(String[] args) {
        // String s = "42";
        String s = "   -42";
        System.out.println(new Atoi().myAtoi(s));
    }
}
