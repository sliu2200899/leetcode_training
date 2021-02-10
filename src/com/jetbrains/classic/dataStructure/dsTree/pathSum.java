package com.jetbrains.classic.dataStructure.dsTree;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class pathSum {
    /*
        pathSum 1:
        Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all
        the values along the path equals the given sum.

        algo:
        The most intuitive way is to use a recursion here. One is going through the tree by considering at each step
        the node itself and its children. If node is not a leaf, one calls recursively hasPathSum method for its children
        with a sum decreased by the current node value. If node is a leaf, one checks if the the current sum is zero,
        i.e if the initial sum was discovered.

        time: we visit each node exactly once, thus the time complexity is O(N), where NN is the number of nodes.
        Space complexity : in the worst case, the tree is completely unbalanced, e.g. each node has only
        one child node, the recursion call would occur NN times (the height of the tree), therefore the storage
        to keep the call stack would be O(N). But in the best case (the tree is completely balanced),
        the height of the tree would be log(N). Therefore, the space complexity in this case would be
        O(log(N)).
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            }
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /*
        pathSUm 2:
        Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

        Note: A leaf is a node with no children.

        algo:
The intuition for this approach is pretty straightforward. The problem statement asks us to find all root to leaf
paths in a given binary tree. If you simply consider the depth first traversal on a tree, all it does is traverse
once branch after another. All we need to do here is to simply execute the depth first traversal and maintain two things
along the way:

    A running sum of all the nodes traversed till that point in recursion and
    A list of all those nodes

If ever the sum becomes equal to the required sum, and the node where this happens is a leaf node, we can simply
add the list of nodes to our final solution. We keep on doing this for every branch of the tree and we will get
all the root to leaf paths in this manner that add up to a certain value. Basically, these paths are branches
and hence the depth first traversal makes the most sense here. We can also use the breadth first approach for
solving this problem. However, that would be super heavy on memory and is not a recommended approach for this
very problem. We will look into more details towards the end.

Algorithm

We'll define a function called recurseTree which will take the following parameters

    node which represents the current node we are on during recursion
    remainingSum which represents the remaining sum that we need to find going down the tree. We can also pass the current sum in our recursive calls. However, then we'd also need to pass the required sum as an additional variable since we'd have to compare the current sum against that value. By passing in remaining sum, we can avoid having to pass additional variable and just see if the remaining sum is 0 (or equal to the value of the current node).
Finally, we'll have to pass a list of nodes here which will simply contain the list of all the nodes we have seen till now on the current branch. Let's call this pathNodes.
The following examples assume the sum to be found is 22.


     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        helper(root, res, new ArrayList<Integer>(), sum);

        return res;
    }

    private void helper(TreeNode root, List<List<Integer>> res, List<Integer> list, int sum) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                list.add(root.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                return;
            }
        }

        list.add(root.val);
        helper(root.left, res, list, sum - root.val);
        helper(root.right, res, list, sum - root.val);
        list.remove(list.size() - 1);
    }

}
