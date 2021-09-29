package com.jetbrains.zCompanyInterviewPrep.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestCommonContinuousSubarray {
    /*
        [
          ["3234.html", "xys.html", "7hsaa.html"], // user1
          ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
        ]

        输出两个user的最长连续且相同的访问记录。

        ["xys.html", "7hsaa.html"]
     */

    /*
        和LCS做法类似，如果当前两个string相等就把当前格子变成[i - 1][j - 1] + 1。不相等就保留0。
     */

    public static List<String> longestCommonContinuousHistory(List<String> history1, List<String> history2) {
        List<String> res = new ArrayList<>();

        int count = -1;
        int[][] memo = new int[history1.size() + 1][history2.size() + 1];
        for (int i = 1; i <= history1.size(); ++i) {
            for (int j = 1; j <= history2.size(); ++j) {
                if (history1.get(i-1).equals(history2.get(j-1))) {
                    memo[i][j] = 1 + memo[i-1][j-1];
                    if(memo[i][j] > count) {
                        count = memo[i][j];
                        res.clear();
                        for (int k = i - count; k < i; ++k) {
                            res.add(history1.get(k));
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> history1 = Arrays.asList("3234.html", "xys.html", "7hsaa.html");
        List<String> history2 = Arrays.asList("3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html");

        List<String> res = longestCommonContinuousHistory(history1, history2);
        for (String s : res) {
            System.out.println(s);
        }

    }

}
