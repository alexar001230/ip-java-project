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

    private List<String> doRestoreIpAddress(String s) {
        backTracking(s, 0, 0);
        return res;
    }


    private void backTracking(String s, int startIdx, int pointNum) {
        //1.回溯出口  路径长度为4就可返回
        if (pointNum == 3) {
            if (isValid(s, startIdx, s.length() - 1)) {
                res.add(s);
            }
            return;
        }
        //2.回溯搜索遍历顺序
        for (int i = startIdx; i < s.length(); i++) {
            if (isValid(s, startIdx, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointNum++;
                backTracking(s, i + 2, pointNum);
                s = s.substring(0, i + 1) + s.substring(i + 2);
                pointNum--;
            } else {
                break;
            }
        }
        return;
    }

    private boolean isValid(String str, int start, int end) {
        if (start > end) {
            return false;
        }

        if (str.charAt(start) == '0' && start != end) {
            return false;
        }

        // boolean hasOthers = false;
        int toParseNo = 0;
        for (int i = start; i <= end; i++) {
            char aChar = str.charAt(i);
            if (aChar > '9' || aChar < '0') {
                // hasOthers = true;
                // break;
                return false;
            }
            toParseNo = toParseNo * 10 + aChar - '0';
            if (toParseNo > 255) {
                return false;
            }
        }
        // if (!hasOthers) {
        //     return Integer.parseInt(str.substring(start, end + 1)) <= 255;
        // } else {
        //     return false;
        // }
        return true;
    }


    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println(JSON.toJSONString(new RestoreIpAddress().restoreIpAddresses(s)));
    }
}
