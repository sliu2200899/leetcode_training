package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        // a = "hrorse"
        // b = "hros"

        // if a[i] == b[j], check a[i+1] == b[j+1] or not
        // else if a[i] != b[j],
        //.   1. remove a[i], and check a[i+1] and b[i]
        //.   2. remove b[j], and check a[i] and b[j+1]
        //.   3. add the same character as b[j] in front of a[i], and check a[i] and b[j+1]
        //.   4. add the same character as a[i] in front of b[j], and check a[i+1] and b[j]
        //.   5. replace a[i] with b[j] or vice versa. and check a[i+1] and b[j+1]

        // draw the recursion tree based on the above description
        // notice that there exists lots of overlapping subproblems
        // minDist(i, j) = min(minDist(i-1, j)+1, minDist(i, j-1)+1, minDist(i-1, j-1)) , if a[i] == b[j]
        //                 min(minDist(i-1, j)+1, minDist(i, j-1)+1, minDist(i-1, j-1)+1), if a[i] != n[j]

        if (word1 == null || word2 == null) return 0;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        int n1 = word1.length(), n2 = word2.length();
        char[] a = word1.toCharArray(), b = word2.toCharArray();

        int[][] dp = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            // 初始化第0列:a[0..i]与b[0..0]的编辑距离
            if (a[i] == b[0]) {
                //
                dp[i][0] = i;
            } else if (i != 0) {
                // add one character
                dp[i][0] = dp[i-1][0]+1;
            } else {
                //
                dp[i][0] = 1;
            }
        }


        for (int j = 0; j < n2; ++j) {
            if (a[0] == b[j]) {
                dp[0][j] = j;
            } else if (j != 0) {
                dp[0][j] = dp[0][j-1]+1;
            } else {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < n1; ++i) {
            for (int j = 1; j < n2; ++j) {
                if (a[i] == b[j]) {
                    dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]);
                } else {
                    dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]+1);
                }
            }
        }

        return dp[n1-1][n2-1];
    }

    private int min(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }
}
