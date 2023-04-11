package com.yunlong.lee.dataStructure.str.matching;

/**
 * @author lijie
 * @version 1.0
 * @description 44. 通配符匹配
 * @date 10/4/23 3:26 下午
 * @link https://leetcode.cn/problems/wildcard-matching/
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        return doIsMatch(s, p);
    }

    private boolean doIsMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }

        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (p.charAt(j - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] =
                                dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1);
                    }
                } else {
                    //p[j-1]为*情况
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];

                }
            }
        }
        return dp[m][n];
    }

    // 去掉*/?，s.contains(p)=true
    //
    private boolean myDoIsMatch(String s, String p) {
        //1.不含通配符，长度相同且相等
        //2.含*，在两边，去掉后，s含去掉*后p的子串,在中间，p以*分割成两部分,都是s的子串
        //3.含?, 在两边，去掉后，s含去掉?后p的子串,在中间，p以?分割成两部分,都是s的子串
        //4.含？+*，在两边，去掉后，s含去掉*?后p的子串,在中间，p以?分割成两部分,都是s的子串，归纳到以问号的问题
        return false;

    }

    //1.不含通配符，长度相同且相等
    private boolean notContainsWildCard(String str, String pattern) {
        return str.equals(pattern);
    }

    //2.含*，在两边，去掉后，s含去掉*后p的子串,在中间，p以*分割成两部分,都是s的子串
    private boolean onlyContainsStar(String str, String pattern) {
        int starIdx = str.indexOf('*');
        String leftSubStr = str.substring(0, starIdx);
        boolean isLeftMatching = false;
        if (leftSubStr.contains("*")) {
            isLeftMatching = onlyContainsStar(str, leftSubStr);
        }
        boolean isRightMatching = false;
        String rightSubStr = str.substring(starIdx + 1, str.length() - 1);
        return false;

    }
}
