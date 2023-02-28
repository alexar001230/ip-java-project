package com.yunlong.lee.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 14/2/23 6:04 下午
 * @link https://leetcode.cn/problems/combination-sum-iii/
 */
public class CombinationSum {

    static List<List<Integer>> combine3Res = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        int n = 9;
        int k = 3;
        System.out.println(JSON.toJSONString(combinationSum3(k, n)));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum3Res(k, n - 1);
        for (int i = 0; i < combine3Res.size(); i++) {
            List<Integer> aPath = combine3Res.get(i);
            int aPathTotal = 0;
            for (int j = 0; j < aPath.size(); j++) {
                aPathTotal = aPathTotal + aPath.get(j);
                if (aPathTotal > n) {
                    break;
                }
                if (j == aPath.size() - 1 && aPathTotal == n) {
                    res.add(new ArrayList<>(aPath));
                }
            }
        }
        return res;
    }


    public static List<List<Integer>> combinationSum3Res(int k, int n) {
        combineSum3Helper(k, n, 1);
        return combine3Res;
    }

    public static void combineSum3Helper(int k, int n, int startIdx) {
        if (path.size() == k) {
            combine3Res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIdx; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            combineSum3Helper(k, n, i + 1);
            path.removeLast();
        }
    }
}
