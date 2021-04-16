package com.jetbrains.classic.advancedDataStructure.Fenwick;

import java.util.*;

public class CountSmallerNumberAfterSelf {

    /*
        fanwick tree
     */

    /*
        idea of Fenwick tree

        nums
        [5,2,6,1]

        sorted   // be used later to get ranks
        [1,2,5,6]

        ranks  // mapping
        reverse nums:
        [1,6,2,5]
        [1,4,2,3]


        tree
    */
    public List<Integer> countSmaller2(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        // sort the unique numbers
        Arrays.sort(sorted);
        // map the number to its rank
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < sorted.length; ++i) {
            if (i == 0 || sorted[i] != sorted[i-1]) {
                ranks.put(sorted[i], ++rank);
            }
        }

        List<Integer> ans = new ArrayList<>();
        FenwickTree tree = new FenwickTree(ranks.size());
        // scan the numbers in reversed order
        for (int i = nums.length - 1; i >= 0; --i) {
            ans.add(tree.query(ranks.get(nums[i]) - 1));   // get the count of smaller numbers
            tree.update(ranks.get(nums[i]), 1);   // 1  is delta... just increase the count of the rank of current number
        }

        Collections.reverse(ans);

        return ans;
    }

    class FenwickTree{
        int[] BIT;
        int size;

        public FenwickTree(int n) {
            this.size = n;
            BIT = new int[n + 1];
        }

        public void update(int i, int delta) {

            while (i <= size) {
                BIT[i] += delta;
                i += (i & -i);
            }
        }

        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += BIT[i];
                i -= (i & -i);
            }

            return sum;
        }
    }

    /*
    BST, but not balanced, time: O(nlogn) ~ O(n^2)
 */
    class Node {
        int val;
        int count;
        int left_count;
        Node left;
        Node right;
        public Node(int val) { this.val = val; this.count = 1; }
        public int less_or_equal() { return count + left_count; }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0) return ans;
        int n = nums.length;
        Node root = new Node(nums[n - 1]);
        ans.add(0);
        for (int i = n - 2; i >= 0; --i)
            ans.add(insert(root, nums[i]));
        Collections.reverse(ans);
        return ans;
    }

    private int insert(Node root, int val) {
        if (root.val == val) {
            ++root.count;
            return root.left_count;
        } else if (val < root.val) {
            ++root.left_count;
            if (root.left == null) {
                root.left = new Node(val);
                return 0;
            }
            return insert(root.left, val);
        } else  {
            if (root.right == null) {
                root.right = new Node(val);
                return root.less_or_equal();
            }
            return root.less_or_equal() + insert(root.right, val);
        }
    }
}
