package com.jetbrains.classic.advancedDataStructure.dsStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextGreaterElements2 {
    /*
        approach 1: monotonic stack + double array
        the top of the stack refers to the index of the next greater element found so far.

        algo:
            We start traversing the nums array from right towards the left. For an element nums[i] encountered, we pop all the elements stack[top] from the stack such that
                nums[stack[top]] <= nums[i]
            We continue the popping till we encounter a stack[top] satisfying nums[stack[top]] > nums[i]. Now, it is obvious that the current stack[top] only can act as the Next greater element for nums[i]
            if no element remains on the top of the stack, it means no larger element than nums[i] exists to its right. Along with this, ....

        time: O(n)
        space: O(n)
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] extArr = new int[nums.length*2];
        System.arraycopy(nums, 0, extArr, 0, nums.length);
        for (int i = 0; i < nums.length; ++i) {
            extArr[i + nums.length] = nums[i];
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums.length];
        for (int i = extArr.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && stack.peek() <= extArr[i]) {
                stack.pop();
            }

            if (i <= nums.length - 1) {
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(extArr[i]);
        }

        return res;
    }
}
