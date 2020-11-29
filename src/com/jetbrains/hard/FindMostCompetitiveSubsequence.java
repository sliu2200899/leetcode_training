package com.jetbrains.hard;

public class FindMostCompetitiveSubsequence {
    /*
        Use a mono increasing stack.


        Explanation
        Keep a mono increasing stacks result.
        If current element a is smaller then the last element in the stack,
        we can replace it to get a smaller sequence.

        Before we do this,
        we need to check if we still have enough elements after.
        After we pop the last element from stack,
        we have stack.size() - 1 in the stack,
        there are A.size() - i can still be pushed.
        if stack.size() - 1 + A.size() - i >= k, we can pop the stack.

        Then, is the stack not full with k element,
        we push A[i] into the stack.

        Finally we return stack as the result directly.


        Complexity
        Time O(n)
        Space O(k)


     */
    public int[] mostCompetitive(int[] A, int k) {
        int stack[] = new int[k], n = A.length, j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && stack[j - 1] > A[i] && j - 1 + n - i >= k)
                j--;
            if (j < k)
                stack[j++] = A[i];
        }
        return stack;
    }
}
