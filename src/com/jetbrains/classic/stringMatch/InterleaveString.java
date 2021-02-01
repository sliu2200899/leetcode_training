package com.jetbrains.classic.stringMatch;

public class InterleaveString {
    public boolean isInterleave(String s1, String s2, String s3) {
        // dp[i][j]   represents whether it is possible to obtain a substring of length (i+j+2) which is a prefix of s3 by some interleaving of prefixes of string s1 and s2 having lengths (i+1) and (j+1) respectively.
        // For filling in the entry of dp[i][j], we need to consider two cases:
        //    1. The character just included i.e. either at ith index of s1 or at jth index of s2 doesn't match the character at kth index of s3, where k=i+j+1.
        //       In this case, the resultant string formed using some interleaving of
        //       prefixes of s1 and s2 can never result in a prefix of length k+1 in s3. Thus, we enter FalseFalse at the character dp[i][j].
        //    2. The character just included i.e. either at ith index of s1 or at jth index of s2 match the character at kth index of s3, where k=i+j+1.
        //       We need to keep xx at the last position in the resultant interleaved string formed so far.
        //       Thus, in order to use string s1 and s2 upto indices i and j to form a resultant string of length (i+j+2) which is a prefix of s3,
        //       we need to ensure that the strings s1 and s2 upto indices (i−1) and j respectively obey the same property.
        //.   3. Similarly, if we just included the jth character of s2, which matches with the kth character of s3,
        //       we need to ensure that the strings s1 and s2 upto indices i and (j−1) also obey the same property to enter a truetrue at dp[i][j].

        // time: O(mn) dp array of size m*n is filled
        // space: O(mn) 2D dp of size (m+1) *(n+1) is required.
        if (s1 == null || s2 == null || s3 == null) return false;
        if (s3.length() != s1.length() + s2.length()) return false;

        if (s1.length() == 0) return s2.equals(s3);
        if (s2.length() == 0) return s1.equals(s3);

        int s1Len = s1.length(), s2Len = s2.length(), s3Len = s3.length();
        boolean[][] dp = new boolean[s1Len+1][s2Len+1];

        dp[0][0] = true;

        for (int i = 1; i <= s1Len; ++i) {
            dp[i][0] = (s1.charAt(i-1) == s3.charAt(i-1) && dp[i-1][0]);
        }

        for (int j = 1; j <= s2Len; ++j) {
            dp[0][j] = (s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1]);
        }

        for (int i = 1; i <= s1Len; ++i) {
            for (int j = 1; j <= s2Len; ++j) {

                dp[i][j] = (s2.charAt(j-1) == s3.charAt(i+j-1) && dp[i][j-1]) ||
                        (s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j]);
            }
        }
        return dp[s1Len][s2Len];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        if (s3.length() != s1.length() + s2.length()) return false;

        if (s1.length() == 0) return s2.equals(s3);
        if (s2.length() == 0) return s1.equals(s3);

        int s1Len = s1.length(), s2Len = s2.length(), s3Len = s3.length();
        boolean[] dp = new boolean[s2Len+1];

        dp[0] = true;

        for (int j = 1; j <= s2Len; ++j) {
            dp[j] = (s2.charAt(j-1) == s3.charAt(j-1) && dp[j-1]);
        }

        for (int i = 1; i <= s1Len; ++i) {
            for (int j = 0; j <= s2Len; ++j) {
                if (j == 0) {
                    if (s1.charAt(i-1) == s3.charAt(i-1)) dp[j] = dp[j];
                    else dp[j] = false;
                } else {
                    dp[j] = (s2.charAt(j-1) == s3.charAt(i+j-1) && dp[j-1]) ||
                            (s1.charAt(i-1) == s3.charAt(i+j-1) && dp[j]);
                }
            }
        }
        return dp[s2Len];
    }
}
