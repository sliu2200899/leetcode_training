package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

public class LCS {
    /*
        Given two strings text1 and text2, return the length of their longest common subsequence.

        A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted
        without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not).
        A common subsequence of two strings is a subsequence that is common to both strings.

        If there is no common subsequence, return 0.

        Input: text1 = "abcde", text2 = "ace"
        Output: 3
        Explanation: The longest common subsequence is "ace" and its length is 3.
     */


    /*
        what is common subsequence?
        A common subsequence is a sequence of letters that appears in both strings. Not every letter in the strings has to be used,
        but letters cannot be rearranged. In essence, a subsequence of a string s is a string we get by deleting some letters in s.

     */

    /*
    approach 1: recursive + Memoization

    define function LCS(text1, text2):
    # If either string is empty there can be no common subsequence
    if length of text1 or text2 is 0:
        return 0

    letter1 = the first letter in text1

    # The case where the line *is not* part of the optimal solution
    case1 = LCS(text1.substring(1), text2)

    case2 = 0
    if letter1 is in text2:
        firstOccurence = first position of letter1 in text2
        # The case where the line *is* part of the optimal solution
        case2 = 1 + LCS(text1.substring(1), text2.substring(firstOccurence + 1))

    return maximum of case1 and case2


    time: O(M*N^2)
    We analyze a memoized-recursive function by looking at how many unique subproblems it will solve, and then what the cost of solving each subproblem is.
    The input parameters to the recursive function are a pair of integers; representing a position in each string. There are MM possible positions for the first string, and NN for the second string. Therefore, this gives us M \cdot NM⋅N possible pairs of integers, and is the number of subproblems to be solved.
    Solving each subproblem requires, in the worst case, an O(N) operation; searching for a character in a string of length NN. This gives us a total of (M⋅N^2)

    space: O(MN)
*/
    public int longestCommonSubsequence1(String text1, String text2) {
        // init
        int[][] memo = new int[text1.length() + 1][text2.length() + 1];
        // we need to initialise the memo array to -1 so that
        for (int i = 0; i < text1.length(); ++i) {
            for (int j = 0; j < text2.length(); ++j) {
                memo[i][j] = -1;
            }
        }

        return memoSolve(text1, text2, 0, 0, memo);
    }

    private int memoSolve(String text1, String text2, int p1, int p2, int[][] memo) {
        // check whether or not we've already solved this subproblem.
        // this also covers the base cases where p1 == text1.length
        // or p2 == text2.length
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }

        // option 1: we don't include text1[p1] in the solution
        int option1 = memoSolve(text1, text2, p1 + 1, p2, memo);

        // option 2: we include text1[p1] in the solution, as long as a mtach for it in text2 at or after p2 exists
        int firstOccurence = text2.indexOf(text1.charAt(p1), p2);
        int option2 = 0;
        if (firstOccurence != -1) {
            option2 = 1 + memoSolve(text1, text2, p1 + 1, firstOccurence + 1, memo);
        }

        // add the best answer to the memo before returning it
        memo[p1][p2] = Math.max(option1, option2);
        return memo[p1][p2];
    }

    /*
        a better memoization approach

        If the first character of each string is not the same, then either one or both of those characters will not be used in the final result
        (i.e. not have a line drawn to or from it). Therefore, the length of the longest common subsequence is max(LCS(p1 + 1, p2), LCS(p1, p2 + 1)).

        When the first character of each string is the same, the length of the longest common subsequence is 1 + LCS(p1 + 1, p2 + 1).
        In other words, we draw a line a line between the first two characters, adding 1 to the length to represent that line, and then solving
        the resulting subproblem (that has the first character removed from each string).

        time: O(mn)  solve each of the subproblems cost O(1). again, there are M* N subproblems, so we get a total time complexity of O(MN)
        space: O(mn)
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        // init
        int[][] memo = new int[text1.length() + 1][text2.length() + 1];
        // we need to initialise the memo array to -1 so that
        for (int i = 0; i < text1.length(); ++i) {
            for (int j = 0; j < text2.length(); ++j) {
                memo[i][j] = -1;
            }
        }

        return memoSolve2(text1, text2, 0, 0, memo);
    }

    private int memoSolve2(String text1, String text2, int p1, int p2, int[][] memo) {
        // check whether or not we've already solved this subproblem.
        // this also covers the base cases where p1 == text1.length
        // or p2 == text2.length
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }

        // recursive cases
        int answer = 0;
        if (text1.charAt(p1) == text2.charAt(p2)) {
            answer = 1 + memoSolve(text1, text2, p1 + 1, p2 + 1, memo);
        } else {
            answer = Math.max(memoSolve(text1, text2, p1, p2 + 1, memo), memoSolve(text1, text2, p1 + 1, p2, memo));
        }

        // add the best answer to the memo before returning it
        memo[p1][p2] = answer;
        return memo[p1][p2];
    }

    /*
        bottom up:
        Remembering back to Approach 2, there were two cases.

            The first letter of each string is the same.
            The first letter of each string is different.

            For the first case, we solve the subproblem that removes the first letter from each, and add 1.
            In the grid, this subproblem is always the diagonal immediately down and right.

            For the second case, we consider the subproblem that removes the first letter off the first word,
            and then the subproblem that removes the first letter off the second word. In the grid,
            these are subproblems immediately right and below.

        Putting this all together, we iterate over each column in reverse, starting from the last column (we could also do rows, the final result will be the same)
        // If the corresponding characters for this cell are the same...
        if (text1.charAt(row) == text2.charAt(col)) {
          dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
        // Otherwise they must be different...
        } else {
          dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1]);
        }


        time: O(MN)
        space: O(MN)
     */

    public int longestCommonSubsequence3(String text1, String text2) {
        // Make a grid of 0's with text2.length() + 1 columns
        // and text1.length() + 1 rows.
        int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];

        // Iterate up each column, starting from the last one.
        for (int col = text2.length() - 1; col >= 0; col--) {
            for (int row = text1.length() - 1; row >= 0; row--) {
                // If the corresponding characters for this cell are the same...
                if (text1.charAt(row) == text2.charAt(col)) {
                    dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
                    // Otherwise they must be different...
                } else {
                    dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1]);
                }
            }
        }

        // The original problem's answer is in dp_grid[0][0]. Return it.
        return dpGrid[0][0];
    }

    /*
        Approach 4: Dynamic Programming with Space Optimization

        time: O(mn)
        space: O(min(m,n))
     */
    public int longestCommonSubsequence4(String text1, String text2) {
        // If text1 doesn't reference the shortest string, swap them.
        if (text2.length() < text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        // The previous column starts with all 0's and like before is 1
        // more than the length of the first word.
        int[] previous = new int[text1.length() + 1];

        // Iterate through each column, starting from the last one.
        for (int col = text2.length() - 1; col >= 0; col--) {
            // Create a new array to represent the current column.
            int[] current = new int[text1.length() + 1];
            for (int row = text1.length() - 1; row >= 0; row--) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    current[row] = 1 + previous[row + 1];
                } else {
                    current[row] = Math.max(previous[row], current[row + 1]);
                }
            }
            // The current column becomes the previous one.
            previous = current;
        }

        // The original problem's answer is in previous[0]. Return it.
        return previous[0];
    }

}
