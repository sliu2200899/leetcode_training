package com.jetbrains.classic.searchByAlgorithm.bfs;

import com.jetbrains.innerStructure.TreeNode;

import java.util.*;

public class AllNodesDistanceKInBT {
    /*
        annotate parent

        time: O(N)
        space: O(N)
     */
    Map<TreeNode, TreeNode> parent;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parent = new HashMap<>();
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);

        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (dist == K) {
                List<Integer> list = new ArrayList<>();
                for (TreeNode n: queue) {
                    list.add(n.val);
                }
                return list;
            }
            for (int i = 0; i < size; ++i) {

                TreeNode node = queue.poll();

                if (node.left != null && !seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }

                if (node.right != null && !seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }

                TreeNode par = parent.get(node);
                if (par != null && !seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
            dist ++;
        }
        return new ArrayList<Integer>();
    }

    private void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }


    /*
        dfs version:  keep record of the distance in the path from target node to root node
     */
    Map<TreeNode, Integer> map = new HashMap<>();

    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        find(root, target);
        dfs2(root, target, K, map.get(root), res);
        return res;
    }

    // find target node first and store the distance in that path that we could use it later directly
    private int find(TreeNode root, TreeNode target) {
        if (root == null) return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }

    private void dfs2(TreeNode root, TreeNode target, int K, int length, List<Integer> res) {
        if (root == null) return;
        if (map.containsKey(root)) length = map.get(root);
        if (length == K) res.add(root.val);
        dfs2(root.left, target, K, length + 1, res);
        dfs2(root.right, target, K, length + 1, res);
    }
}
