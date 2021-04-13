package com.jetbrains.classic.topic.stringMatch.conversion;

import java.util.ArrayList;
import java.util.List;

public class MultiplyStrings {
    /*
        more elegant approach to solve the problem
        Start from right to left, perform multiplication on every pair of digits, and add them together. Let's draw the process! From the following draft, we can immediately conclude:

        `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`

        time: O(mn)
        space:O(m+n)
     */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                int val1 = num1.charAt(i) - '0', val2 = num2.charAt(j) - '0';

                int index = i + j;
                int num = val1 * val2 + pos[index+1];

                pos[index+1] = num % 10;
                pos[index] += num/10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : pos) {
            if (!(sb.length() == 0 && i == 0)) sb.append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /*
        my solution
            multiply single digit
            add those result
     */
        /*
        clarify:
            1. input, output, example
            2.
        algo:
            num1 = "123", num2 = "456"
            1       2       3
            4       5       6


            iterate over the num2 from back to front
                list.add(multiplySingleDigit(num1, digit))

            iterate over the numbers in the list and add them to get the result

            refer to: https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation

    */
    public String multiply2(String num1, String num2) {
        if ((num1.length() == 1 && num1.charAt(0) == '0') ||
                (num2.length() == 1 && num2.charAt(0) == '0')) {
            return "0";
        }
        List<String> list = new ArrayList<>();
        String tailZero = "";
        for (int i = num2.length() - 1; i >= 0; --i) {
            list.add(multiplySingleDigit(num1, num2.charAt(i) + "") + tailZero);
            tailZero = tailZero + "0";
        }

        return addSum(list);
    }

    private String multiplySingleDigit(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1; i >= 0; --i) {
            int num = (num1.charAt(i) - '0') * (num2.charAt(0) - '0') + carry;
            carry = num / 10;
            num = num % 10;
            sb.insert(0, num);
        }

        if (carry != 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }

    private String addSum(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, len = list.get(list.size() - 1).length();
        for (int i = 0; i < len; ++i) {
            int num = carry;
            for (int j = 0; j < list.size(); ++j) {
                int lenNum = list.get(j).length();
                num += (lenNum > i ? (list.get(j).charAt(lenNum - 1 - i) - '0') : 0);
            }
            carry = (num / 10);
            num = num % 10;

            sb.insert(0, num);
        }

        if (carry != 0) {
            sb.insert(0, carry);
        }

        return sb.toString();
    }
}
