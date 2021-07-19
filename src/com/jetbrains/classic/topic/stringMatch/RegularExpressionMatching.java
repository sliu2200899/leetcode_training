package com.jetbrains.classic.topic.stringMatch;

public class RegularExpressionMatching {
    /*
           time:  refer to:  https://levelup.gitconnected.com/solving-for-recursive-complexity-736439987cb0
           space:
     */
    public boolean isMatch(String s, String p) {
        /*
            rule:
                1. if p is empty, just check if s is empty or not
                2. if p is not empty,
                        first, check the first character
                            s is empty. -> false
                            s is not empty. ->  1. the first character of p and s are the same. It's a lowercase letter. 2. the first character of p is '.' sign.
                        second, check the second character
                            first char not match   ->. isMatch(s, p.substring(2))
                            first char match   -> (first_match && isMatch(s.substring(1), p))

        */
        // p is empty
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        // p is not empty, check first character matching
        boolean first_match = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        // check if the second character is the kleene star
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2)) ||
                    (first_match && isMatch(s.substring(1), p)));
        }

        // If there were no Kleene stars (the * wildcard character for regular expressions), the problem would be easier -
        // we simply check from left to right if each character of the text matches the pattern.
        return first_match && isMatch(s.substring(1), p.substring(1));
    }

    /*
        Dp: top-down approach
        we use dp(i, j) to handle those calls saving us expensive string-building operations and allowing us to cache the intermediate results.
     */
    Boolean[][] cache;

    public boolean isMatch2(String s, String p) {
        // recursion
        cache = new Boolean[s.length()+1][p.length()+1];
        return dp(0,0,s,p);
    }

    private boolean dp(int i, int j, String text, String pattern) {

        if (cache[i][j] != null) {
            return cache[i][j];
        }

        Boolean ans;

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


        cache[i][j] = ans;
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
