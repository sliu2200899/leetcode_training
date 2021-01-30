package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

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
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];   // dp[i][j] represents match result, does text[i:] and pattern[j:] match?
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
