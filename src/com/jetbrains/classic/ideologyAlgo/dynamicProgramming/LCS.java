package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class LCS {
    /*
        Given two strings text1 and text2, return the length of their longest common subsequence.

        A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted
        without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not).
        A common subsequence of two strings is a subsequence that is common to both strings.

        If there is no common subsequence, return 0.

        Input: text1 = "abcde", text2 = "ace"
        Output: 3
        Explanation: The longest common subsequence is "ace" and its length is 3.
     */
    public int longestCommonSubsequence(String text1, String text2) {

        // if a[i] == b[j], add lcs by 1 and check a[i+1] == b[j+1] or not
        // else if a[i] != b[j], do not add lcs, but have two plans to choose
        //.   1. remove a[i], or just add the same character as a[i] in front of b[j], and check a[i+1] and b[j]
        //.   2. remove b[j], or just add the same character as b[j] in front of a[i], and check a[i] and b[j+1]

        // draw the recursion tree based on the above description
        // notice that there exists lots of overlapping substructure
        // lcs(i, j) = max(lcs(i-1, j), lcs(i, j-1), lcs(i-1, j-1)+1) , if a[i] == b[j]
        //             max(lcs(i-1, j), lcs(i, j-1), lcs(i-1, j-1)), if a[i] != n[j]

        if (text1 == null || text2 == null) return 0;

        if (text1.length() == 0 || text2.length() == 0) return 0;

        int n1 = text1.length(), n2 = text2.length();

        char[] a = text1.toCharArray(), b = text2.toCharArray();

        int[][] dp = new int[n1][n2];
        for (int i = 0; i < n1; ++i) {
            // check a[0..i] with b[0..0]
            if (a[i] == b[0]) {
                dp[i][0] = 1;
            } else if (i != 0) {
                dp[i][0] = dp[i-1][0];
            } else {
                dp[i][0] = 0;
            }
        }

        for (int j = 0; j < n2; ++j) {
            if (b[j] == a[0]) {
                dp[0][j] = 1;
            } else if (j != 0) {
                dp[0][j] = dp[0][j-1];
            } else {
                dp[0][j] = 0;
            }
        }

        for (int i = 1; i < n1; ++i) {
            for (int j = 1; j < n2; ++j) {
                if (a[i] == b[j]) {
                    dp[i][j] = max(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]+1);
                } else {
                    dp[i][j] = max(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]);
                }
            }
        }

        return dp[n1-1][n2-1];
    }

    private int max(int a, int b, int c) {
        int temp = Math.max(a, b);
        return Math.max(temp, c);
    }
}
