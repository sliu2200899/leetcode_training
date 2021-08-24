package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class OptimalUtilization {
    /*
        given 2 lists a and b. each element is a pair of integers where the first integer
        represents the unique id and the second integer represents a value.
        your task is to find an element from a and an elemnt from b such that the sume of
        their values is less or equals to target and as close to target as possible.

        Return a list of ids of selected elements. if no pair is possible, return an empty list.

        example
        input:
            a = [[1,2], [2,4], [3,6]]
            b = [[1,2]]

        output:
            [[2,1]]
     */
    public List<List<Integer>> compute(int[][] a, int[][] b, int target) {
        List<List<Integer>> res = new ArrayList<>();
        TreeMap<Integer, List<List<Integer>>> tree = new TreeMap<>();

        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < b.length; ++j) {
                int sum = a[i][1] + b[j][1];
                if (sum <= target) {
                    List<List<Integer>> list = tree.computeIfAbsent(sum, (k) -> new ArrayList<>());
                    list.add(Arrays.asList(a[i][0], b[j][0]));
                }
            }
        }

        return tree.floorEntry(target).getValue();
    }
}
