package com.jetbrains.classic.topic.mathExpression;

public class BasicCalculatorNaive {
    /*
        string only includes +, -, non-integers and spaces
        juse parse teh character one by one from left to right
     */
    public static int basicCalculator(String expression) {
        int op = 1, left = 0;
        for (int i = 0; i < expression.length(); ++i) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + expression.charAt(i) - '0';
                    i++;
                }
                --i;
                if (op == 1) {
                    left = left + num;
                } else {
                    left = left - num;
                }
            } else {
                if (ch == '+') {
                    op = 1;
                } else if (ch == '-'){
                    op = 0;
                }
            }
        }
        return left;
    }
}
