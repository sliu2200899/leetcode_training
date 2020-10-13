package com.jetbrains.master;

import java.util.*;
import java.util.function.BiFunction;

public class EvalRPN {
    /*
    As we move towards understanding what Reverse Polish Notation is, keep in mind that while it seems a bit strange and un-intuitive (at first!), that Infix Notation is actually more confusing.
    The only reason Infix Notation seems intuitive is because you've probably been using it all your life and so it is now second nature to you.
    People who use Reverse Polish Notation on a daily basis find it very intuitive! Some hand-held calculators still use it!
     */

    /*
        algo:
        The first approach worked, but O(n^2) is too slow for large nn. As hinted at above, a Double-Linked List could be an option. However, it requires a lot of set-up code,
        and in practice requires more space than the elegant Stack approach we're going to look at now.

        We don't want to repeatedly delete items from the middle of a list, as this inevitably leads to O(n^2) time performance. So recall that the above algorithm scanned through the list from left to right,
        and each time it reached an operator, it'd replace the operator and the 2 numbers immediately before it with the result of applying the operator to the 2 numbers.

        The two key steps of the above algorithm were:
        1. Visit each operator, in linear order. Finding these can be done with a linear search of the original list.
        2. Get the 2 most recently seen numbers that haven't yet been replaced. These could be tracked using a Stack.

     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;

        Deque<Integer> stack = new ArrayDeque<>();
        for (String t : tokens) {
            if ("+-*/".indexOf(t) != -1) {
                int num2 = stack.pop();
                int num1 = stack.pop();

                if (t.equals("+")) {
                    stack.push(num1 + num2);
                } else if (t.equals("-")) {
                    stack.push(num1 - num2);
                } else if (t.equals("*")) {
                    stack.push(num1 * num2);
                } else if (t.equals("/")) {
                    stack.push(num1 / num2);
                }
            } else {
                stack.push(Integer.parseInt(t));
            }
        }

        return stack.pop();
    }


    // using java 8 Map BiFunction and Lambda function

    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>();

    // Ensure this only gets done once for ALL test cases.
    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN2(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            if (!OPERATIONS.containsKey(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int number2 = stack.pop();
            int number1 = stack.pop();
            BiFunction<Integer, Integer, Integer> operation;
            operation = OPERATIONS.get(token);
            int result = operation.apply(number1, number2);
            stack.push(result);
        }

        return stack.pop();

    }
}
