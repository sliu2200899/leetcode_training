package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequence {
    /*
        approach one : bruteforce double loop  TLE
     */
    public int longestSubsequence(int[] arr, int difference) {

        int len = arr.length;

        int ans = 1;
        for (int i = 0; i < len; ++i) {

            int start = arr[i];
            int j = i + 1, target = arr[i] + difference;
            int ret = 1;
            while (j < len) {
                if (target == arr[j]) {
                    ret++;
                    target = arr[j] + difference;
                }
                j++;
            }
            ans = Math.max(ans, ret);
        }

        return ans;
    }

    /*
        approach two:  DP + HashTable

        in this problem, x is not the index, but the value, so we need the hashtable to store the state.
        dp[x] = dp[x - d] + 1, if x - d exists
                1,             otherwise
     */

    public int longestSubsequence2(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();

        int ans = 1;
        for (int i = 0; i < arr.length; ++i) {
            if (!map.containsKey(arr[i] - difference)) {
                map.put(arr[i], 1);
            }
            else {
                map.put(arr[i], map.get(arr[i] - difference) + 1);
                ans = Math.max(ans, map.get(arr[i]));
            }
        }

        return ans;
    }

    /*

        for (int i = 0; i < arr.length; ++i) {
            map.put(arr[i], map.getOrDefault(arr[i] - difference, 0) + 1);
            ans = Math.max(ans, map.get(arr[i]));
        }
     */

}
