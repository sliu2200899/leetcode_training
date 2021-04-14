package com.jetbrains.classic.topic.stringMatch.decode;

public class DecodeWays {
    /*
        split the s

        226
        2 + f(26)  =>   find 1 way
        22 + f(6)   =>  find 1 way
        226

        f
        2   2   6
        i


    */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return dfs(s, 0, new Integer[s.length()]);
    }

    private int dfs(String s, int index, Integer[] memo) {
        if (index == s.length()) {
            return 1;
        }

        Integer ans = memo[index];
        if (ans != null) {
            return ans;
        }

        if (s.charAt(index) == '0') {
            memo[index] = 0;
            return memo[index];
        }

        ans = 0;
        for (int i = index+1; i <= s.length(); ++i) {
            String numStr = s.substring(index, i);
            if (numStr.length() >= 3 || (numStr.length() == 2 && Integer.parseInt(numStr) > 26)) {
                continue;
            }
            ans += dfs(s, i, memo);
        }
        memo[index] = ans;
        return ans;
    }
}
