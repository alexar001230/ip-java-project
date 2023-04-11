package com.yunlong.lee.dataStructure.str.matching;

/**
 * @author lijie
 * @version 1.0
 * @description 10. 正则表达式匹配
 * @date 11/4/23 3:43 下午
 * @link https://leetcode.cn/problems/regular-expression-matching/
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        return doIsMatch(s, p);
    }

    private boolean doIsMatch(String s, String p) {
        //dp 5步走
        //1.dp[i][j]含义，为s前i个字符s[0...i-1],与p前j个字符p[0...j-1]是否匹配,能匹配上则为true
        //2.转移方程,dp[i][j]的真假,就是在分析s[i-1],p[j-1]
        //  2.1 当p[j-1]!='*'时
        //      2.1.1 if s[i-1] == p[j-1],dp[i][j] = dp[i-1][j-1]
        //      2.1.2 if p[j-1] == '.',可直接将.转换成s[i-1],归纳到dp[i][j] = dp[i-1][j-1]情况
        //  2.2 当p[j-1]='*'时,主要看p[j-2]做判断
        //      2.2.1 若p[j-2]为小写字符,&& p[j-2] = s[i-1],此时，s继续往前移动，p不动,dp[i][j] =
        //            dp[i-1][j]
        //      2.2.2 p[j-2]为.,那么".*"为万能匹配串,可直接返回true
        //      2.2.3 其他p[j-2]为小写字符，&& p[j-2]!=s[i-1],看dp[i][j-2]
        //3.初始化
        //  3.1 空的s
        //      3.1.1 空的p,dp[0][0] = true;
        //      3.1.2 非空的p,空的s可能可以匹配非空的p,例如"a*",因此需要经过转移计算 todo 不懂
        //  3.2 非空的s
        //      3.2.1 空的p,空的p不可能匹配非空的s,所以dp[i][0] = false,i range 1,m
        //      3.2.2 非空的p,需要经过转移计算
        //4.遍历顺序，显然正序遍历
        //5.举例验证及返回值,dp[m+1][n+1]
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // for (int i = 1; i <= m; i++) {
        //     dp[i][0] = false;
        // }

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (i >= 1 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    //p[j-1]为*情况
                    if (j >= 2 && i >= 1 && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')) {
                        dp[i][j] = dp[i - 1][j];
                    }
                    if (j >= 2) {
                        dp[i][j] |= dp[i][j - 2];
                    }

                }
            }
        }
        return dp[m][n];
    }
}
