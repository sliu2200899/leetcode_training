package com.jetbrains.classic.ideologyAlgo.dynamicProgramming;

import java.util.List;

public class Triangle {
    /*
        This problem is quite well-formed in my opinion. The triangle has a tree-like structure, which would lead people
         to think about traversal algorithms such as DFS. However, if you look closely, you would notice that the adjacent
         nodes always share a 'branch'. In other word, there are overlapping subproblems. Also, suppose x and y are 'children' of k.
         Once minimum paths from x and y to the bottom are known, the minimum path starting from k can be decided in O(1),
         that is optimal substructure. Therefore, dynamic programming would be the best solution to this problem in terms of time complexity.

         'Bottom-up' DP, on the other hand, is very straightforward: we start from the nodes on the bottom row; the min pathsums
         for these nodes are the values of the nodes themselves. From there, the min pathsum at the ith node on the kth row would
         be the lesser of the pathsums of its two children plus the value of itself, i.e.:
         minpath[k][i] = min( minpath[k+1][i], minpath[k+1][i+1]) + triangle[k][i];

         Or even better, since the row minpath[k+1] would be useless after minpath[k] is computed, we can simply set minpath as a 1D array, and iteratively update itself:
         minpath[i] = min( minpath[i], minpath[i+1]) + triangle[k][i];

         clarify:  can we modify the input triangle?  if yes, we can use the following algorithm.

         time: O(n),  n is the number of hte numbers ion the triangle
         space: O(n),

     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0)
            return 0;

        List<Integer> nextRow = null;
        for (int i=triangle.size() - 2; i>=0; i--) {   // we just start from the second last row
            for (int j=0; j<=i; j++) {
                nextRow = triangle.get(i+1);     // get the next row..
                int sum = Math.min(nextRow.get(j), nextRow.get(j+1)) + triangle.get(i).get(j);
                triangle.get(i).set(j, sum);    // set the min path sum
            }
        }
        return triangle.get(0).get(0);
    }

    /*
        most of the time, you cannot modify the triangle,
        so, in this case, you need to have an extra array   2D
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        if(triangle.size() == 0)
            return 0;

        int rowNum = triangle.size();
        int colNum = triangle.get(triangle.size() - 1).size();

        int[][] dp = new int[rowNum][colNum];

        // bottom - up DP approach
        // fill up the last row ...
        int i = 0;
        for (Integer n : triangle.get(rowNum - 1)) {
            dp[rowNum - 1][i++] = n;
        }

        for (int row = rowNum - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[row][col] = Math.min(dp[row+1][col], dp[row+1][col+1]) + triangle.get(row).get(col);
            }
        }

        return dp[0][0];
    }

    /*
        we can improve further on the space
        Or even better, since the row minpath[k+1] would be useless after minpath[k] is computed,
        we can simply set minpath as a 1D array, and iteratively update itself:

     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        if(triangle.size() == 0)
            return 0;

        int rowNum = triangle.size();
        int[] dp = new int[rowNum];

        for (int i = 0; i < triangle.get(rowNum - 1).size(); ++i) {
            dp[i] = triangle.get(rowNum - 1).get(i);
        }

        for (int row = rowNum - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = Math.min(dp[col], dp[col+1]) + triangle.get(row).get(col);
            }
        }

        return dp[0];
    }
}
