package com.jetbrains.classic.stringMatch;

public class WildcardMatching {

    /*

        backtracking algorithm:  can solve the most of the problem, but would TLE for the following situation:
            "bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab"
            "b*b*ab**ba*b**b***bba"

     */
    private boolean matched = false;
    private char[] pattern;
    private int plen;

    public WildcardMatching(char[] pattern, int plen) {   // pattern and len
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text, int tlen) {   // text and len
        matched = false;
        helper(0, 0, text, tlen);
        return matched;
    }

    private void helper(int ti, int pj, char[] text, int tlen) {
        // base case
        if (matched) return; // 如果已经匹配了，就不要继续递归了
        if (pj == plen) {
            if (ti == tlen) matched = true;
            return;
        }

        // recursive case
        if (pattern[pj] == '*') {
            for (int k = 0; k <= tlen - ti; ++k) {
                helper(ti + k, pj + 1, text, tlen);
            }
        } else if (pattern[pj] == '?') {
            helper(ti, pj + 1, text, tlen);
            helper(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) {
            helper(ti + 1, pj + 1, text, tlen);
        }
    }

    // recursion with Memoization


    // DP
    /*
           why we use DP?
               we notice that the final result is just true or false, and this problem has some optimal substructure, where
               we need to check if the newly constructed string matches the pattern

           How to use DP?
               we use dp[i][j] to represent if the s.substring(0, i) can match p.substring(0, j).  So in this case, the final
               result is dp[n][m]

               for example, s[i], p[j] are the i/j th character in s, p
               if p[j] == 'regular character'
                    if  s[i] == p[j]   cur char matches and dp[i][j] = dp[i - 1][j - 1]

               if  p[j] is '?'   cur char definitely matches, we only need to check dp[i - 1][j - 1]

               if  p[j] is '*'

     */
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) return false;

        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        // dp base condition for the two empty string
        dp[0][0] = true;

        // initialize the dp[0][i]
        for (int i = 1; i <= m; ++i) {
            if (p.charAt(i - 1) == '*' && dp[0][i - 1]) {
                dp[0][i] = true;
            }
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (p.charAt(j - 1) == '?' ||
                        s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}
