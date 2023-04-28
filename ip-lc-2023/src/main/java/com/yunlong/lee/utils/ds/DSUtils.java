package com.yunlong.lee.utils.ds;

import com.yunlong.lee.dataStructure.stackAndQAndHash.stack.MyMinStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * @author lijie
 * @version 1.0
 * @description 部分数据结构的test操作参数比较多
 * @date 27/4/23 6:25 下午
 * @link
 */
public class DSUtils {

    public static void printResByOperatesAndParams(Class clazz,
                                                   String[] operatesArr,
                                                   String paramsStr,
                                                   String sep,
                                                   String nonParamOperatesStr) {
        System.out.println(getResByOperatesAndParams(clazz, operatesArr,
                paramsStr,
                sep, nonParamOperatesStr));
    }

    /**
     * @param operatesArr         方法名称
     * @param paramsStr           方法参数
     * @param sep                 分割符
     * @param nonParamOperatesStr 无参方法名称
     */
    public static <T> String getResByOperatesAndParams(Class<T> clazz,
                                                       String[] operatesArr,
                                                       String paramsStr,
                                                       String sep,
                                                       String nonParamOperatesStr) {
        T instance = null;

        String[] operates = operatesArr;
        String[] params = paramsStr.split(sep);
        UnaryOperator<String> unaryOp =
                str -> str.replaceAll("\\[", "").replaceAll("]", "");
        Arrays.asList(params).replaceAll(unaryOp);
        String[] nonParamOperateArr = nonParamOperatesStr.split(sep);
        List<String> nonParamOperates = new LinkedList<>(Arrays.asList(nonParamOperateArr));

        Class targetClazz = clazz;
        StringBuilder resSb = new StringBuilder();
        try {
            Constructor<?>[] constructors = targetClazz.getConstructors();
            if (constructors[0].getParameterCount() > 0) {
                instance =
                        (T) constructors[0].newInstance(Integer.parseInt(params[0]));
            } else {
                instance = (T) targetClazz.getConstructor().newInstance();
            }
            resSb.append("null,");
        } catch (Exception e) {
            e.printStackTrace();
        }


        int len = operates.length;

        for (int i = 1; i < len; i++) {
            try {
                if (!nonParamOperates.contains(operates[i])) {
                    Method method = targetClazz.getMethod(operates[i], int.class);
                    int param = Integer.parseInt(params[i]);
                    Object result = method.invoke(instance, param);
                    Class<?> returnType = method.getReturnType();
                    if (returnType.getSimpleName().equalsIgnoreCase("Void")) {
                        resSb.append("null");
                    } else {
                        resSb.append(result);
                    }
                } else {
                    Method method = targetClazz.getMethod(operates[i]);
                    if (null != instance) {
                        Object result = method.invoke(instance);
                        Class<?> returnType = method.getReturnType();
                        if (returnType.getSimpleName().equalsIgnoreCase("Void")) {
                            resSb.append("null");
                        } else {
                            resSb.append(result);
                        }
                    }
                }
                if (i != len - 1) {
                    resSb.append(",");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "[" + resSb.toString() + "]";
    }
}
