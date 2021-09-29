package com.jetbrains.zCompanyInterviewPrep.linkedIn;

import java.util.List;

public class NestListWeightSum {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *     // Constructor initializes an empty nested list.
     *     public NestedInteger();
     *
     *     // Constructor initializes a single integer.
     *     public NestedInteger(int value);
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // Set this NestedInteger to hold a single integer.
     *     public void setInteger(int value);
     *
     *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
     *     public void add(NestedInteger ni);
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return empty list if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public void setInteger(int value);
        public void add(NestedInteger ni);
        public List<NestedInteger> getList();
    }

    /*
        time: O(N) N is the total number of integers in the list
        space: O(N) at most O(D) recursive calls are placed on the stack, where D is the maximum level of nesting in the input. in hte worst case, D = N.
     */

    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    private int depthSum(List<NestedInteger> nestedList, int level) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += (level * ni.getInteger());
            } else {
                sum += depthSum(ni.getList(), level + 1);
            }
        }

        return sum;
    }


    /*
        version 2:

        The weight of an integer is maxDepth - (the depth of the integer) + 1.
        Return the sum of each integer in nestedList multiplied by its weight.

     */

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth = getMaxDepth(nestedList);
        return depthSumInverse(nestedList, 1, maxDepth);
    }

    private int getMaxDepth(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        int depth = 1;
        for (NestedInteger ni : nestedList) {
            if (!ni.isInteger()) {
                depth = Math.max(depth, getMaxDepth(ni.getList()) +1);
            }
        }

        return depth;
    }

    private int depthSumInverse(List<NestedInteger> nestedList, int depth, int maxDepth) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if(ni.isInteger()) {
                sum += ((maxDepth - depth + 1) * ni.getInteger());
            } else {
                sum += depthSumInverse(ni.getList(), depth + 1, maxDepth);
            }
        }

        return sum;
    }

}
