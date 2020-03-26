package com.jetbrains.hard;

import com.jetbrains.innerStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class BTMaximumPathSum2 {
    // 是求from root to any node的最大路径值，返回的path至少要有root.val

    // dfs recursive way 
    int max = Integer.MIN_VALUE;
    public int maxPathSum2(TreeNode root) {
        // write your code here
        if (root == null) return -1;

        dfs(root, 0);
        return max; // what if the root is negative?
    }

    private void dfs(TreeNode root, int sum) {
        // base case
        if (root == null) return;

        max = Math.max(max, sum + root.val);

        // recursive case
        if (root.left != null) dfs(root.left, sum + root.val);
        if (root.right != null) dfs(root.right, sum + root.val);
    }


    // Or we can solve it using dfs iterative method
    class Tuple{
        TreeNode node;
        int sum;

        public Tuple(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }
    public int maxPathSum2a(TreeNode root) {
        if (root == null) return -1;

        Deque<Tuple> stack = new LinkedList<>();
        stack.push(new Tuple(root, root.val));

        int max = root.val;
        while (!stack.isEmpty()) {
            Tuple t = stack.pop();
            max = Math.max(max, t.sum);

            if (t.node.left != null) stack.push(new Tuple(t.node.left, t.sum + t.node.left.val));
            if (t.node.right != null) stack.push(new Tuple(t.node.right, t.sum + t.node.right.val));
        }

        return max;
    }

}
