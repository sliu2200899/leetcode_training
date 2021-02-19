package com.jetbrains.classic.topic.mathExpression;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AddOperators {
    /*
      algo:
          dfs(num, target, List<String> res, String path)
              // base case
              if (getResult() == target) {
                  put the expression into the result
                  return
              }

              if (i == nums.length() || getResult() != target) return;

              for loop the num string
                  split the num into several digits
                  for loop the +, -, *
                      dfs()

  */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, target, res, "");

        return res;
    }

    private void dfs(String s, int target, List<String> res, String path) {
        if (s.length() == 0 && getResult(path) == target) {
            res.add(path);
            return;
        }

        if (s.length() == 0) {
            return;
        }

        for (int i = 1; i <= s.length(); ++i) {
            // solve "00" problem
            if (i != 1 && s.charAt(0) == '0' && s.charAt(1) == '0') continue;

            String left = (s.charAt(0) == '0' ? s.substring(0, 1) : s.substring(0, i));

            if (i == s.length()) {
                dfs("", target, res, path + left);
            }
            else {
                String oper = "+-*";
                for (int j = 0; j < oper.length(); ++j) {

                    String newPath = left + oper.charAt(j);
                    dfs(s.substring(left.length()), target, res, path + newPath);

                }
            }
        }
    }

    // note that this problem getResult would return a long instead of int.
    // consider the case  "2147483648"  -2147483648
    private long getResult(String s) {
        if (s == null || s.length() == 0) return 0;

        Deque<Long> stack = new ArrayDeque<>();
        char operator = '+';

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            else if (Character.isDigit(c)) {
                long num = 0;
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
                    long left = stack.pop();
                    stack.push(left * num);
                }else if (operator == '/') {
                    long left = stack.pop();
                    stack.push(left / num);
                }
            }
            else {
                operator = c;
            }
        }

        long ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}