package com.jetbrains.zCompanyInterviewPrep.karat;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.util.*;

public class Ancester {
    /*
         0个或1个parent的节点
         输入是int[][] input, input[0]是input[1] 的parent，比如 {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}}会形成上面的图
        第一问是只有0个parents和只有1个parent的节点
     */
    public int[] findNodesWithZeroOrOneParent(int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0].length  == 0) {
            return new int[0];
        }

        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> ind = new HashMap<>();
        for (int[] edge : edges) {
            adj.putIfAbsent(edge[0], new ArrayList<>());
            adj.get(edge[0]).add(edge[1]);

            ind.put(edge[1], ind.getOrDefault(edge[1], 0) + 1);
            ind.put(edge[0], 0);
        }

        List<Integer> res = new ArrayList<>();
        for (int key : ind.keySet()) {
            if (ind.get(key) <= 1) {
                res.add(key);
            }
        }

        return res.stream().mapToInt(x -> x).toArray();
    }

    /*
        两个节点是否有公共祖先
     */
    public boolean hasCommonAncestor(int[][] edges, int x, int y) {
        if (x == y) {
            return true;
        }

        // build parent list
        Map<Integer, List<Integer>> parent = new HashMap<>();
        for (int[] edge : edges) {
            parent.putIfAbsent(edge[1], new ArrayList<>());
            parent.get(edge[1]).add(edge[0]);

            parent.putIfAbsent(edge[0], new ArrayList<>());
        }

        Set<Integer> ancA = createAncestorList(x, parent);
        Set<Integer> ancB = createAncestorList(y, parent);
        for(Integer p : ancA){
            if(ancB.contains(p)){
                return true;
            }
        }

        return false;
    }

    private Set<Integer> createAncestorList(int a, Map<Integer, List<Integer>> map) {
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> ancestors = new HashSet<>();
        queue.offer(a);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int parent = queue.poll();
                ancestors.add(parent);
                if (map.containsKey(parent)) {
                    queue.addAll(map.get(parent));
                }
            }
        }
        return ancestors;
    }

    /*
       最远祖先
     */



    public static void main(String[] args) {
//        int[][] edges = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
//        System.out.println(Arrays.toString(findNodesWithZeroOrOneParent(edges)));

        Ancester pc = new Ancester();
        int[][] relations = {
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5},{4, 8}, {4, 9}, {9, 11}, {14, 4}, {13, 12}, {12, 9}
        };
        System.out.println(pc.hasCommonAncestor(relations, 3, 8));  // =>false
        System.out.println(pc.hasCommonAncestor(relations, 5, 8));  // =>true
        System.out.println(pc.hasCommonAncestor(relations, 6, 8));  // =>true
        System.out.println(pc.hasCommonAncestor(relations, 6, 9));  // =>true
        System.out.println(pc.hasCommonAncestor(relations, 1, 3));  // =>false
        System.out.println(pc.hasCommonAncestor(relations, 3, 1));  // =>false
        System.out.println(pc.hasCommonAncestor(relations, 7, 11));  // =>true
        System.out.println(pc.hasCommonAncestor(relations, 6, 5));  // =>true
        System.out.println(pc.hasCommonAncestor(relations, 5, 6));  // =>true
    }

}
