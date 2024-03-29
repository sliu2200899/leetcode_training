package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class Backpack01 {
    public int backPack(int m, int[] A) {
        // write your code here

        // dp[i][sum] represents whether we can add them up to sum in the first ith element.
        /*
              0 1 2 3 4 5 6 7 8 9 10
            0 1 0 0 0 0 0 0 0 0 0 0
            1 1 0 0 0 0 0 0 0 0 0 0
            2 1 0
            3 1
            4 1

            can we have sum 0 in first j element
            row: first i element
            col: sum

            dp[i][sum] = dp[i-1][sum - A[i-1]]  if we pick current element
                         dp[i-1][sum]           if we don't pick current element


            dp[2][1] =  ||

            time: O(mn)
            space: O(mn)
        */
        boolean[][] dp = new boolean[A.length+1][m+1];

        for (int i = 0; i <= A.length; ++i) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= m; ++j) {
                dp[i][j] = (j-A[i-1] >= 0 ? dp[i-1][j-A[i-1]] : false) || dp[i-1][j];
            }
        }

        for (int i = m; i >= 0; --i) {
            if (dp[A.length][i]) return i;
        }
        return -1;
    }

    /*
        how to optimize space

        note, use two lines to store intermediate result,
        only impact the dp array not A array
        only impact on the row as a whole, ie.  entire calculation %2
     */
    public int backPack1improve(int m, int[] A) {
        // dp[i][j] for the first i elements, can we make sum of j
        boolean[][] dp = new boolean[2][m+1];

        for (int i = 0; i <= m; ++i) dp[i%2][0] = true;

        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= m; ++j) {
                dp[i%2][j] = (j-A[i-1] >= 0 ? dp[(i-1)%2][j-A[i-1]] : false) || dp[(i-1)%2][j];
            }
        }

        for (int i = m; i >= 0; --i) {
            if (dp[A.length % 2][i]) return i;
        }
        return -1;
    }

    /*
        backpack 2
        这次每个元素除了 size 之外也具有 value，就变成了更典型的 01 背包问题。

        dp 结构一致，而采用 int[][] 来记录
        dp[i][j] represents maximum value we can get within j space in the first ith element

        m denotes the size of a backpack
        A & V: Given n items with size A[i] and value V[i]
        return: the maximum value
     */
    public int backPack2(int m, int[] A, int[] V) {
        /*              j
              0 1 2 3 4 5 6 7 8 9 10
            0 0 0 0 0 0 0 0 0 0 0 0
            1 0 0 1 1 1 1 1 1 1 1 1
        i   2 0 0 1 5 5 6 6 6 6 6 6
            3 0 0 1 5 5 6 6 6 7 7 8
            4 0 0 1 5 5 6 6 6 7 7 9
                                       j                         i
             value ? we can get within 3 space, and in the first 4th elements
             j-A[i-1]
             max(6, 0) = 1

            dp[i][j] represents maximum value we can get within j space, and in the first ith elements

            dp[i][j] = max(dp[i-1][j-A[i-1]] + V[i-1],                     if pick this element and if j - A[i-1] >= 0
                           dp[i-1][j])                                     otherwise
        */

        int[][] dp = new int[A.length+1][m+1];

        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= m; ++j) {
                if (j - A[i-1] >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j-A[i-1]] + V[i-1], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[A.length][m];
    }

    /*
        backpack 3

        Given n kinds of items, and each kind of item has an infinite number available. The i-th item has size A[i] and value V[i].
        Also given a backpack with size m. What is the maximum value you can put into the backpack?

     */

    public int backPack3(int[] A, int[] V, int m) {
        // write your code here

        /*
            dp[i]  represents maximum value you can put into the backPack in the first ith element in A
                    j
            A = [2, 3, 5, 7]
                    i
            0 1 2 3 4 5 6 7 8 9 10
            0 0 1 5 5 6

            dp[i] = max(dp[i- A[j]]+ V[j])   where 0 < i <= m and 0 <= j < A.length and only if i - A[j] >= 0

            dp[1] = 0
            dp[2] = dp[0]+V[0] = 1
            dp[3] = max(dp[3-2] + V[0], dp[3-3] + V[1])
            dp[4] = max(dp[4-2] + V[0], dp[4-3] + V[1])
            dp[5] = max(dp[5-2] + V[0], dp[5-3] + V[1], dp[5-5] + V[2])
            ...
        */

        if (A == null || A.length == 0) return 0;

        int n = A.length;

        int[] dp = new int[m+1];
        dp[0] = 0;

        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < A.length; ++j) {
                if (i - A[j] >= 0) {
                    dp[i] = Math.max(dp[i], dp[i - A[j]] + V[j]);
                }
            }
        }

        return dp[m];
    }
}
