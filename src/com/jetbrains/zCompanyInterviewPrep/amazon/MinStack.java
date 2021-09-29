package com.jetbrains.zCompanyInterviewPrep.amazon;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    /*
     stack with int array

     Recall that with a Stack, we only ever add (push) and remove (pop) numbers from the top. Therefore,
     an important invariant of a Stack is that when a new number, which we'll call x, is placed on a Stack,
     the numbers below it will not change for as long as number x remains on the Stack. Numbers could come
     and go above x for the duration of x's presence, but never below.
 */
    private Deque<int[]> stack = new ArrayDeque<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new int[]{val, val});
        } else {
            int[] top = stack.peek();
            stack.push(new int[]{val, Math.min(top[1], val)});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        int[] top = stack.peek();
        return top[0];
    }

    public int getMin() {
        int[] top = stack.peek();
        return top[1];
    }

    /*
        two stack approaches


        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> minStack = new ArrayDeque<>();


        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(val);
                minStack.push(val);
            } else {
                int num = minStack.peek();
                if (val <= num) {
                    minStack.push(val);
                }
                stack.push(val);
            }
        }

        public void pop() {
            int num = stack.pop();
            if (num == minStack.peek()) {
                minStack.pop();
            }
        }
     */

}
