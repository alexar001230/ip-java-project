package com.yunlong.lee.str;

/**
 * @author lijie
 * @version 1.0
 * @description
 * @date 10/3/23 3:15 下午
 * @link https://leetcode.cn/problems/compare-version-numbers/
 */
public class CompareVersion {

    private static final String POIT_SPLIT = "\\.";

    public int compareVersion(String version1, String version2) {
        //按照分割，统一转换成较长的数组，两个指针遍历
        int maxPart = Math.max(version1.split(POIT_SPLIT).length,
                version2.split(POIT_SPLIT).length);
        int[] version1Arr = versionTransform(version1, maxPart);
        int[] version2Arr = versionTransform(version2, maxPart);
        int i = 0;
        while (i < maxPart) {
            if (version1Arr[i] > version2Arr[i]) {
                return 1;
            } else if (version1Arr[i] < version2Arr[i]) {
                return -1;
            }
            i++;
        }
        return 0;
    }

    private int[] versionTransform(String versionStr, int nPart) {
        String[] versionStrArr = versionStr.split(POIT_SPLIT);
        int[] versionArr = new int[nPart];
        for (int i = 0; i < nPart; i++) {
            if (i < versionStrArr.length) {
                versionArr[i] = Integer.parseInt(versionStrArr[i]);
            } else {
                versionArr[i] = 0;
            }
        }
        return versionArr;
    }


    public static void main(String[] args) {
        String version1 = "1.0", version2 = "1.0.0";
        // String version2 = ;
        System.out.println(new CompareVersion().compareVersion(version1, version2));
    }
}
