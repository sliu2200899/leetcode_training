package com.jetbrains.classic;

import com.jetbrains.innerStructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) return list;

        dfs(root, list, new StringBuilder());
        return list;
    }

    private void dfs(TreeNode node, List<String> list, StringBuilder sb) {
        if (node.left == null && node.right == null) {
            sb.append(node.val);
            list.add(sb.toString());
            return;
        }

        sb.append(node.val);

        if (node.left != null) {
            int len = sb.length();
            sb.append("->");
            dfs(node.left, list, sb);
            sb.setLength(len);
        }
        if (node.right != null) {
            int len = sb.length();
            sb.append("->");
            dfs(node.right, list, sb);
            sb.setLength(len);
        }
    }
}
