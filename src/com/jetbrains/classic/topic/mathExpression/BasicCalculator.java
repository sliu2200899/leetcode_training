package com.jetbrains.classic.topic.mathExpression;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    /*
    Iterate the expression string in reverse order one character at a time. Since we are reading the expression character by character, we need to be careful when we are reading digits and non-digits.

    The operands could be formed by multiple characters. A string "123" would mean a numeric 123, which could be formed as: 123 >> 120 + 3 >> 100 + 20 + 3. Thus, if the character read is a digit we need to form the operand by multiplying a power of 10 to the current digit and adding it to the overall operand. We do this since we are processing the string in the reverse order.

    The operands could be formed by multiple characters. We need to keep track of an on-going operand. This part is a bit tricky since in this case the string is reversed. Once we encounter a character which is not a digit, we push the operand onto the stack.

    When we encounter an opening parenthesis (, this means an expression just ended. Recall we have reversed the expression. So an opening bracket would signify the end of the an expression. This calls for evaluation of the expression by popping operands and operators off the stack till we pop corresponding closing parenthesis. The final result of the expression is pushed back onto the stack.

    Note: We are evaluating all the sub-expressions within the main expression. The sub-expressions on the right get evaluated first but the main expression itself is evaluated from left to right when all its components are resolved, which is very important for correct results.

    For eg. For expression A - (B+C) + (D+E-F)A−(B+C)+(D+E−F), D+E-FD+E−F is evaluated before B+CB+C. While evaluating D+E-FD+E−F the order is from left to right. Similarly for the parent expression, all the child components are evaluated and stored on the stack so that final evaluation is left to right.

    Push the other non-digits onto to the stack.

    Do this until we get the final result. It's possible that we don't have any more characters left to process but the stack is still non-empty. This would happen when the main expression is not enclosed by parenthesis. So, once we are done evaluating the entire expression, we check if the stack is non-empty. If it is, we treat the elements in it as one final expression and evaluate it the same way we would if we had encountered an opening bracket.

    We can also cover the original expression with a set of parenthesis to avoid this extra call.
     */

    // Stack and String Reversal

    /*
        "2 - 1 + 2 "
        "(1+(4+5+2)-3)+(6+8)"
     */
    public int calculate(String s) {
        int operand = 0;
        int n = 0;
        Deque<Object> stack = new ArrayDeque<>();

        for (int i = s.length() - 1; i >= 0; --i) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {

                // Forming the operand - in reverse order
                operand = (int) Math.pow(10, n) * (int)(ch - '0') + operand;
                n += 1;

            } else if (ch != ' ') {
                if (n != 0) {
                    // save the operand on the stack
                    // as we encounter some non-digit
                    stack.push(operand);
                    n = 0;
                    operand = 0;
                }
                if (ch == '(') {
                    int res = evaluateExpr(stack);
                    stack.pop();

                    // Append the evaluated result to the stack. This result could be of a sub-expression within the parenthesis.
                    stack.push(res);
                } else {
                    stack.push(ch);
                }
            }
        }

        if (n != 0) {
            stack.push(operand);
        }

        return evaluateExpr(stack);
    }

    private int evaluateExpr(Deque<Object> stack) {
        int res = 0;
        if (!stack.isEmpty()) {
            res = (int)stack.pop();
        }
        // evaluate the expression till we get corresponding ')'
        while (!stack.isEmpty() && !((char)stack.peek() == ')')) {
            char sign = (char)stack.pop();

            if (sign == '+') {
                res += (int)stack.pop();
            } else {
                res -= (int)stack.pop();
            }
        }
        return res;
    }


    /*

    1. Iterate the expression string one character at a time. Since we are reading the expression character by character, we need to be careful when we are reading digits and non-digits.

    2. The operands could be formed by multiple characters. A string "123" would mean a numeric 123, which could be formed as: 123 >> 120 + 3 >> 100 + 20 + 3.
    Thus, if the character read is a digit we need to form the operand by multiplying 10 to the previously formed continuing operand and adding the digit to it.

    3. Whenever we encounter an operator such as + or - we first evaluate the expression to the left and then save this sign for the next evaluation.

    4. If the character is an opening parenthesis (, we just push the result calculated so far and the sign on to the stack (the sign and the magnitude) and start a fresh as if we are calculating a new expression.

    5. If the character is a closing parenthesis ), we first calculate the expression to the left. The result from this would be the result of the expression within the set of parenthesis that just concluded.
    This result is then multiplied with the sign, if there is any on top of the stack. Remember we saved the sign on top of the stack when we had encountered an open parenthesis?
    This sign is associated with the parenthesis that started then, thus when the expression ends or concludes, we pop the sign and multiply it with result of the expression.
    It is then just added to the next element on top of the stack.
     */
    public int calculate2(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int operand = 0;
        int result = 0; // for the on-going result
        int sign = 1; //. 1 means positive, -1 means negative

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {

                operand = operand * 10 + (int)(ch - '0');
            }
            else if (ch == '+' || ch == '-') {
                // evaluate the expression to the left, with result, sign, operand
                result += sign * operand;

                // save teh "+" sign
                sign = ch == '+' ? 1 : -1;

                // reset operand
                operand = 0;
            }
            else if (ch == '(') {

                // Push teh result and sign on to the stack
                // we push the result first, then sign
                stack.push(result);
                stack.push(sign);

                // reset operand and result, as if new evaluation begins for the new sub-expression
                sign = 1;
                result = 0;
            }
            else if (ch == ')') {
                // evaluate the expression to teh left with result, sign and operand
                result += sign * operand;

                // ')' marks end of expression within a set of parenthesis
                // its result is multiplied with sign on top of stack as stack.pop() is the sign before the parenthessis
                result *= stack.pop();

                // then add to teh next operand on the top
                // as stack.pop() is the result calculated before this parenthesis
                result += stack.pop();

                operand = 0;
            }
        }

        return result + (sign * operand);
    }

    /*

     */

}
