package com.jetbrains.zCompanyInterviewPrep.linkedIn;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);

            if ("([{".indexOf(ch) != -1) {
                stack.push(ch);
                continue;
            }

            if(stack.isEmpty()) {
                return false;
            }

            char top = stack.pop();
            if ((ch == ')' && top != '(') ||
                    (ch == ']' && top != '[') ||
                    (ch == '}' && top != '{')) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
