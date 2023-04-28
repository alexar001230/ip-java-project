package com.yunlong.lee.dataStructure.stackAndQAndHash.stack;


import com.yunlong.lee.utils.ds.DSUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijie
 * @version 1.0
 * @description 155. 最小栈
 * @date 10/3/23 5:21 下午
 * @link https://leetcode.cn/problems/min-stack/
 */
public class MyMinStack {


    //region mySolution
    int minVal = Integer.MAX_VALUE;
    int minIndex = -1;
    LinkedList<Integer> elements;

    public MyMinStack() {
        elements = new LinkedList<>();
    }

    public void push(int val) {
        elements.add(val);
        if (minVal > val) {
            minVal = val;
            minIndex = elements.size() - 1;
        }
    }

    public void pop() {
        if (emptyStack()) {
            throw new IllegalStateException("栈为空");
        }
        Integer result = elements.getLast();
        if (result == minVal && minIndex == elements.size() - 1) {
            int newMin = Integer.MAX_VALUE;
            for (int i = 0; i < elements.size() - 1; i++) {
                if (newMin > elements.get(i)) {
                    newMin = elements.get(i);
                    minIndex = i;
                }
            }
            minVal = elements.get(minIndex);
            if (elements.size() == 1) {
                minVal = Integer.MAX_VALUE;
                minIndex = -1;
            }
        }
        elements.removeLast();
    }

    public int top() {
        if (emptyStack()) {
            throw new IllegalStateException("栈为空");
        }
        return elements.getLast();
    }

    public int getMin() {
        if (emptyStack()) {
            throw new IllegalStateException("栈为空");
        }
        return minVal;
    }

    private boolean emptyStack() {
        return Objects.isNull(elements) || elements.isEmpty();
    }
    //endregion


    public static void main(String[] args) {
        String[] operatesArr = {"MinStack", "push", "push", "push", "top", "pop", "getMin", "pop", "getMin", "pop", "push", "top", "getMin", "push", "top", "getMin", "pop", "getMin"};
        String paramsStr = "[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]";

        String nonParamOperates = "MinStack,getMin,pop,top";


        String sep = ",";
        String res = DSUtils.getResByOperatesAndParams(MyMinStack.class,
                operatesArr
                , paramsStr,
                sep, nonParamOperates);
        System.out.println(res);
        String expect = "[null,null,null,null,2147483647,null,2147483646,null,2147483646,null,null,2147483647,2147483647,null,-2147483648,-2147483648,null,2147483647]";
        System.out.println(expect);

    }
}
