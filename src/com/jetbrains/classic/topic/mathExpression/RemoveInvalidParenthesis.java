package com.jetbrains.classic.topic.mathExpression;

import java.util.*;

public class RemoveInvalidParenthesis {
        /*
        algo:
            step1: compute all the misplaced left and right parentheses
            step2: recursively remove some parentheses and check this is a valid parenthese

            compute()
                left, right
                for (char c : s.toCharArray()) {
                    if (c == '(')
                        left++
                    else if (c == ')')
                        if (left != 0)
                            left--;
                        else
                            right++;
                }



            dfs(s, res, left, right, index)
                if (left == 0 && right == 0 && check(s))
                    add s to res

                if (index == s.length)
                    return;

                if (s[index] == '(' && left > 0)
                    remove this '(' and dfs()
                else if (s[index] == ')' && right > 0)
                    remove this ')' and dfs()



            check()
                count('(') >= count(')')  i < n - 1
                count('(') == count(')')  i == n - 1

        test:

            left:
            right:


        time:
            worst case O(2^n)
        space:
            O(n)
    */

    public List<String> removeInvalidParentheses(String s) {

        Set<String> set = new HashSet<>();

        // compute the misplaced parentheses
        int left = 0, right = 0;
        for (char c: s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left != 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        dfs("", s, set, left, right, 0, 0);
        return new ArrayList<String>(set);
    }

    private void dfs(String str, String s, Set<String> res, int left, int right, int index, int offset) {
        if (left == 0 && right == 0 && index == s.length() && offset == 0) {
            res.add(str);
            return;
        }

        if (left < 0 || right < 0 || index == s.length() || offset < 0) {
            return;
        }

        if (s.charAt(index) == '(') {
            dfs(str, s, res, left-1, right, index+1, offset);  // remove current '('
            dfs(str+'(', s, res, left, right, index+1, offset+1);   // keep current '('
        } else if (s.charAt(index) == ')') {
            dfs(str, s, res, left, right-1, index+1, offset);  // remove current ')'
            dfs(str+')', s, res, left, right, index+1, offset-1);  // keep current ')'
        } else {
            dfs(str + s.charAt(index), s, res, left, right, index+1, offset);
        }
    }

    /*
        solution 2: dfs w/o set
        more elegant way to sovle the problem
     */
    public List<String> removeInvalidParentheses2(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    // last_i represents  the start position where we do the +1, -1 count,
    // last_j represents the start position where we look for a parenthesis that can be removed
    public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            // this part is used for addressing the invalid parens
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            // after finishing the remove work, we need to return.
            return;
        }
        // if the string is valid, we need to check it from right to left, since )(( can pass the above loop, but it's not a valid parens
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    /*
        bfs
        The good thing of using BFS is that we can guarantee the number of parentheses that need to be removed is minimal, also no recursion call is needed in BFS.
        The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ), check if they are valid,
        if found valid ones on the current level, put them to the final result list and we are done, otherwise, add them to a queue and carry on to the next level.

        Time complexity:

        In BFS we handle the states level by level, in the worst case, we need to handle all the levels, we can analyze the time complexity level by level and add them up to get the final complexity.

        On the first level, there's only one string which is the input string s, let's say the length of it is n, to check whether it's valid, we need O(n) time.
        On the second level, we remove one ( or ) from the first level, so there are C(n, n-1) new strings, each of them has n-1 characters,
        and for each string, we need to check whether it's valid or not, thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level, total time complexity is (n-2) x C(n, n-2), so on and so forth...

        Finally we have this formula:
            T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1)
     */
    public List<String> removeInvalidParentheses3(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // init
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                res.add(s);
                found = true;
            }

            if (found) continue;  // at current level, have found the valid parens, don't need to explore deeper

            // generate all possbile states
            for (int i = 0; i < s.length(); ++i) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i)  + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }


    // helper function checks if string s contains valid parens
    private boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')') {
                if (count == 0) {
                    return false;
                } else {
                    count--;
                }
            }
        }

        return count == 0;
    }
}
