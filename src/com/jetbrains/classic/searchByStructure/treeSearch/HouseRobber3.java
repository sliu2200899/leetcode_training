package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber3 {
    /*
        Approach 1: Recursion
Intuition

In this part, we explain how to think of this approach step by step. If you are only interested in the pure algorithm, you can jump to the algorithm part.

Since the tree itself is a recursive data structure, usually recursion is used to tackle these tree-relative problems.

Now we need a recursive function, let's call it helper (or whatever you want to call it).

Usually, we use a node as the input to the helper and add other arguments if we need more information.

The pseudo code of the common structure to solve recursive problems is as below:

function helper(node, other_information) {
    // basic case, such as node is null
    if node is null:
        return things like 0 or null
    else:
        do something relates to helper(node.left) and helper(node.right)
}
function answerToProblem(root) {
    return helper(root, other_information)
}
In some cases, we can use answerToProblem itself as the helper function.

OK, back to our problem. The next question is what should our helper function return.

Since the problem asks us to find out the maximum amount of money the thief can get, we could try using this maximum value as the return value of the helper function.

So helper receives a node as input, and returns the maximum amount the thief can get starting from this node.

Let's try writing the actual code. Well, it's a bit of trouble...

function helper(node) { // return the maximum by starting from this node
    if node is null: // basic case
        return 0
    else:
        two choices: rob this node or not?
        if not rob... we have: helper(node.left) + helper(node.right)

        what if rob? we get node.val!
        what about node.left and node.right? we can not rob them.
        Hmm... maybe we need to touch node.left.left and its other siblings... troublesome!
}
If we need to touch the grandchildren of this node, the case becomes complicated. Well, it is not infeasible but requires extra effort and code. Often, the best practice is to only touch its children, not its grandchildren.

The ideal situation is to make node.left and node.right automatically handle the grandchildren for us.

How to do it? Well, we can let them know whether we robbed this node or not by passing this information as input, like this:

    two choices: rob this node or not?

    rob = node.val + helper(node.left, parent_robbed=True)
                + helper(node.right,  parent_robbed=True)

    not_rob = helper(node.left, parent_robbed=False)
            + helper(node.right, parent_robbed=False)

    return max(rob, not_rob)
Cool, we also need to change the input correspondingly:

function helper(node, the parent is robbed or not?) {
    // return the maximum by starting from this node
    tackle basic case...

    if the parent is robbed:
        we can not rob this node.
        return helper(node.left, False) + helper(node.right, False)

    if the parent is not robbed:
        two choices: rob this node or not?
        calculate `rob` and `not_rob`...
        return max(rob, not_rob)
}
Good, now we have a functioning code. But the code is still not perfect.

An obvious problem is that the helper is called too many times. Ideally, it should only be called as least as possible to reduce redundant calculations.

For example, when calculating rob and not_rob:

    rob = node.val + helper(node.left, True) + helper(node.right, True)
    not_rob = helper(node.left, False) + helper(node.right, False)
The helper is called four times. Moreover, when we call helper(node.left, True) and helper(node.left, False), those two involve same calculations internally, such as helper(node.left.left, False).

In other words, helper(node.left.left, False) is called inside helper(node.left, True), and also is called inside helper(node.left, False). It is calculated twice! We do not want that.

What if... we can combine them together?

We return the results of helper(node.left, True) and helper(node.left, False) in a single function: helper(node.left). Those two results can be stored in a two-element array.

function helper(node) {
    // return original [`helper(node.left, True)`, `helper(node.left, False)`]
    tackle basic case...
    left = helper(node.left)
    right = helper(node.right)
    some calculation...
    return [max_if_rob, max_if_not_rob]
}
In this case, we fully use the calculation results without redundant calculation.
     */
    Map<TreeNode, Integer> robResult = new HashMap<>();
    Map<TreeNode, Integer> notRobResult = new HashMap<>();
    public int rob(TreeNode root) {
        return helper(root, false);
    }

    public int helper(TreeNode node, boolean parentRobbed) {
        if (node == null) {
            return 0;
        }

        if (parentRobbed) {
            if (robResult.containsKey(node)) {
                return robResult.get(node);
            }
            int result = helper(node.left, false) + helper(node.right, false);
            robResult.put(node, result);
            return result;
        } else {
            if (notRobResult.containsKey(node)) {
                return notRobResult.get(node);
            }

            int rob = node.val + helper(node.left, true) + helper(node.right, true);
            int notRob = helper(node.left, false) + helper(node.right, false);
            int result = Math.max(rob, notRob);
            notRobResult.put(node, result);
            return result;
        }
    }
}
