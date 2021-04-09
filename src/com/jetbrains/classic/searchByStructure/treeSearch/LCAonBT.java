package com.jetbrains.classic.searchByStructure.treeSearch;

import com.jetbrains.innerStructure.TreeNode;
import com.jetbrains.innerStructure.TreeNodeParent;

import java.util.*;

public class LCAonBT {
    /*
        LCA of a BST
     */
    // simple, just skip

    /*
        LCA of BT
        recursion solve the problem...
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // get all the nodes from root to p
        List<TreeNode> listP = new ArrayList<>();
        getNodes(root, p, listP, new ArrayList<TreeNode>());

        // get all the nodes from root to q
        List<TreeNode> listQ = new ArrayList<>();
        getNodes(root, q, listQ, new ArrayList<TreeNode>());

        // find their LCA
        TreeNode prev = null;
        int cur = 0;
        while (cur != listQ.size() && cur != listP.size()) {
            if (listQ.get(cur).val != listP.get(cur).val) {
                return prev;
            }

            prev = listP.get(cur);
            cur++;
        }
        return prev;
    }

    private void getNodes(TreeNode node, TreeNode target, List<TreeNode> list, List<TreeNode> tmp) {
        if (node.val == target.val) {
            tmp.add(node);
            for (TreeNode n : tmp) {
                list.add(n);
            }
            return;
        }

        tmp.add(node);
        if (node.left != null) getNodes(node.left, target, list, tmp);
        if (node.right != null) getNodes(node.right, target, list, tmp);
        tmp.remove(tmp.size() - 1);
    }

    // preferred way
    // use DC to explore the binary tree,
    // if we find either p or q, we just return the node
    // otherwise, we search the p or q in left or right subtree.
    // if both left and right is not null, the current node is the LCA
    // otherwise, we return the left or right node which is not null.
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        if (left != null && right != null) return root;
        return left == null ? right : left;
    }

    // another way to solve this problem is to record the index for each treenode.
    // then we try to find the LCA on BST using the index
    public TreeNode lowestCommonAncestorInorder(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        int index = 0;
        Map<TreeNode, Integer> map = new HashMap<>();
        while(!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            map.put(node, index++);
            cur = node.right;
        }

        return getLCA(root, map, p, q);
    }

    private TreeNode getLCA(TreeNode root, Map<TreeNode, Integer> map, TreeNode p, TreeNode q) {
        while (root != null) {
            if (map.get(p) < map.get(root) && map.get(q) < map.get(root)) {
                return getLCA(root.left, map, p, q);
            } else if (map.get(p) > map.get(root) && map.get(q) > map.get(root)) {
                return getLCA(root.right, map, p, q);
            } else {
                break;
            }
        }
        return root;
    }

    // follow up: we need to consider the node doesn't exist in the tree (LCAonBT)  w/o checking nodes existence
    boolean pFound = false;
    boolean qFound = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = LCA(root, p, q);
        return pFound && qFound ? LCA : null;
    }

    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        TreeNode left = LCA(root.left, p, q);
        TreeNode right = LCA(root.right, p, q);

        // those two part should be placed after lca(left) and lca(right)
        // because Keep traversing down the entire tree. If you return early, the above example would be null, because the code stops when it finds 5 and does not keep searching for 4.
        if (root == p) {
            pFound = true;
            return root;
        }
        if (root == q) {
            qFound = true;
            return root;
        }
        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }


    // follow up: what if the node has parent pointer
    // can use a hashset, everytime,
    public static TreeNodeParent lowestCommonAncester3(TreeNodeParent root, TreeNodeParent p, TreeNodeParent q) {
        if (root == null || q == null || p == null) return null;

        Set<TreeNodeParent> set = new HashSet<>();
        while(p != null) {
            set.add(p);
            p = p.parent;
        }

        while(q != null) {
            if (set.contains(q)) {
                break;
            }
            q = q.parent;
        }
        return q;
    }

    // w/o hashset, we can use 2 arraylists to store all the treenodes in the path from A or B to the root.
    // and then perform a backward search on hte two path, stop when we find the first different treenode
    public TreeNodeParent lowestCommonAncestorII(TreeNodeParent root, TreeNodeParent A, TreeNodeParent B) {

        if (root == null || A == null || B == null) return null;

        ArrayList<TreeNodeParent> pathA = getPath2Root(A);
        ArrayList<TreeNodeParent> pathB = getPath2Root(B);

        int sizeA = pathA.size() - 1;
        int sizeB = pathB.size() - 1;

        TreeNodeParent lowestAncestor = null;
        while (sizeA >= 0 && sizeB >= 0) {
            if (pathA.get(sizeA) != pathB.get(sizeB)) {
                break;
            }
            lowestAncestor = pathA.get(sizeA);
            sizeA--;
            sizeB--;
        }
        return lowestAncestor;
    }

    private ArrayList<TreeNodeParent> getPath2Root(TreeNodeParent root) {
        ArrayList<TreeNodeParent> res = new ArrayList<>();
        while (root != null) {
            res.add(root);
            root = root.parent;
        }
        return res;
    }
}
