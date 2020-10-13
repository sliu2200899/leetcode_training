package com.jetbrains.hard;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

import java.math.BigInteger;
public class BasicCalculator3 {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        return solveRPN(getRPN(s));
    }

    private String getRPN(String s) {
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                sb.append(c);
                continue;
            }

            sb.append(" ");

            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.peek() != '(') {
                    sb.append(stack.pop());
                    sb.append(" ");
                }
                stack.pop();  // pop up the '('
            } else if ("+-*/".indexOf(c) != -1) {
                while (!stack.isEmpty() && stack.peek() != '('
                        && getPriority(c) <= getPriority(stack.peek()))
                {
                    sb.append(stack.pop());
                    sb.append(" ");
                }
                stack.push(c);
            }
        }

        // finally put remaining operators in the stack into the stringBuilder
        while(!stack.isEmpty()) {
            sb.append(" ");
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    private int getPriority(char c) {
        if (c == '+' || c == '-') return 0;
        if (c == '*' || c == '/') return 1;
        return -1;
    }

    private int solveRPN(String rpn) {
        String[] strs = rpn.split(" ");
        Deque<BigInteger> stack = new ArrayDeque<>();
        for (String str : strs) {
            if (str.equals("")) continue;
            if ("+-*/".indexOf(str) != -1) {
                BigInteger num2 = stack.pop();
                BigInteger num1 = stack.pop();

                if (str.equals("+")) {
                    stack.push(num1.add(num2));
                }
                else if (str.equals("-")) {
                    stack.push(num1.subtract(num2));
                }
                else if (str.equals("*")) {
                    stack.push(num1.multiply(num2));
                }
                else if (str.equals("/")) {
                    stack.push(num1.divide(num2));
                }
            } else {
                stack.push(new BigInteger(str));
            }
        }
        return stack.pop().intValue();
    }
}