package com.yunlong.lee.dataStructure.stackAndQ;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/3/23 5:21 下午
 * @link https://leetcode.cn/problems/min-stack/
 */
public class MinStack {
    private static MinStack instance;


    int capacity = 10000 * 3;
    int minVal = Integer.MAX_VALUE;
    int minIndex = 0;
    Integer[] arr = new Integer[capacity];
    int popIndex = -1;
    int pushIndex = 0;

    public MinStack() {
        System.out.print("null");
    }

    public void push(int val) {
        arr[pushIndex] = val;
        if (minVal > val) {
            minVal = val;
            minIndex = pushIndex;
        }
        popIndex = pushIndex;
        pushIndex++;
        System.out.print(",null");
    }

    public void pop() {
        if (emptyStack()) {
            throw new IllegalStateException("栈为空");
        }
        int result = arr[popIndex];
        int newMin = Integer.MAX_VALUE;
        if (popIndex == minIndex) {
            for (int i = 0; i < popIndex; i++) {
                if (null != arr[i]) {
                    if (newMin >= arr[i]) {
                        newMin = arr[i];
                        minIndex = i;
                    }
                }
            }
            minVal = newMin;
        }
        arr[popIndex] = null;
        popIndex--;
        System.out.print(",null");
    }

    public int top() {
        if (emptyStack()) {
            throw new IllegalStateException("栈为空");
        }
        System.out.print("," + arr[popIndex]);
        return arr[popIndex];
    }

    public int getMin() {
        if (emptyStack()) {
            throw new IllegalStateException("栈为空");
        }
        System.out.print("," + minVal);
        return minVal;
    }

    private boolean emptyStack() {
        if (popIndex == -1) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        String[] operates = {"MinStack", "push", "push", "getMin", "getMin",
                "getMin", "pop", "getMin", "push", "push", "getMin", "push", "top",
                "push", "getMin", "push", "getMin", "push", "push", "getMin", "push",
                "push", "push", "getMin", "top", "push", "getMin", "getMin", "getMin",
                "pop", "push", "getMin", "getMin", "getMin", "top", "top", "push",
                "top", "getMin", "push", "push", "getMin", "top", "push", "getMin",
                "getMin", "top", "top", "push", "getMin", "getMin", "pop", "pop",
                "top", "getMin", "push", "pop", "push", "push", "getMin",
                "push", "push", "pop", "getMin", "getMin", "push", "push",
                "getMin", "push", "getMin", "push", "push", "push", "push", "push", "getMin", "pop", "getMin", "push", "push", "push", "push", "pop", "getMin", "top", "getMin", "getMin", "top", "push", "pop", "push", "top", "push", "push", "push", "getMin", "top", "push", "push", "push", "top", "push", "push", "push", "top", "getMin", "push", "getMin", "push", "top", "push", "push", "getMin", "pop", "pop", "push", "pop", "getMin", "top", "getMin", "top", "push", "getMin", "push", "getMin", "getMin", "push", "getMin", "push", "top", "getMin", "getMin", "push", "getMin", "pop", "getMin", "getMin", "getMin", "top", "pop", "push", "top", "getMin", "getMin", "getMin", "push", "getMin", "push", "pop", "push", "push", "push", "push", "pop", "getMin", "pop", "pop", "push", "top", "push", "pop", "getMin", "getMin", "push", "push", "getMin", "pop", "push", "top", "push", "push", "top", "push", "push", "push", "getMin", "push", "pop", "getMin", "push", "push", "getMin", "push", "getMin", "pop", "getMin", "push", "push", "top", "top", "push", "getMin", "pop", "push", "getMin", "push", "push", "getMin", "top", "pop", "push"};
        String paramsStr = "[],[-124],[-164],[],[],[],[],[],[-24],[-100],[]," +
                "[33],[],[-22],[],[197],[],[127],[122],[],[139],[142],[189],[],[],[-40],[],[],[],[],[174],[],[],[],[],[],[-28],[],[],[177],[-117],[],[],[42],[],[],[],[],[-43],[],[],[],[],[],[],[158],[],[25],[-197],[],[-120],[-74],[],[],[],[51],[60],[],[182],[],[-161],[57],[156],[-182],[28],[],[],[],[122],[-119],[-101],[77],[],[],[],[],[],[],[-185],[],[-25],[],[-7],[91],[-79],[],[],[-168],[115],[-14],[],[-95],[-24],[34],[],[],[20],[],[-88],[],[1],[186],[],[],[],[-45],[],[],[],[],[],[89],[],[-65],[],[],[-100],[],[-85],[],[],[],[91],[],[],[],[],[],[],[],[-80],[],[],[],[],[-66],[],[-3],[],[-185],[-15],[-185],[-8],[],[],[],[],[177],[],[-160],[],[],[],[-39],[-67],[],[],[187],[],[178],[32],[],[-119],[-83],[162],[],[124],[],[],[-86],[-21],[],[19],[],[],[],[-126],[-110],[],[],[71],[],[],[-64],[],[-21],[148],[],[],[],[72]";


        // String[] operates = {"MinStack", "push", "push", "push", "getMin", "pop",
        //         "top", "getMin"};
        // String paramsStr = "[],[-2],[0],[-3],[],[],[],[]";

        String[] params = paramsStr.split(",");

        List<String> nonParamOperates = new ArrayList<>();
        nonParamOperates.add("MinStack");
        nonParamOperates.add("getMin");
        nonParamOperates.add("pop");
        nonParamOperates.add("top");


        Class minStackClazz = MinStack.class;

        String constructorName = "";

        try {
            constructorName = minStackClazz.getConstructor().getName();
            instance = (MinStack) minStackClazz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < operates.length; i++) {
            if (constructorName.contains(operates[i])) {
                continue;
            }
            try {
                // String curPackageName = minStackClazz.getPackage().getName();
                //String curMethodName = curPackageName + "." + operates[i];
                if (!nonParamOperates.contains(operates[i])) {
                    Method method = minStackClazz.getMethod(operates[i], int.class);
                    int param = Integer.parseInt(params[i].substring(1,
                            params[i].length() - 1));
                    method.invoke(instance, param);
                } else {
                    Method method = minStackClazz.getMethod(operates[i]);
                    if (null != instance) {
                        method.invoke(instance);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
