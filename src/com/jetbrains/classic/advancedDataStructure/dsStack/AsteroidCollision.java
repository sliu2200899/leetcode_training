package com.jetbrains.classic.advancedDataStructure.dsStack;

import java.util.Stack;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack();
        boolean blows = false;
        for (int ast: asteroids) {
            blows = false;
            while (!stack.isEmpty() && ast < 0 && 0 < stack.peek() && !blows) {
                if (stack.peek() > -ast) {
                    blows = true;
                } else if (stack.peek() == -ast) {
                    blows = true;
                    stack.pop();
                } else {
                    stack.pop();  // small ast in stack blow so need to pop out from the stack
                }
            }
            if (!blows) {
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }
}
