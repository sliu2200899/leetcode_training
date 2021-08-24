package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.Arrays;

public class MaximizeScoresAfterNOperation {
    /*
        given nums, an array of positive integers of size 2 * n. you need to perform n operations on this array.

        in the ith operation,
            1. choose two elements, x, and y
            2. receive a score of i * gcd(x, y)
            3. remove x and y from nums.

        return maximum score you can receive after performing n operations
     */
    int[] map;
    int[][] dp;


    /*
        The greatest common divisor of two integers, also known as GCD, is the greatest positive integer that divides the two integers. ...
        The least common multiple , also known as the LCM, is the smallest number that is divisible by both integer a and b

        public int lcm(int a, int b) {
            return (a / gcd(a,b)) * b;
        }

     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }


    public int maxScore(int[] nums) {
        map = new int[(int)Math.pow(2, 14)];   // maximum mask would be 16383 which has 14 '1' in its binary representation
        Arrays.fill(map, -1);
        dp = new int[nums.length][nums.length];

        for (int i = 0; i < dp.length; ++i) {
            for (int j = i + 1; j < dp.length; ++j) {
                dp[i][j] = gcd(nums[i], nums[j]);
            }
        }

        return find(nums, 0, 1);
    }

    public int find(int[] nums, int mask, int idx) {
        if (map[mask] != -1) return map[mask];
        int ans = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                int a = (1 << i) + (1 << j);  // can left shift at most 13 bits
                if ((mask&a) == 0) {   // the pair(i,j) still available to choose
                    ans = Math.max(ans, idx*dp[i][j] + find(nums, mask|a, idx+1));
                }
            }
        }
        map[mask] = ans;
        return ans;
    }
}
