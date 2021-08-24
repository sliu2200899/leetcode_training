package com.jetbrains.classic.advancedDataStructure.Fenwick;

import java.util.*;

public class CountSmallerNumberAfterSelf {

    /*
       BIT

       nums:        5,2,6,1

       sorted:      1,2,5,6
       rank:        1,2,3,4    will be used later

       reversed:    1,6,2,5   // get the smaller number after self
       rank:        1,4,2,3

       the original problem has been reduced into this simple problem
           how many smaller number have we met before self?  kind of freq problem...

       every time, increase freq[rank] by 1
       num.  rank.      freq        prefix sum / query(rank - 1)
       -      -     [0,0,0,0,0]
       1      1     [0,1,0,0,0]       0
       6      4     [0,1,0,0,1]       1
       2      2     [0,1,1,0,1]       1
       5      3     [0,1,1,1,1]       2

       binary index tree => freq
       update & query O(log(n))
       total time complexity would be O(nlog(n))

   */
    public List<Integer> countSmaller1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        // get sorted array
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        // get the rank
        int rank = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sorted.length; ++i) {
            if (i == 0 || sorted[i] != sorted[i-1]) {
                map.put(sorted[i], rank++);
            }
        }

        FenwichTree tree = new FenwichTree(map.size());
        for (int i = nums.length - 1; i >= 0; --i) {
            res.add(tree.query(map.get(nums[i]) - 1));
            tree.update(map.get(nums[i]), 1);
        }

        Collections.reverse(res);


        return res;
    }

    private class FenwichTree{
        int size;
        int[] BIT;

        public FenwichTree(int n) {
            this.size = n;
            this.BIT = new int[n+1];
        }

        public void update(int i, int val) {
            i++;
            while (i <= size) {
                BIT[i] += val;
                i += (i & (-i));
            }
        }

        public int query(int i) {
            i++;
            int sum = 0;
            while (i > 0) {
                sum += BIT[i];
                i -= (i & (-i));
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
