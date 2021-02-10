package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        // offset:
        /*
            analyze:
                '('
                    offset++
                ')'
                    if offset == 0
                        return;
                    else
                        add ')' to result
                        offset--

            algo:
                dfs(s, left, right, offset, res)
                   if left == 0 && right == 0
                        add s to res

                   if (left < 0 || right < 0)
                        return

                   if (left > 0) {
                        dfs(s + '(', left-1, right, offset+1, res)
                   }

                   if (right > 0) {
                        if (offset == 0) return;
                        dfs(s + ')', left, right-1, offset+1, res)
                   }

            test:
                     n = 1
                     left = 1
                     right = 1

                     res = []

                     s = '('
                     []

                  (

               ((   ()

        */

        List<String> res = new ArrayList<>();

        dfs("", n, n, 0, res);

        return res;
    }

    private void dfs(String s, int left, int right, int offset, List<String> res) {
        if (left == 0 && right == 0){
            res.add(s);
            return;
        }

        if (left < 0 || right < 0) {
            return;
        }

        if (left > 0) {
            dfs(s + '(', left-1, right, offset+1, res);
        }

        if (right > 0) {
            if (offset == 0) {
                return;
            }

            dfs(s + ')', left, right-1, offset-1, res);
        }
    }
}
