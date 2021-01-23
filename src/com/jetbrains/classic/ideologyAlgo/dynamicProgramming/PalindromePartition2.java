package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class PalindromePartition2 {
    /*
        区间类 DP
        first, we need that
        isPal(A, i, j) =     true  (i >=j)
                             A[i] == A[j]
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();

        // build the dp matrix to hold the palindrome information
        // dp[i][j] represents whether s[i] to s[j] can form a palindrome
        boolean[][] dp = buildMatrix(s, n);

        // res[i] represents the minimum cut needed
        // from s[0] to s[i]
        int[] res = new int[n];

        for (int j = 0; j < n; ++j) {
            int cut = j;
            for (int i = 0; i <= j; ++i) {
                if (dp[i][j]) {
                    // s[i] to s[j] is a palindrome
                    // try to update the cut with res[i-1]
                    cut = Math.min(cut, i==0?0:res[i-1]+1);
                }
            }
            res[j] = cut;
        }
        return res[n-1];
    }

    private boolean[][] buildMatrix(String s, int n) {
        boolean[][] dp = new boolean[n][n];

        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (i+1 >= j-1 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp;
    }
}
