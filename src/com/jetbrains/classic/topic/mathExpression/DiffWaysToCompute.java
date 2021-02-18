package com.jetbrains.classic.topic.mathExpression;

import java.util.ArrayList;
import java.util.List;

public class DiffWaysToCompute {
    public List<Integer> diffWaysToCompute(String s) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 ) return list;

        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isDigit(s.charAt(i))) {
                List<Integer> left = diffWaysToCompute(s.substring(0, i));
                List<Integer> right = diffWaysToCompute(s.substring(i+1));

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

        if (list.size() == 0) {
            list.add(Integer.parseInt(s));
        }

        return list;
    }
}
