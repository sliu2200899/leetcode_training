package com.jetbrains.master;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        /*
            analyze:
                S = "a1b2"
                ["a1b2","a1B2","A1b2","A1B2"]

                "a1b2"
                    i
                    i == S.length()
                        ...
                    a
                       digits: move
                       letter:
                            lowercase
                            uppercase

                 a1b2
                 A1b2

                 a1B2
                 A1B2

                 recursion DFS backtracking problem to solve the problem

             algo:

                 dfs(s, res, index, temp)
                    // base case
                    if (index == s.length())
                        add list to res

                    //
                    if (curr is digit)
                        dfs(index+1)
                    else
                        dfs(index+1, list)
                        dfs(index+1, list)


        */

        List<String> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;

        dfs(S, res, 0, "");

        return res;
    }

    private void dfs(String s, List<String> res, int index, String temp) {
        if (index == s.length()) {
            res.add(temp);
            return;
        }

        if (Character.isDigit(s.charAt(index))) {
            dfs(s, res, index+1, temp + s.charAt(index));
        } else {

            dfs(s, res, index+1, temp + s.substring(index, index+1).toLowerCase());
            dfs(s, res, index+1, temp + s.substring(index, index+1).toUpperCase());
        }
    }
}
