package com.jetbrains.hard;

import com.jetbrains.innerStructure.TreeNode;

import java.util.*;

public class UniqueBST {
    Map<Integer, Integer> map;
    public int numTrees(int n) {
        map = new HashMap<>();

        return helper(n);
    }

    private int helper(int m) {
        if (map.containsKey(m)) {
            return map.get(m);
        }

        // base case
        if (m == 0 || m == 1) {
            map.put(m, 1);
            return 1;
        }

        // recursive case
        int total = 0;
        for (int i = 1; i <= m; ++i) {
            total += (helper(i - 1) * helper(m - i));
        }

        // DP memorization
        map.put(m, total);

        return total;
    }


    /*
        another kind of memorization with resursion
        The problem can be solved in a dynamic programming way.

Given a sorted sequence 1 ... n, to construct a Binary Search Tree (BST) out of the sequence, we could enumerate each number i in the sequence,
and use the number as the root, then, the subsequence 1 ... (i-1) on its left side would lay on the left branch of the root, and similarly the right subsequence (i+1) ... n lay on the right branch of the root.
We then can construct the subtree from the subsequence recursively. Through the above approach, we could be assured that the BST that we construct are all unique, since they start from unique roots.

As we can see, the problem can be reduced into problems with smaller sizes, instead of recursively (also repeatedly) solve the subproblems, we can store the solution of subproblems and reuse them later, i.e. the dynamic programming way.

Time complexity : the main computation of the algorithm is done at the statement with G[i]. So the time complexity is essentially the number of iterations for the statement,therefore the time complexity is O(N^2)
Space complexity : The space complexity of the above algorithm is mainly the storage to keep all the intermediate solutions, therefore O(N).
     */
    public int numTrees2(int n) {
        if (n == 0 || n == 1) return 1;

        int[] arr = new int[n + 1];
        Arrays.fill(arr, -1);

        return helper(n, arr);
    }

    private int helper(int n, int[] arr) {

        if (arr[n] == -1) {

            if (n == 0 || n == 1) {
                arr[n] = 1;
                return arr[n];
            }

            if (n == 2) {
                arr[n] = 2;
                return arr[n];
            }

            int num = 0;
            for (int i = 1; i <= n; ++i) {
                num += (helper(i - 1, arr) * helper(n - i, arr));
            }
            arr[n] = num;
        }

        return arr[n];
    }



    
    // follow up, print all the tree node
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return helper(1, n);
    }

    private List<TreeNode> helper(int low, int high) {
        List<TreeNode> list = new ArrayList<>();

        if (low > high) {
            list.add(null);
            return list;
        }

        for (int i = low; i <= high; ++i) {

            List<TreeNode> left = helper(low, i - 1);
            List<TreeNode> right = helper(i + 1, high);

            for (TreeNode j : left) {
                for (TreeNode k : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = j;
                    root.right = k;
                    list.add(root);
                }
            }
        }

        return list;
    }
}
