package com.jetbrains.master;

import com.jetbrains.innerStructure.Node;
import com.jetbrains.innerStructure.TreeNodeNext;

import java.util.Deque;
import java.util.LinkedList;

public class PopulatingNextRightPointerTree {
    public TreeNodeNext connect(TreeNodeNext root) {

        if (root == null) return null;

        Deque<TreeNodeNext> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNodeNext node = queue.poll();
                if (i == size - 1) node.next = null;
                else {
                    TreeNodeNext next = queue.peek();
                    node.next = next;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return root;
    }
}
