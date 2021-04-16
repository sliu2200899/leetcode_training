package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    /*
        clarify:
            1. input, output, example

        algo:

            ans[i][j] = ans[i-1][j-1] + ans[i-1][j]

    */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            ans.add(new ArrayList<Integer>());

            if (i == 0) {
                ans.get(i).add(1);
                continue;
            }

            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    ans.get(i).add(1);
                } else {
                    int num1 = ans.get(i-1).get(j-1);
                    int num2 = ans.get(i-1).get(j);
                    ans.get(i).add(num1 + num2);
                }
            }
        }
        return ans;
    }
    /*
        follow up: Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
     */
        /*
        clarify:
            1. input, output, example

        algo":
            Input: rowIndex = 3
            Output: [1,3,3,1]

                    1
                1       1
            1       2       1
             ...

             ans[i][j] = ans[i-1][j-1] + ans[i-1][j]

             need to store the number in the previous row
             calculate the current row based on the previous row

    */
    public List<Integer> getRow(int r) {
        List<Integer> curr, prev = new ArrayList<Integer>(){
            {
                add(1);
            }
        };

        for (int i = 1; i <= r; ++i) {
            curr = new ArrayList(){
                {
                    add(1);
                }
            };

            for (int j = 1; j < i; ++j) {
                curr.add(prev.get(j - 1) + prev.get(j));
            }

            curr.add(1);

            prev = curr;
        }

        return prev;
    }

}
