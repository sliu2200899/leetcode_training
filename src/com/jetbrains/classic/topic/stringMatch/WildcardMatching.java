package com.jetbrains.classic.topic.stringMatch;

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
           refer to: https://www.youtube.com/watch?v=3ZDZ-N0EPV0&t=82s&ab_channel=TusharRoy-CodingMadeSimple


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

               if  p[j] is '*'   dp[i-1][j] || dp[i][j-1]    dp[i-1][j] means if match when * represents this character      dp[i][j-1] means if match when * represents zero sequence

     */
    public boolean isMatch2(String s, String p) {

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        // replace multiple * with one *
        // e.g. a**b****c --> a*b*c

        int writeIndex = 0;
        for (int i = 0; i < pattern.length; ++i) {
            if (pattern[i] == '*' && i != 0 && pattern[i-1] == '*') continue;
            pattern[writeIndex++] = pattern[i];
        }

        boolean[][] dp = new boolean[str.length+1][writeIndex+1];
        dp[0][0] = true;

        if (writeIndex > 0 && pattern[0] == '*') {
            dp[0][1] = true;
        }

        /*
            dp[i][j] represents whether s[:i] can match p[:j]
            dp[i][j] = dp[i-1][j-1]  if str[i] == pattern[j] || pattern[j] == '?'
                       dp[i-1][j] || dp[i][j-1] if pattern[j] == '*'
                       false
        */
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                if (pattern[j-1] == '?' || str[i-1] == pattern[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else if (pattern[j-1] == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }

        return dp[str.length][writeIndex];
    }
}
