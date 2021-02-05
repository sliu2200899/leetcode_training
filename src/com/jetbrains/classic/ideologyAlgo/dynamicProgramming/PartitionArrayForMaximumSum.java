package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class PartitionArrayForMaximumSum {
    /*
          recursion + memoization

          time: O(kn) , n is the length of the arr
          space: O(n) ,  space used for recursion stack
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        /*
            "you should partition the array into (contiguous) subarrays of length at most k"
            At each index i of the array, we have k decision to make on how to partition the array as follows,
                Partition at index i so we will have array of size 1.
                Partition at index i + 1 so we will have array of size 2.
                ...
                Partition at index i + k - 1 so we will have array of size k.

            "After partitioning, each subarray has their values changed to become the maximum value of that subarray"
            Since the problem says all the elements in the partition become the maximum value.
            We maintain the partition array running max and multiply the max with number of elements in the partition.


            After each partition the array we get on the right side of the partition is a sub problem (optimal substructure) so we call the recursion on it. And add the result with previously computed current left array partition max sum.
        */
        Integer[] memo = new Integer[arr.length];
        return maxSumAfterPartitioning(arr, k, 0, memo);
    }

    private int maxSumAfterPartitioning(int[] arr, int k, int i, Integer[] memo) {
        if (i == arr.length) {
            return 0;
        }

        if (memo[i] != null) {
            return memo[i];
        }

        int currMax = 0;
        int sumMax = 0;
        for (int j = 0; j < k; ++j) {
            int to = i + j;
            if (to >= arr.length) {
                break;
            }
            currMax = Math.max(currMax, arr[to]);
            int leftSum = currMax * (j + 1);
            int rightSum = maxSumAfterPartitioning(arr, k, to + 1, memo);
            sumMax = Math.max(sumMax, leftSum + rightSum);
        }

        memo[i] = sumMax;
        return sumMax;
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
