package com.jetbrains.classic;

import java.util.LinkedList;

public class IsValidExpression {

    /*
    Before looking at how we can check if a given expression consisting of these parenthesis is valid or not, let us look at a simpler version of the problem that consists of just one type of parenthesis.
    So, the expressions we can encounter in this simplified version of the problem are e.g.
        (((((()))))) -- VALID


    Let's look at a simple algorithm to deal with this problem.

    1. We process the expression one bracket at a time starting from the left.

    2. Suppose we encounter an opening bracket i.e. (, it may or may not be an invalid expression because there can be a matching ending bracket somewhere in the remaining part of the expression. Here, we simply increment the counter keeping track of left parenthesis till now. left += 1

    3. If we encounter a closing bracket, this has two meanings:

        3.1 One, there was no matching opening bracket for this closing bracket and in that case we have an invalid expression. This is the case when left == 0 i.e. when there are no unmatched left brackets available.
        3.2 We had some unmatched opening bracket available to match this closing bracket. This is the case when left > 0 i.e. we have unmatched left brackets available.

    4. If we encounter a closing bracket i.e. ) when left == 0, then we have an invalid expression on our hands. Else, we decrement left thus reducing the number of unmatched left parenthesis available.

    5. Continue processing the string until all parenthesis have been processed.

    6. If in the end we still have unmatched left parenthesis available, this implies an invalid expression.


    If we try and follow the same approach for our original problem, then it simply won't work.
    The reason a simple counter based approach works above is because all the parenthesis are of the same type.
    So when we encounter a closing bracket, we simply assume a corresponding opening matching bracket to be available i.e. if left > 0.

    But, in our problem, if we encounter say ], we don't really know if there is a corresponding opening [ available or not. You could say:
    Why not maintain a separate counter for the different types of parenthesis?
    This doesn't work because the relative placement of the parenthesis also matters here. e.g.:   [{]


    notice that, What if whenever we encounter a matching pair of parenthesis in the expression, we simply remove it from the expression?
    The stack data structure can come in handy here in representing this recursive structure of the problem.
    We can't really process this from the inside out because we don't have an idea about the overall structure.
    But, the stack can help us process this recursively i.e. from outside to inwards.

    Let us have a look at the algorithm for this problem using stacks as the intermediate data structure.

    Algorithm

    1. Initialize a stack S.
    2. Process each bracket of the expression one at a time.
    3. If we encounter an opening bracket, we simply push it onto the stack. This means we will process it later, let us simply move onto the sub-expression ahead.
    4. If we encounter a closing bracket, then we check the element on top of the stack. If the element at the top of the stack is an opening bracket of the same type,
        then we pop it off the stack and continue processing. Else, this implies an invalid expression.
    5. In the end, if we are left with a stack still having elements, then this implies an invalid expression.
    6. We'll have a look a dry run for the algorithm and then move onto the implementation.

     */
    public boolean isValid(String s) {
        // sanity check
        if (s == null || s.length() == 0) return false;

        LinkedList<Character> stack = new LinkedList<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if ("([{".indexOf(c) != -1) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                char cur = stack.peek();
                if (!(cur == '(' && c == ')') &&
                        !(cur == '[' && c == ']') &&
                        !(cur == '{' && c == '}'))
                    return false;

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
