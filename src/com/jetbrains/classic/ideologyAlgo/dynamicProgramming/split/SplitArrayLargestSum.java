package com.jetbrains.classic.ideologyAlgo.dynamicProgramming.split;

import java.util.Arrays;

public class SplitArrayLargestSum {
    /*              m-1                              1
    nums = [7,      2,      5,              10,     8], m = 2

             dp[i][k] := largest sum among these k subarrays from the array[0 ... i]

                k-1                              1
            1... j                        j+1 ...   i
            dp[i][k-1]                 sum(nums[j+1...i])

            dp[i][k] = min(max(dp[i][k-1], sum(nums[j+1...i])))   where k-1 <= j < i

    time: O(n^2 *m )
    space: O(nm)
*/
    public int splitArray(int[] nums, int m) {
        int n = nums.length;

        if (m > n) {
            return -1;
        }

        int[][] dp = new int[n+1][m+1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int k = 1; k <= m; ++k) {
                int md = 0;
                for (int j = i - 1; j >= k - 1; --j) {
                    md += nums[j];
                    dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k-1], md));
                }
            }
        }

        return dp[n][m];
    }

    /*
        binary search...

        time: O(log(sum(nums))* n)
        space: O(1)
     */
    public int splitArray2(int[] nums, int m) {
        long l = 0;
        long r = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            r += nums[i];
            if (l < nums[i]) {
                l = nums[i];
            }
        }
        long ans = r;
        while (l <= r) {
            // specify mid as the largest sum in each group,  for each group, you cannot group too many numbers there, in this case, you will get a group number cnt,
            // if this number is larger or equals to m, we can decrease the r ...
            long mid = (l + r) >> 1;
            long sum = 0;
            int cnt = 1;
            for (int i = 0; i < n; i++) {
                if (sum + nums[i] > mid) {
                    cnt ++;
                    sum = nums[i];
                } else {
                    sum += nums[i];
                }
            }
            if (cnt <= m) {
                ans = Math.min(ans, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)ans;
    }
}
