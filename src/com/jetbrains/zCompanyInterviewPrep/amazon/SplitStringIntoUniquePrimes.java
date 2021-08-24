package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.ArrayList;
import java.util.List;

public class SplitStringIntoUniquePrimes {
    /*
        Given a string made up of integers 0 to 9, count the number of ways to split the string into
        prime numbers in the range of 2 to 1000 inclusive, using up all the characters in the string.

        Each prime number, pn, must not contain leading 0s, and 2 <= pn <= 1000

        example:
            input: 31173
            output: 6
                [3,11,7,3]
                [3,11,73]
                [31,17,3]
                [31,173]
                [311,73]
                [311,7,3]
     */
    public int findNumberOfWays(String s) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(s, 0, res, new ArrayList<>());
        return res.size();
    }

    public void dfs(String s, int idx, List<List<Integer>> res, ArrayList<Integer>list) {
        if (idx == s.length()) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = idx; i < s.length(); ++i) {
            int num = Integer.parseInt(s.substring(idx, i+1));
            if (isPrime(num)) {
                list.add(num);
                dfs(s, i+1, res, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        // check if n is multiple of 2
        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i < n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }


}
