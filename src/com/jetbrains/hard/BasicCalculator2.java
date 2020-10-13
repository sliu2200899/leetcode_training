package com.jetbrains.hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator2 {
    /*
    像这种只有两个优先级的calculator，可以直接用stack来做，存储优先级较低的，遇到高优先级就处理。这道题，用stack存储 prev operator 为 + 或 - 的num，然后当遇到*或/的时候从stack去数字进行计算。
     */
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        Deque<Integer> stack = new ArrayDeque<>();
        char operator = '+';

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            else if (Character.isDigit(c)) {
                int num = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (int)(s.charAt(i) - '0');
                    i++;
                }
                i--;

                if (operator == '+') {
                    stack.push(num);
                }else if (operator == '-') {
                    stack.push(-num);
                }else if (operator == '*') {
                    int left = stack.pop();
                    stack.push(left * num);
                }else if (operator == '/') {
                    int left = stack.pop();
                    stack.push(left / num);
                }
            }
            else {
                operator = c;
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}
