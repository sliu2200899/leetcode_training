package com.jetbrains.classic.advancedDataStructure.dsStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleHistogram {

    /*
        brute force approach
        this is a better brute force approach
        We can do one slight modification in the previous approach to optimize it to some extent.
        Instead of taking every possible pair and then finding the bar of minimum height lying between them everytime,
        we can find the bar of minimum height for current pair by using the minimum height bar of the previous pair.

        time: O(n^2)
        space: O(1)
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int length = heights.length;

        for (int i = 0; i < length; ++i) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < length; ++j) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, (j - i + 1) * minHeight);
            }
        }
        return maxArea;
    }

    /*
        Divide and Conquer

        This approach relies on the observation that the rectangle with maximum area will be the maximum of:

        The widest possible rectangle with height equal to the height of the shortest bar.
        The largest rectangle confined to the left of the shortest bar(subproblem).
        The largest rectangle confined to the right of the shortest bar(subproblem).
     */
    public int largestRectangleArea2(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }

    private int calculateArea(int[] heights, int start, int end) {
        if (start > end) {
            return 0;
        }

        int minIndex = start;
        for (int i = start; i <= end; ++i) {
            if (heights[minIndex] > heights[i]) {
                minIndex = i;
            }
        }

        return Math.max((end - start + 1) * heights[minIndex],
                Math.max(calculateArea(heights, start, minIndex - 1),
                        calculateArea(heights, minIndex + 1, end)));
    }

    /*
        stack:
        monotonic stack with ascending order

        algo:
            [2,     1,      5,      6,      2,      3]
                            i
     */
    public int largestRectangleArea3(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();   // monotonic stack with ascending order
        stack.push(-1);
        int length = heights.length;
        int maxArea = 0;
        for (int i = 0; i < length; ++i) {
            while ((stack.peek() != -1) &&
                    (heights[stack.peek()] >= heights[i])) {
                int currentHeight = heights[stack.pop()];
                int currentWidth = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int currentHeight = heights[stack.pop()];
            int currentWidth = length - stack.peek() - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }

        return maxArea;
    }
}
