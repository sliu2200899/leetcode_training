package com.jetbrains.classic.advancedDataStructure.dsHeap;

import java.util.*;

public class SkylineProblem {
    /*
        1.sweep line
            priority queue

        time: O(n^2)
    */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();

        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});   // start of a building, height stored as negative
            height.add(new int[]{b[1], b[2]});   // end of a building, height stored as positive
        }

        Collections.sort(height, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));  // sort the height list

        // a pq that stores all the encountered buildings' heights in descending order
        PriorityQueue<Integer> pqmax = new PriorityQueue<>((a, b) -> (b - a));
        pqmax.offer(0);
        int preMax = 0;

        for (int[] h : height) {
            if (h[1] < 0) {    // h[1] < 0, that means it meets a new building, so add it to pq
                pqmax.offer(-h[1]);
            } else {            // h[1] > 0, that means it has reached the end of the building, so remove it from pq.   this operation take O(n), not optimal, can use treeMap to optimize
                pqmax.remove(h[1]);
            }

            // the current max height in all encountered buildings
            int curMax = pqmax.peek();
            // if the max height is different from the previous one,
            if (curMax != preMax) {
                res.add(new ArrayList<Integer>(Arrays.asList(h[0], curMax)));
                preMax = curMax;
            }
        }

        return res;
    }

    /*
        use treeMap to optimize the approach 1

        time: O(nlogn)
    */
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();

        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});   // start of a building, height stored as negative
            height.add(new int[]{b[1], b[2]});   // end of a building, height stored as positive
        }

        Collections.sort(height, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));  // sort the height list

        // a pq that stores all the encountered buildings' heights in descending order
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(0, 1);
        int preMax = 0;

        for (int[] h : height) {
            if (h[1] < 0) {
                if (!treeMap.containsKey(-h[1])) {
                    treeMap.put(-h[1], 1);
                } else {
                    int count = treeMap.get(-h[1]);
                    treeMap.put(-h[1], count + 1);
                }
            } else {
                int count = treeMap.get(h[1]);
                if (count == 1) {
                    treeMap.remove(h[1]);
                } else {
                    treeMap.put(h[1], count - 1);
                }
            }

            int curMax = treeMap.lastKey();    // get the largest value in treeMap
            if (preMax != curMax) {
                res.add(new ArrayList<>(Arrays.asList(h[0], curMax)));
                preMax = curMax;
            }
        }

        return res;
    }

    /*

        to optimize further: BIT
        refer to:  https://leetcode.com/problems/the-skyline-problem/discuss/433833/Explanations-on-difficulties-applying-Binary-Indexed-Tree(BITFenwick)-solution
        refer to:  https://leetcode.com/problems/the-skyline-problem/discuss/1064674/Java-Binary-Indexed-Tree-Fenwick-Tree-18ms

     */
}
