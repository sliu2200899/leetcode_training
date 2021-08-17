package com.jetbrains.classic.topic.stringMatch.decode;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
    /*
        s = "12"

             |

             1, -> further search
             12,  -> further seach

             base case

             The problem deals with finding number of ways of decoding a string. What helps to crack the problem is to think
             why there would be many ways to decode a string. The reason is simple since at any given point we either decode
             using two digits or single digit. This choice while decoding can lead to different combinations.
    */
    public int numDecodings(String s) {
        Map<Integer, Integer> memo = new HashMap<>();
        return helper(s, 0, memo);
    }

    private int helper(String s, int idx, Map<Integer, Integer> memo) {
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }

        // if you reach the end of the string
        if (idx == s.length()) {
            return 1;
        }

        if (s.charAt(idx) == '0') {
            return 0;
        }

        int ans = helper(s, idx + 1, memo);
        if (idx + 2 <= s.length() && Integer.valueOf(s.substring(idx, idx+2)) <= 26) {
            ans += helper(s, idx + 2, memo);
        }

        memo.put(idx, ans);

        return ans;
    }
}
