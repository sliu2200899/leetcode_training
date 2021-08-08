package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.Arrays;

public class LIS {
    /*
        LIS is a DP with  state: one dimension, time: two dimension

        Algorithm

        Initialize an array dp with length nums.length and all elements equal to 1. dp[i] represents the length of the longest increasing subsequence
        that ends with the element at index i.

        Iterate from i = 1 to i = nums.length - 1. At each iteration, use a second for loop to iterate from j = 0 to j = i - 1 (all the elements before i).
        For each element before i, check if that element is smaller than nums[i]. If so, set dp[i] = max(dp[i], dp[j] + 1).

        Return the max value from dp.

        time: O(n^2)
        space: O(n)
     */
    public int lengthOfLIS(int[] nums) {

        int n = nums.length;

        // step1 : we need some function or array that represents the answer to the problem for a given state
        // dp[i] represents the length of the longest increasing subsequence that ends with the ith element.
        int[] dp = new int[n];

        // step3 : the third component is simplest, we need a base case or some kind of initialization
        Arrays.fill(dp, 1);

        // step2 : we need a way to transition between states, such as dp[5] and dp[7]. This is called a recurrence relation.
        // dp[i] = max(dp[j] + 1) for all j where nums[j] < nums[i] and j < i.
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }


        // get the LIS
        int longest = 0;
        for (int c : dp) {
            longest = Math.max(longest, c);
        }

        return longest;
    }

    /*
        DP + greedy + binary search
        for two subsequences of the same length [1,2,8] and [3,4,5] which one is better?
        [3,4,5] is better since the last element 5 is smaller than 8 which has more chance to expand.

        dp[i] the smallest ending number of a subsequence that has length + 1

        In this approach, we scan the array from left to right. We also make use of a dp array initialized with all 0's.
        This dp array is meant to store the increasing subsequence formed by including the currently encountered element.
        While traversing the nums array, we keep on filling the dp array with the elements encountered so far.

        An important point to be noted is that for Binary Search, we consider only that portion of the dp array in which
        we have made the updates by inserting some elements at their correct positions(which remains always sorted).
        Thus, only the elements upto the ith index in the dp array can determine the position of the current element in it.
        Since, the element enters its correct position(ii) in an ascending order in the dp array, the subsequence formed so far in it is surely an increasing subsequence.
        Whenever this position index ii becomes equal to the length of the LIS formed so far(len), it means, we need to update the len as len = len + 1

        summary:  dp array does not result in longest increasing subsequence, but length of dp array will give you length of LIS.
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            // int i = binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i+1);
            }

            dp[i] = num;

            // this algorithm does not always generate a valid subsequence of the input, but the length of the subsequence will always equal
            // the length of the longest increasing subsequence. For example, with the input [3, 4, 5, 1], at the end we will have sub = [1, 4, 5],
            // which isn't a subsequence, but the length is still correct.

            // The length remains correct because the length only changes when a new element is larger than any element in the subsequence.
            // In that case, the element is appended to the subsequence instead of replacing an existing element.
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    // can we implement the binary search
    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {

            // int i = Arrays.binarySearch(dp, 0, len, num);

            // // find the first elements that is larger than or equal to num
            int left = 0, right = len - 1;
            int i = len;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (dp[mid] < num) {
                    left = mid + 1;
                } else {
                    if (mid == 0 || (dp[mid-1] < num)) {
                        i = mid;
                        break;
                    }
                    right = mid - 1;
                }
            }

            dp[i] = num;

            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
