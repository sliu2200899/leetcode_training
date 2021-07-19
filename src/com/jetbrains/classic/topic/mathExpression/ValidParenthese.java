package com.jetbrains.classic.topic.mathExpression;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParenthese {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if ("({[".indexOf(ch) != -1) {
                stack.push(ch);
            } else {
                if (stack.isEmpty() ||
                        (ch == ')' && stack.peek() != '(') ||
                        (ch == '}' && stack.peek() != '{') ||
                        (ch == ']' && stack.peek() != '[')) {

                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
