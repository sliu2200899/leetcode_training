package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class LongestPalindromicSubstring {
    /*
        approach 1: expand around center
        We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are only 2n - 1 such centers.
        time: O(n^2)
        space: O(1)
     */
    int maxLen = 0;
    int startIndex = 0;

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        int len = s.length();
        for (int i = 0; i < len; ++i) {
            expandPalindrome(s, i, i+1);
            expandPalindrome(s, i, i);
        }

        return s.substring(startIndex, startIndex + maxLen);
    }

    private void expandPalindrome(String s, int start, int end) {
        if (end >= s.length()) return;

        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        if(maxLen < end - start - 1) {
            maxLen = end - start - 1;
            startIndex = start + 1;
        }
    }

    /*
        approach 2: Dynamic Programming
        To improve over the brute force solution, we first observe how we can avoid unnecessary re-computation while validating palindromes.
        Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome since the two left and right end letters are the same.

        we can define
        dp(i,j) = true, if the substring Si,...,Sj is a palindrome
                  false, otherwise

        Therefore,
        dp(i,j) = dp(i+1,j-1) if and only if Si == Sj

        The base case are
            dp(i,i) = true,
            dp(i, i+1) = (Si == Sj)
     */
    public String longestPalindrome2(String s) {
        String ans = "";
        if(s.length() == 0) return ans;
        int n = s.length(), start = 0, end = 0, maxLen = 0;
        boolean[][] DP = new boolean[n][n];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if (j-i <= 1) {  // process the abba
                    DP[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    DP[i][j] = s.charAt(i) == s.charAt(j) && DP[i+1][j-1];
                }
//                 (j - 1 <= i + 1 || );
                if(DP[i][j] && (j - i > end - start)) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end+1);
    }
}
