package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {

    /*
        time: O(N * 2^N)    recursion tree  in the worst case
        space: O(N) the recursion stack, equivalent to the length of s

        Algorithm

        In the backtracking algorithm, we recursively traverse over the string in depth-first search fashion. For each recursive call, the beginning index of the string is given as \text{start}start.

            Iteratively generate all possible substrings beginning at \text{start}start index. The \text{end}end index increments from \text{start}start till the end of the string.

            For each of the substring generated, check if it is a palindrome.

            If the substring is a palindrome, the substring is a potential candidate. Add substring to the \text{currentList}currentList and perform a depth-first search on the remaining substring.
            If current substring ends at index \text{end}end, \text{end}+1end+1 becomes the \text{start}start index for the next recursive call.

            Backtrack if \text{start}start index is greater than or equal to the string length and add the \text{currentList}currentList to the result.
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        dfs(0, result, new ArrayList<String>(), s);

        return result;
    }

    private void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
        if (start >= s.length()) {
            result.add(new ArrayList<String>(currentList));
        }

        for (int end = start; end < s.length(); end++) {
            if(isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));

                dfs(end + 1, result, currentList, s);

                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int low, int high) {
        while(low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }

        return true;
    }


    /*
        backtracking with DP
     */
    public List<List<String>> partition2(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(currentList));
        }

        for (int end = start; end < s.length(); ++end) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start+1][end-1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
}
