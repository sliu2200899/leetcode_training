package com.jetbrains.classic.topic.mathExpression;

public class LongestValidParenthesis {
    /*
    "((())"
         i
         i-1
     ( ()())
        dp[i-1] = 4

    This problem can be solved by using Dynamic Programming. We make use of a \text{dp}dp array where ith element of dp represents
    the length of the longest valid substring ending at ith index. We initialize the complete dp array with 0's. Now, it's obvious
    that the valid substrings must end with \text{‘)’}‘)’. This further leads to the conclusion that the substrings ending with
    \text{‘(’}‘(’ will always contain '0' at their corresponding \text{dp}dp indices. Thus, we update the \text{dp}dp array only
    when \text{‘)’}‘)’ is encountered.

    To fill \text{dp}dp array we will check every two consecutive characters of the string and if

     dp[i] longest valid parens so far ending with current character
     dp[i] = 0                    if '('
             if s[i-1] == '('     if ')'
                  dp[i] = (i >= 2? dp[i-2] : 0) + 2;
             else if (i - dp[i-1] > 0 && s.charAt(i - dp[i-1] - 1) == '(')
                  dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;

    test

    dp[1] = 0
    dp[2] = 0
    dp[3] = 2

    time: O(N)  single traversal of dp array is done
    space: O(N)   dp array size of n is used.

*/
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];

        int res = 0;
        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ')') {
                if (s.charAt(i-1) == '(') {
                    dp[i] = ((i >= 2) ? dp[i-2] : 0)+ 2;
                } else if (i - dp[i-1] > 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i-1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2);
                }

                res = Math.max(res, dp[i]);
            }
        }

        return res;
    }

    /*

        solution without extra space
        In this approach, we make use of two counters leftleft and rightright. First, we start traversing the string from the left
        towards the right and for every \text{‘(’}‘(’ encountered, we increment the leftleft counter and for every \text{‘)’}‘)’ encountered,
        we increment the rightright counter. Whenever leftleft becomes equal to rightright, we calculate the length of the current valid string
        and keep track of maximum length substring found so far. If rightright becomes greater than leftleft we reset leftleft and rightright to 00.


        algo:
        Let i and j denote the starting and ending indices of the longest valid subsequence.
        Note that in the forward pass after (fully) processing each character, it's always the case that left >= right. (*)
        This is in particular true after processing i-1 (immediately before processing i).

        Case 1: If immediately before i left = right, then the forward pass will detect the length of the longest valid subsequence.
        (The number of '(' and ')' is the same for any valid subseq. Thus after processing j, left = right.)

        Case 2: If immediately before i left > right, the forward pass will not detect the longest valid subsequence, but we claim the backward pass will.
        Similar to observation (*) above, note that right >= left after fully processing each element in the backward pass.
        We detect the longest valid subsequence in the backward pass if and only if right = left after processing j+1 (immediately before processing j).
        So what if right > left (in the backward pass immediately before processing j)?
        Well, then the maximality of our subsequence from i to j would be contradicted.
        Namely, we can increase j to j' so that (j,j'] has one more ')' than '(', and decrease i to i', so that [i',i) has one more '(' than ')'.
        The resulting subsequence from i' to j' will have longer length and will still be valid (the number of '(' and ')' will be incremented by the same amount).

        Thus, either the backward pass or forward pass (possibly both) will detect the longest valid subsequence.

        time: O(N)  single traversal of dp array is done
        space: O(1)   constant space used
     */
    public int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }

        return maxlength;
    }
}
