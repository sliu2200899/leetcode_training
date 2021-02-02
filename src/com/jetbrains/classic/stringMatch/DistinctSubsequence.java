package com.jetbrains.classic.stringMatch;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class DistinctSubsequence {
    /*
        leetcode: 115
        solution 1: recusion + memoization
        refer to:  https://leetcode.com/problems/distinct-subsequences/solution/

        time: O(mn)
            The time complexity for a recursive solution is defined by two things: the number of recursive calls that we make and the time it takes to process a single call.
            If you notice the solution closely, all we are doing in the function is to check the dictionary for a key, and then we make a couple of function calls. So the time it takes to process a single call is actually O(1).
            The number of unique recursive calls is defined by the two state variables that we have. Potentially, we can make O(M×N) calls where MM and NN represent the lengths of the two strings.
            Thus, the time complexity for this solution would be O(M×N).

        space: O(nm)
            The maximum space is utilized by the dictionary that we are using and the size of that dictionary would also be controlled by the total possible combinations of i and j which turns out to be O(M \times N)O(M×N) as well.
            We also have the space utilized by the recursion stack which is O(M)O(M) where MM is the length of string S.
     */
    // Dictionary that we will use for memoization
    private Map<Pair<Integer, Integer>, Integer> memo;

    private int recurse(String s, String t, int i, int j) {
        int M = s.length();
        int N = t.length();

        // base case
        if (i == M || j == N || M - i < N - j) {
            return j == t.length() ? 1 : 0;
        }

        Pair<Integer, Integer> key = new Pair<>(i, j);

        // check to see if the result for this recursive call is already cached
        if (this.memo.containsKey(key)) {
            return this.memo.get(key);
        }

        // always calculate this result since it's required for both the cases
        int ans = this.recurse(s, t, i+1, j);

        if (s.charAt(i) == t.charAt(j)) {
            ans += this.recurse(s, t, i+1, j+1);
        }

        this.memo.put(key, ans);
        return ans;
    }

    public int numDistinct1(String s, String t) {
        this.memo = new HashMap<>();
        return this.recurse(s, t, 0, 0);
    }

    /*
        solution 2: bottom up
        recurse(i, j)   represents the number of distinct subsequences in string s[i..M] that equals the string t[j..N]
        implies that we need to calculate the value of recurse(i, j) before we can find answers for recurse(i-1, j), recurse(i, j-1), recurse(i-1, j-1)

     */
    public int numDistinct2(String s, String t) {
        int M = s.length(), N = t.length();

        int[][] dp = new int[M+1][N+1];
        for (int i = 0; i <= M; ++i) {
            // initialize the last column
            dp[i][N] = 1;  // because an every string has an empty subsequence.
        }

        for (int i = 0; i < N; ++i) {
            dp[M][i] = 0;  // represetns that we have an empty substring left in string S while we still have T[j..N-1] left.
            // that means, there's no possibility of a match. So, dp[M][j] = 0 except when j == N
        }

        for (int i = M-1; i >= 0; --i) {
            for (int j = N-1; j >= 0; --j) {
                dp[i][j] = dp[i+1][j];

                // If the characters match, we add the
                // result of the next recursion call (in this
                // case, the value of a cell in the dp table
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    /*
        space optimization
     */

    public int numDistinct3(String s, String t) {
        int M = s.length();
        int N = t.length();

        int[] dp = new int[N];

        int prev = 1;

        // Iterate over the strings in reverse so as to
        // satisfy the way we've modeled our recursive solution
        for (int i = M - 1; i >= 0; i--) {

            // At each step we start with the last value in
            // the row which is always 1. Notice how we are
            // starting the loop from N - 1 instead of N like
            // in the previous solution.
            prev = 1;

            for (int j = N - 1; j >= 0; j--) {

                // Record the current value in this cell so that
                // we can use it to calculate the value of dp[j - 1]
                int old_dpj = dp[j];

                // If the characters match, we add the
                // result of the next recursion call (in this
                // case, the value of a cell in the dp table
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j] += prev;
                }

                // Update the prev variable
                prev = old_dpj;
            }
        }

        return dp[0];
    }

    /*
        solution 4  preferred way to solve the problem
     */
    public int numDistinct4(String s, String t) {

        /*
              0 r a b b i t
           0  1 0 0 0 0 0 0
           r  1 1 0 0 0 0 0
           a  1 1 1 0 0 0 0
           b  1 1 1 1 0 0 0
           b  1 1 1 2 1 0 0
           b ...
           i
           t

           s: rabb
           t: rab

           dp[i][j] = 0                               if s.length() < t.length()

                      dp[i-1][j]                      if s.charAt(i) != t.charAt(j)
                      dp[i-1][j-1] + dp[i-1][j]                 otherwise

        */

        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        // initialize the dp value when t is an empty string, number of subsequence of an empty string should be 1
        for(int i = 0; i < m; i ++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= m; i ++){
            for(int j = 1; j <= n; j ++){
                //in both cases, the subsequence in String t should be ending with character t.charAt(j - 1)
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    // when two pointers pointing to same character
                    // if we take these two characters simultaneously, we should have dp[i-1][j-1] subsequences
                    // otherwise if we overlook current i (moving back for one step) and keeping the current j we have another dp[i -1][j]
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else{
                    // when two pointers pointing to difference characters
                    //we cannot take these two characters but we still should make j ending with pointing to current position
                    // then we should move i backward
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    /*
        space optimization
     */
    public int numDistinct5(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[m];

        int prev = 1;
        for(int i = 0; i <= m-1; i ++){
            for(int j = 0; j <= n-1; j ++){  // j -> j+1

                int old_dpj = dp[j];   // dp[i-1][j]

                if(s.charAt(i) == t.charAt(j)){
                    dp[j] += prev;
                }

                prev = old_dpj; // prev = dp[i-1][j-1]
            }
        }
        return dp[n-1];
    }
}
