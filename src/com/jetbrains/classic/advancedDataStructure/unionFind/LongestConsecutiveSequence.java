package com.jetbrains.classic.advancedDataStructure.unionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    /*
        union find with parent, size hashMap
     */
    private class WeightedUnionFind{
        private HashMap<Integer, Integer> parent;
        private HashMap<Integer, Integer> size;
        private int maxSize;

        public WeightedUnionFind(){}
        public WeightedUnionFind(int[] nums) {
            int N = nums.length;
            parent = new HashMap<Integer, Integer>();
            size = new HashMap<Integer, Integer>();
            maxSize = 1;

            for (int i = 0; i < N; ++i) {
                parent.put(nums[i], nums[i]);
                size.put(nums[i], 1);
            }
        }

        private int getMaxSize() {
            return this.maxSize;
        }

        private Integer find(Integer num) {
            if (!parent.containsKey(num)) return null;

            Integer root = num;
            while (root != parent.get(root)) {
                root = parent.get(root);
            }

            while (num != root) {
                Integer next = parent.get(num);
                parent.put(num, root);
                num = next;
            }

            return root;
        }

        private void union(int p, int q) {
            Integer pRoot = find(p);
            Integer qRoot = find(q);

            if (pRoot == null || qRoot == null) return;
            if (pRoot == qRoot) return;

            int pSize = size.get(pRoot);
            int qSize = size.get(qRoot);

            if (pSize > qSize) {
                parent.put(qRoot, pRoot);
                size.put(pRoot, pSize + qSize);
                maxSize = Math.max(maxSize, pSize + qSize);
            } else {
                parent.put(pRoot, qRoot);
                size.put(qRoot, pSize + qSize);
                maxSize = Math.max(maxSize, pSize + qSize);
            }
        }
    }
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        WeightedUnionFind uf = new WeightedUnionFind(nums);

        for (int num:  nums) {
            if (num != Integer.MIN_VALUE) uf.union(num, num - 1);
            if (num != Integer.MAX_VALUE) uf.union(num, num + 1);
        }

        return uf.maxSize;
    }

    /*
        preferrable approach to solve the problem
        hashset dynamically adjust longest consecutive sequence range

     */
    public int longestConsecutive2(int[] nums) {
        int maxLen = 0;
        if (nums == null || nums.length == 0) {
            return maxLen;
        }

        Set<Integer> unvisited = getSet(nums);

        for (int n : nums) {
            if (unvisited.isEmpty()) continue;
            if (!unvisited.remove(n)) continue;

            int len = 1, leftOffset = -1, rightOffset = 1;
            while (unvisited.remove(n + leftOffset--)) len++;
            while (unvisited.remove(n + rightOffset++)) len++;

            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }

    private Set<Integer> getSet(int[] nums) {
        Set<Integer> ans = new HashSet<>();
        for (int n : nums) ans.add(n);
        return ans;
    }
}
