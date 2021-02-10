package com.jetbrains.classic.topic.stringMatch;

public class DeleteOperForTwoString {
    public int minDistance(String word1, String word2) {
        // dp[i][j] represetns minimum nuber of steps required to make word1[:i] and word2[:j] the same.
        /*
           "sea"
            i

           "eat"
           j

            dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + 1            if s1.charAt(i) != s2.charAt(j)
                       dp[i-1][j-1]   otherwise

            time: O(mn)
            space: O(mn)
        */

        if (word1 == null || word2 == null) return 0;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        int[][] dp = new int[word1.length()+1][word2.length()+1];
        dp[0][0] = 0;
        for (int i = 1; i <= word1.length(); ++i) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= word2.length(); ++i) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); ++i) {
            for (int j = 1; j <= word2.length(); ++j) {
                if (word1.charAt(i-1) != word2.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                }
                else {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
