package com.jetbrains.classic.topic.mathExpression;

import java.util.ArrayList;
import java.util.List;

public class MinimumRemoveInvalidParent {
    /*
    clarify:
        1. input, output, example
        2. is it possible to show () with nothing in the middle?
    algo:
        s = "lee(t(c)o)de)"
        lee(t(c)o)de)

        (()))
        int pArr record the
        if ( , pArr++;
        if ) , pArr--;

        ())
        if pArr<0, record the index and pArr++
        in the end,
            pArr == 0,
            pArr > 0, traverse string from end to front, and remove pArr left parant...

    time: O(n)
    space: O(1)

*/
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) return "";

        int n = s.length();

        int pArr = 0;
        List<Integer> ivdRP = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == '(') {
                pArr++;
            } else if (ch == ')') {
                pArr--;
                if (pArr < 0) {
                    ivdRP.add(i);
                    pArr++;
                }
            }
        }

        if (ivdRP.size() == 0 && pArr == 0) {
            return s;
        }

        char[] arr = s.toCharArray();
        for (int i : ivdRP) {
            arr[i] = '#';
        }


        for (int i = n - 1; pArr > 0 && i >= 0; --i) {
            if (arr[i] == '(') {
                arr[i] = '#';
                pArr--;
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != '#') {
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }
}
