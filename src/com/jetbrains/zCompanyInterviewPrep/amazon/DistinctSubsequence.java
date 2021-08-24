package com.jetbrains.zCompanyInterviewPrep.amazon;

import javafx.util.Pair;

import java.util.HashMap;

public class DistinctSubsequence {
    /*
        refer to:  https://leetcode.com/problems/distinct-subsequences/solution/

        approach 1 : recursion + memoization

        As mentioned before, the algorithm will be following the same ideology as before. We will be using a
        couple of indices to match one character at a time. Let's look at the two different scenarios again
        that we could possibly encounter and also look at what we have to do differently this time around.

            The first scenario is where the current characters do not match. In this case,
            we don't have any choice but to move on one step further in the string S in the hopes of
            a potential match. If we talk in terms of indices i and j where i represents the current
            character in string S while j is for the current character in string T, then it would mean
            that we have to move from (i, j) to (i + 1, j).

            The second scenario is a bit more interesting. Suppose the two characters match up. Now, in this
            case we can simply move one character each in both the strings i.e. (i + 1, j + 1) which is what
            we did for our simpler version of this problem. However, we need to find all possible subsequence matches,
             right? So, it's possible that we find the same character as i, at another index down the line, and from
             that point on we are able to find the remainder of the string T as well? Let's look at s = rabbbit
             and t = rabit.


        func recurse(i, j)
        {
            if (i == M or j == N)
            {
                return j == N ? 1 : 0
            }

            if (s[i] == t[j])
            {
                return recurse(i + 1, j) + recurse(i + 1, j + 1)
            }
            else
            {
                return recurse(i + 1, j)
            }
        }

        time: O(m*n)
        The time complexity for a recursive solution is defined by two things:
            the number of recursive calls that we make and
            the time it takes to process a single call.
        space: O(m*n)
     */
    // dictionary that we will use for memoization
    private HashMap<Pair<Integer, Integer>, Integer> memo;

    public int numDistinct(String s, String t) {
        this.memo = new HashMap<Pair<Integer, Integer>, Integer>();
        return this.recurse(s, t, 0, 0);
    }

    private int recurse(String s, String t, int i, int j) {
        int N = s.length(), M = t.length();

        if (i == N || j == M) {
            return j == t.length() ? 1 : 0;
        }

        Pair<Integer, Integer> pair = new Pair<>(i, j);
        if (memo.containsKey(pair)) {
            return memo.get(pair);
        }

        int num = 0;
        if (s.charAt(i) == t.charAt(j)) {
            num += recurse(s, t, i + 1, j);
            num += recurse(s, t, i + 1, j + 1);
        } else {
            num += recurse(s, t, i + 1, j);
        }

        memo.put(pair, num);
        return num;
    }

    /*
        iterative bottom up

     */
    public int numDistinct2(String s, String t) {

        int M = s.length();
        int N = t.length();

        int[][] dp = new int[M + 1][N + 1];

        // Base case initialization
        for (int j = 0; j <= N; j++) {
            dp[M][j] = 0;
        }

        // Base case initialization
        for (int i = 0; i <= M; i++) {
            dp[i][N] = 1;
        }

        // Iterate over the strings in reverse so as to
        // satisfy the way we've modeled our recursive solution
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {

                // Remember, we always need this result
                dp[i][j] = dp[i + 1][j];

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
}
