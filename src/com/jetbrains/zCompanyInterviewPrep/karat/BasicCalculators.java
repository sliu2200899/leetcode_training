package com.jetbrains.zCompanyInterviewPrep.karat;

public class BasicCalculators {
    /*
        basic calculator
        给输入为string，例如"2+3-999"，之包含+-操作，返回计算结果。
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

    /*
        basic calculator 2
        加上parenthesis， 例如"2+((8+2)+(3-999))"，返回计算结果。
     */

    public static void main(String[] args) {
        String s = "2+3-9";

        System.out.println(basicCalculator(s));
    }
}
