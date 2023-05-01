package com.yunlong.lee.utils.ds;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
                                                   String paramSep) {
        System.out.println(getResByOperatesAndParams(clazz, operatesArr,
                paramsStr, paramSep));
    }

    /**
     * @param operatesArr 方法名称
     * @param paramsStr   方法参数
     */

    private static UnaryOperator<String> unaryOp =
            str -> str.replaceAll("\\[", "").replaceAll("]", "");

    public static <T> String getResByOperatesAndParams(Class<T> clazz,
                                                       String[] operatesArr,
                                                       String paramsStr,
                                                       String paramSep) {
        String sep = ",";
        if (Objects.nonNull(paramSep) && paramSep.length() > 0) {
            sep = paramSep;
        }
        T instance = null;

        String[] operates = operatesArr;
        String[] params = paramsStr.split(sep);
        Class<T> targetClazz = clazz;
        StringBuilder resSb = new StringBuilder();
        try {
            Constructor<?>[] constructors = targetClazz.getConstructors();
            if (constructors[0].getParameterCount() > 0) {
                int consParam = paramStr2Int(params[0]);
                instance =
                        (T) constructors[0].newInstance(consParam);
            } else {
                instance = targetClazz.getConstructor().newInstance();
            }
            resSb.append("null,");
        } catch (Exception e) {
            e.printStackTrace();
        }


        int len = operates.length;
        HashMap<String, Method> name2MethodMap = new HashMap<>();
        Method[] methods = targetClazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            name2MethodMap.put(methods[i].getName(), methods[i]);
        }

        for (int i = 1; i < len; i++) {
            try {
                Method method;
                Object result;
                if (pureParam(params[i]).length() == 0) {
                    method = targetClazz.getMethod(operates[i]);
                    result = method.invoke(instance);
                } else {
                    method = name2MethodMap.get(operates[i]);
                    result = method.invoke(instance,
                            paramStr2IntArr(params[i]));
                }
                Class<?> returnType = method.getReturnType();
                if (returnType.getSimpleName().equalsIgnoreCase("Void")) {
                    resSb.append("null");
                } else {
                    resSb.append(result);
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

    //参数str转int
    private static int paramStr2Int(String str) {
        String intStr = str.trim().replaceAll("\\[", "").trim().replaceAll("]",
                "").trim();
        return Integer.parseInt(intStr);
    }

    private static String pureParam(String str) {
        return str.trim().replaceAll("\\[", "").trim().replaceAll(
                "]",
                "").trim();
    }

    private static Integer[] paramStr2IntArr(String str) {
        String curParamStr = str.trim().replaceAll("\\[",
                "").replaceAll("]", "");
        String[] curParamsArr = curParamStr.split(",");
        List<Integer> curParamsInts = Arrays.stream(curParamsArr)
                .map(s -> s.trim())
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        Integer[] curParamIntegers =
                curParamsInts.toArray(new Integer[curParamsInts.size()]);
        return curParamIntegers;
    }
}
