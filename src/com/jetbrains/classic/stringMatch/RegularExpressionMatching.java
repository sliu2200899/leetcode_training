package com.jetbrains.classic.stringMatch;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        // recursion
        // base case

        /*
            1. first character matching or not.
            2. second character is * sign or not.

            time:
         */
        if (p.isEmpty()) return s.isEmpty();

        boolean first_match = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2)) ||   // first not match
                    (first_match && isMatch(s.substring(1), p)));    // first match
        }

        return first_match && isMatch(s.substring(1), p.substring(1));
    }

    /*
        use enum to represent the true or false
        Result - two dimensional array
     */
    enum Result{
        TRUE, FALSE
    }

    Result[][] memo;
    public boolean isMatch2(String s, String p) {
        // recursion
        // base case
        memo = new Result[s.length()+1][p.length()+1];
        return dp(0,0,s,p);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }

        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                        first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }

        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    /*
        Bottom-up variation
        time complextiy: O(TP)  let T, P the length of the text, pattern respectively
        space: O(TP)
     */
    public boolean isMatch3(String text, String pattern) {

        char[] t = text.toCharArray();
        char[] p = pattern.toCharArray();

        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];   // dp[i][j] represents match result, does text[:i] and pattern[:j] match?

        dp[0][0] = true;

        // deal with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i < dp[0].length; ++i) {
            if (p[i-1] == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        /*
            dp[i][j] = dp[i-1][j-1]         if str[i] == pattern[j] || pattern[j] == '.'
                       if pattern[j] == '*'   two situations    dp[i][j-2]
                                                                dp[i-1][j]    if str[i] == pattern[j-1] || pattern[j-1] == '.'

                       false
         */
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                if (p[j-1] == '.' || p[j-1] == t[i-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p[j-1] == '*') {
                    dp[i][j] = dp[i][j-2];
                    if (p[j-2] == '.' || p[j-2] == t[i-1]) {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[t.length][p.length];
    }
}
