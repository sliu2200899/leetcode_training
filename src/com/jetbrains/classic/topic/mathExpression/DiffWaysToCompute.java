package com.jetbrains.classic.topic.mathExpression;

import java.util.ArrayList;
import java.util.List;

public class DiffWaysToCompute {
    /*
        the point of the problem is how to split the string expression into left and right parts.
        we can only split the expression on the operators
     */
    public List<Integer> diffWaysToCompute(String s) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 ) return list;

        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                List<Integer> left = diffWaysToCompute(s.substring(0, i));
                List<Integer> right = diffWaysToCompute(s.substring(i+1));

                // perform the cartesian product
                for (int l : left) {
                    for (int r : right) {
                        int res = 0;
                        switch(s.charAt(i)) {
                            case '+':
                                res = l + r;
                                break;
                            case '-':
                                res = l - r;
                                break;
                            case '*':
                                res = l * r;
                        }
                        list.add(res);
                    }
                }

            }
        }

        // edge case: if there doesn't exist operators at all, then string itself is an number, just add it to the list
        if (list.size() == 0) {
            list.add(Integer.parseInt(s));
        }

        return list;
    }
}
