package com.yunlong.lee.algo.backtrack.split;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lijie
 * @version 1.0
 * @description 93. 复原 IP 地址
 * @date 20/4/23 9:22 下午
 * @link https://leetcode.cn/problems/restore-ip-addresses/
 */
public class RestoreIpAddress {
    public List<String> restoreIpAddresses(String s) {
        return doRestoreIpAddress(s);
    }


    private List<String> res = new LinkedList<>();
    private LinkedList<Integer> aPath = new LinkedList<>();

    private List<String> doRestoreIpAddress(String s) {
        for (int curWidth = 1; curWidth <= s.length() / 4; curWidth++) {
            backTracking(s, curWidth);
        }
        return res;
    }


    private void backTracking(String s, int curWidth) {
        //1.回溯出口  路径长度为4就可返回
        if (aPath.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (Integer aNum : aPath) {
                sb.append(aNum).append(".");
            }
            sb.deleteCharAt(sb.length() - 1);
            res.add(sb.toString());
            return;
        }
        //2.回溯搜索遍历顺序
        for (int i = 0; i < s.length(); i++) {
            String toParse = s.substring(i, i + curWidth);
            if (aPath.size() == 3) {
                toParse = s.substring(i);
            }
            if (toParse.charAt(0) == '0') {
                break;
            }
            int aPartIpNo = Integer.parseInt(toParse);
            if (aPartIpNo > 255) {
                break;
            }
            aPath.add(aPartIpNo);
            String left = s.substring(i + curWidth);
            backTracking(left, curWidth);
            aPath.removeLast();
        }
        return;
    }


    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println(JSON.toJSONString(new RestoreIpAddress().restoreIpAddresses(s)));
    }
}
