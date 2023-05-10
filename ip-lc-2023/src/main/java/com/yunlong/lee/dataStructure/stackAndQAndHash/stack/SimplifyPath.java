package com.yunlong.lee.dataStructure.stackAndQAndHash.stack;

import java.util.LinkedList;

/**
 * @author lijie
 * @version 1.0
 * @description 71. 简化路径
 * @date 7/5/23 1:07 上午
 * @link https://leetcode.cn/problems/simplify-path/description/
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        return doSimplifyPath(path);
    }

    private LinkedList<String> stack = new LinkedList<>();
    private String res = "";

    private String doSimplifyPath(String path) {
        String[] pathArr = path.split("/");
        for (String aStr : pathArr) {
            if (aStr.equals(".") || aStr.length() == 0) {
                continue;
            }
            if (aStr.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(aStr);
        }
        if (stack.isEmpty()) {
            res = "/";
        } else {
            while (!stack.isEmpty()) {
                String aPart = "/" + stack.pop();
                res = aPart + res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // String path = "/../";
        // String path = "/home//foo/";
        String path = "/a/./b/../../c/";
        System.out.println(new SimplifyPath().simplifyPath(path));
    }
}
