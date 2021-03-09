package com.jetbrains.classic.topic.array;

import java.util.HashSet;

public class SplitArray {
    /*
        prefix sum   lc:548
        In the brute force approach, we traversed over the subarrays for every triplet of cuts considered. Rather than doing this,
        we can save some calculation effort if we make use of a cumulative sum array sum, where sum[i] stores the cumulative sum of the array nums upto the ith
        element. Thus, now in order to find the sum(subarray(i:j)), we can simply use sum[j]−sum[i]. Rest of the process remains the same.

        time: O(n^3)  n is the length of the nums    TLE
        space: O(n)
     */
    public boolean splitArray(int[] nums) {
        if (nums == null || nums.length < 7) return false;

        int len = nums.length;

        // create prefix sum array
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; ++i) {
            sum[i] = sum[i-1] + nums[i];
        }

        for (int i = 1; i < nums.length - 5; ++i) {
            int sum1 = sum[i-1];

            for (int j = i + 2; j < nums.length - 3; ++j) {
                int sum2 = sum[j-1] - sum[i];
                if (sum2 != sum1) continue;
                for (int k = j+2; k < nums.length - 1; ++k) {
                    int sum3 = sum[k-1] - sum[j];
                    int sum4 = sum[nums.length-1] - sum[k];
                    if (sum3 == sum4 && sum2 == sum4) return true;
                }
            }
        }
        return false;
    }

    /*
        Cumulative sum and hashset
        In this approach, firstly we form a cumulative sum array sum, where sum[i] stores the cumulative sum of the array nums upto the i th index.
        Then, we start by traversing over the possible positions for the middle cut formed by j. For every j, firstly, we find all the left cut's positions, i,
        that lead to equalizing the sum of the first and the second part (i.e. sum[i-1] = sum [j-1] - sum[i]) and store such sums in the set (a new HashSet is formed for every j chosen).
        Thus, the presence of a sum in set implies that such a sum is possible for having equal sum of the first and second part for the current position of the middle cut(j).

        Then, we go for the right cut and find the position of the right cut that leads to equal sum of the third and the fourth part (sum[n-1]-sum[k]=sum[k-1] - sum[j]),
        for the same middle cut as chosen earlier. We also, look if the same sum exists in the set. If so, such a triplet (i, j, k) exists which satisfies the required criteria, otherwise not.
     */
    public boolean splitArray2(int[] nums) {
        //
        if (nums == null || nums.length < 7) return false;

        int len = nums.length;

        // create prefix sum array
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; ++i) {
            sum[i] = sum[i-1] + nums[i];
        }
        /*
            j is the middle cut, first inner loop just find whether there exist left cut that can form two part of the subarray that has the same sum.
            then teh second inner loop just find whether exist right cut that can form two part of the subarray that has the same sum and this sum equals to the one
         */
        for (int j = 3; j < nums.length-3; ++j) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i-1] == sum[j-1] - sum[i]) {
                    set.add(sum[i-1]);
                }
            }

            for (int k = j+2; k < nums.length - 1; k++) {
                if (sum[nums.length - 1] - sum[k] == sum[k-1] - sum[j] && set.contains(sum[k-1] - sum[j]))
                    return true;
            }
        }


        return false;
    }
}
