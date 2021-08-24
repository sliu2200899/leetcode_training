package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class PartitionArrayForMaximumSum {
    /*
          1043
          recursion + memoization

          time: O(kn) , n is the length of the arr
          space: O(n) ,  space used for recursion stack
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        /*
            for each of the starting point 'i', we are able to choose the values from 'i' -> 'i+k-1'
                then convert all of the values to the largest element within the range.

                then our next range will start right after our currrent range

            we will want to pick the choice which will give us the highest sum
        */

        return maxSumAfterPartitioning(0, arr, k, new int[arr.length]);
    }

    private int maxSumAfterPartitioning(int start, int[] A, int K, int[] memo) {
        if (start >= A.length) return 0;

        if (memo[start] != 0) return memo[start];

        int max = 0;
        int maxSum = 0;

        for (int i = start; i < Math.min(A.length, start + K); ++i) {
            max = Math.max(max, A[i]);
            maxSum = Math.max(maxSum, maxSumAfterPartitioning(i + 1, A, K, memo) + (i - start + 1) * max);
        }

        memo[start] = maxSum;
        return maxSum;
    }

    /*
        DP
     */

    public int maxSumAfterPartitioning2(int[] arr, int k) {
        int N = arr.length, dp[] = new int[N];
        for (int i = 0; i < N; ++i) {
            int curMax = 0;
            for (int j = 1; j <= k && i - j + 1 >= 0; ++j) {
                // [0...i] partition into different size of subarray with j from 1 to k...
                // and check if we can get the largest sum
                curMax = Math.max(curMax, arr[i - j + 1]);
                dp[i] = Math.max(dp[i], (i >= j ? dp[i - j] : 0) + curMax * j);
            }
        }
        return dp[N - 1];
    }
}
