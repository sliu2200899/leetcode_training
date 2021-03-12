package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;

public class SerializeBT {
    /*
         1 2 # # 3 4 ...
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);

        return sb.toString().trim();
    }

    private void dfsSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(" ");
            return;
        }

        sb.append(root.val).append(" ");
        dfsSerialize(root.left, sb);
        dfsSerialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(" ");
        int[] index = new int[1];
        return dfsDeserialize(strs, index);
    }

    private TreeNode dfsDeserialize(String[] strs, int[] index) {
        if (strs[index[0]].equals("#")) {
            index[0]++;
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(strs[index[0]]));  // don't know the length of the left sub tree, how to do that...
        index[0]++;
        root.left = dfsDeserialize(strs, index);
        root.right = dfsDeserialize(strs, index);

        return root;
    }

    /*

        use () to represents left subtree and right subtree
        1(2()())(3(4()())(5()()))
     */
    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        if (root == null) return "";

        String left = serialize(root.left);
        String right = serialize(root.right);

        return Integer.toString(root.val) + "(" + left + ")" + "(" + right + ")";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if (data == null || data.length() == 0) return null;
        if (data.equals("()")) return null;

        int indexFirstLeft = data.indexOf('(');

        int rootVal = Integer.parseInt(data.substring(0, indexFirstLeft));

        int leftCount = 1;
        int indexMatchingRight = indexFirstLeft + 1;
        while (indexMatchingRight < data.length() && leftCount != 0) {
            if (data.charAt(indexMatchingRight) == '(') leftCount++;
            if (data.charAt(indexMatchingRight) == ')') leftCount--;

            indexMatchingRight++;
        }

        String leftSubtree = data.substring(indexFirstLeft + 1, indexMatchingRight - 1);
        String rightSubtree = data.substring(indexMatchingRight + 1, data.length() - 1);

        TreeNode root = new TreeNode(rootVal);
        root.left = deserialize1(leftSubtree);
        root.right = deserialize1(rightSubtree);

        return root;
    }

    /*
        summary of serialize and deserialize different binary tree

        if given tree is binary search tree?
        If the given Binary Tree is Binary Search Tree, we can store it by either storing preorder or post order traversal.
        In case of Binary Search Trees, only preorder or post order traversal is sufficient to store structure information.


        if given binary tree is complete tree?
        A Binary Tree is complete if all levels are completely filled except possibly the last level and all nodes of last level are as left as possible
        (Binary Heaps are complete Binary Tree). For a complete Binary Tree, level order traversal is sufficient to store the tree. We know that the first node is root,
        next two nodes are nodes of next level, next four nodes are nodes of 2nd level and so on.


        if given binary tree is full tree?
        A full Binary is a Binary Tree where every node has either 0 or 2 children. It is easy to serialize such trees as every internal node has 2 children.
        We can simply store preorder traversal and store a bit with every node to indicate whether the node is an internal node or a leaf node.


        how to store a general binary tree?
        A simple solution is to store both Inorder and Preorder traversals. This solution requires requires space twice the size of Binary Tree.
        We can save space by storing Preorder traversal and a marker for NULL pointers.

     */

}
