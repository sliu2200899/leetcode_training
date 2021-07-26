package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.*;

/*
    refer to:  https://leetcode.com/problems/permutations/discuss/18239/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
    refer to:  https://leetcode.com/problems/palindrome-permutation-ii/discuss/69717/Backtrack-Summary%3A-General-Solution-for-10-Questions!!!!!!!!-Python-(Combination-Sum-Subsets-Permutation-Palindrome)

    combination 1,2
    subsets 1,2
    permutation 1,2
    next permutation *
    Permutation Sequence *
    Palindrome Partitioning 1,2(*)
    Palindrome Permutation 1,2(*)

 */


/*
    how to describe the backtracking?
    for the base case:
        If the current combination is done - add it to output.

    for the recursive case:
        Iterate over the integers from first to n.

            Add integer i into the current combination curr.

            Proceed to add more integers into the combination : backtrack(i + 1, curr).

            Backtrack by removing i from curr.
 */


public class Permutation {
    /*
    Analyzing the Time Complexity :

        1. How many times does function perm get called in its base case?
        As we can understand from the recursion explained above that for a string of length 3 it is printing 6 permutations which is actually 3!.
        This is because if it needs to generate permutation, it is needed to pick characters for each slot.
        If there are 3 characters in our string, in the first slot, there are 3 choices, 2 choices for the next slot (for each of 3 choices earlier, i.e multiplication and not addition) and so on.
        This tells that there are n! permutations being printed in the base case which is what is shown in the image.

        2. How many times does function perm get called before its base case?
        Consider that lines 9 through 12 are hit n number of times. Therefore, there will be no more than (n * n!) function calls.

        3. How long does each function call take? (optional)
        Since, each character of string prefix needs to be printed, thus executing line 7 will take O(n) time. Line 10 and line 11 will also take O(n)
        time combined due to string concatenation, as sum of rem, prefix and str.charAt(i) will always be n. Each function call therefore corresponds to O(n) work.

        4. What is the total runtime?
        Calling perm O(n * n!) times (as an upper bound) and each call takes O(n) time, the total runtime will not exceed O(n^2 * n!).
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        dfs(nums, res, new ArrayList<>(), new boolean[nums.length]);

        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] used) {
        // base case
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        // recursive case
        for (int i = 0; i < nums.length; ++i) {
            if (used[i]) continue;

            used[i] = true;
            list.add(nums[i]);
            dfs(nums, res, list, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }

    // follow up: what if the nums array contains duplicates
    /*
        First of all, let us review the general idea of permutation with an example.

        Given the input array [1, 1, 2], to generate a permutation of the array, we could follow the
        Depth-First Search (DFS) approach, or more precisely the backtracking technique as one will see later.

        let's walk through the example with paper and pencil as follows:
        1. Given the input of [1, 1, 2], at the first stage, we have 2 choices to pick a number as the first number in the final permutation,
        i.e. 1 and 2. Suppose that we pick the number 1, now the remaining numbers would become [1, 2].
        Note: The reason that we have only 2 choices instead of 3, is that there is a duplicate in the given input.
        Picking any of the duplicate numbers as the first number of the permutation would lead us to the same permutation at the end.
        Should the numbers in the array be all unique, we would then have the same number of choices as the length of the array.

        2. At the second stage, we now then have again 2 choices, i.e. [1, 2]. Let us pick again the number 1,
        which leaves us the only remaining number 2.

        3. Now at the third stage, we have only one candidate number left, i.e. [2]. We then pick the last remaining number,
        which leads to a final permutation sequence of [1, 1, 2].

        4. Moreover, we need to revisit each of the above stages, and make a different choice in order to try out all possibilities.
        The reversion of the choices is what we call backtracking.
     */
    // We illustrate all potential exploration in the following graph where each node represents a choice at a specific stage:
    // preferred way to solve the problem
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) return res;

        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        dfs2(res, list, nums, new boolean[nums.length]);
        return res;
    }

    private void dfs2(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] visited) {
        if (nums.length == list.size()) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            if (visited[i]) continue;

            visited[i] = true;
            list.add(nums[i]);
            dfs2(res, list, nums, visited);
            list.remove(list.size() - 1);
            visited[i] = false;

        }
    }
}
