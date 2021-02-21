package com.jetbrains.classic.array;

public class SubarraySum2 {

    // two pointer : 同向双指针
    // time: O(n)   -> O(2n)
    //
    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int[] sum = new int[A.length + 1];
        for (int i = 1; i <= A.length; i++) {
            sum[i] += sum[i - 1] + A[i - 1];
        }

        int lo = 0;
        int hi = 0;

        int ans = 0;
        for (int j = 1; j <= A.length; j++) {
            while (lo < j && sum[lo] < sum[j] - end) {
                lo++;
            }

            while (hi < j && sum[hi] <= sum[j] - start) {
                hi++;
            }

            ans += hi - lo;
        }

        return ans;
    }
}
