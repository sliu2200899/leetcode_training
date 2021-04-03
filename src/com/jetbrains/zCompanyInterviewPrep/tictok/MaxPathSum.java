package com.jetbrains.zCompanyInterviewPrep.tictok;

import com.jetbrains.innerStructure.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class MaxPathSum {
    /*
        结合分治法的思路，最大路径和有几种可能：

           1 不经过root的左路中的最大的any to any 路径/单独节点
           2 不经过root的右路中的最大的any to any 路径/单独节点
           3 经过root，root + 左路中最大的root to any path(单路) + 右路中最大的root to any path(单路)
           4 经过root，root + 左路/右路中较大的root to any path
           5 经过root，单独的root最大

        because case 4, 5 are the special cases for the case 3. So we only need to consider the case 1, 2, and 3.
        let's think about what does the recursion return?
            going back up the stack, we cannot just return the value which we got as max value,
            we can only form a path involving the parent node as the root, with either of the branches from parent node,
            so, we have to choose, the max gain between the gain from left branch and the gain from right branch of root node.

        So, one question arise... what is the maximum gain we can get from left or right branch of the node?
            there is a possibility that for a tree with all the negative numbers, the best path in fact is actually the largest number in the whole tree.
            So when we are looking at left and right branches of a node, we only care about teh gains we can make. This means
            if the sum of all the nodes on the either of the branches of a particular node is less than 0, that means that
            branch is not worth exploring at all.

     */
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(0, dfs(root.left));   // check the maximum gain we can get 345
        int right = Math.max(0, dfs(root.right));   // check the maximum gain we can get 345

        max = Math.max(max, root.val + left + right);  // from the perspective of the parent view. case 1 and 2

        return root.val + Math.max(left, right);
    }

    /*
        maximum path sum 2
        是求from root to any node的最大路径值，返回的path至少要有root.val
     */

    // dfs recursive way
    int max2 = Integer.MIN_VALUE;
    public int maxPathSum2(TreeNode root) {
        // write your code here
        if (root == null) return -1;

        dfs2(root, 0);
        return max2; // what if the root is negative?
    }

    private void dfs2(TreeNode root, int sum) {
        // base case
        if (root == null) return;

        max2 = Math.max(max2, sum + root.val);

        // recursive case
        if (root.left != null) dfs2(root.left, sum + root.val);
        if (root.right != null) dfs2(root.right, sum + root.val);
    }


    // Or we can solve it using dfs iterative method
    private class Tuple{
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
