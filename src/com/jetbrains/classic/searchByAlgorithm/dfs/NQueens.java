package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    /*


        algo:
            available(x, y):    x is col y is row
                return !col[x]
                     and !diag1(x,y)    // diag1(x,y)   idx = x + y    from top left to bottom right
                     and !diag2(x,y)   // diag2(x,y)    idx = x - y + (n - 1)   from bottom left to top right

            n_queen(y, n, b, ans):
                if y == n:
                    ans += b
                    return

                for x in range(0, n):
                    if not available(x,y): continue
                    put(x, y, b)
                    n_queen(y+1, n, b, ans)
                    remove(x, y, b)
     */

    public List<List<String>> solveNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1];
        boolean[] diag2 = new boolean[2*n-1];

        List<List<String>> res = new ArrayList<>();

        // init
        char[][] chess = new char[n][n];
        for (char[] tmp: chess) Arrays.fill(tmp, '.');

        // dfs
        dfs(chess, res, 0, n, cols, diag1, diag2);
        return res;
    }


    // how to add result to the returning array
    // how to prunning
    private void dfs(char[][] chess, List<List<String>> res, int depth, int n,
                     boolean[] cols, boolean[] diag1, boolean[] diag2) {
        // base case
        if (depth == n) {
            addSolution(res, chess);
            return;
        }

        // recursive case
        for (int j = 0; j < n; ++j) {
            if (!cols[j] && !diag1[depth + j] && !diag2[j - depth + (n - 1)]) {

                cols[j] = true;
                diag1[depth + j] = true;
                diag2[j - depth + (n - 1)] = true;
                chess[depth][j] = 'Q';

                dfs(chess, res, depth+1, n, cols, diag1, diag2);

                chess[depth][j] = '.';
                diag2[j - depth + (n - 1)] = false;
                diag1[depth + j] = false;
                cols[j] = false;
            }
        }
    }

    private void addSolution(List<List<String>> res, char[][] chess) {
        List<String> list = new ArrayList<>();
        for (char[] row : chess) {
            list.add(String.valueOf(row));
        }

        res.add(list);
    }


    /*
        n queens 2:
        similar to the n_queen 1, and cannot use memoization to simplify the problem
        the difference is that
            1 do not need to add result to the returning array
            2 how to return the total number instead of using ugly global variable?
     */
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1];
        boolean[] diag2 = new boolean[2*n-1];

        List<List<String>> res = new ArrayList<>();

        // init
        char[][] chess = new char[n][n];
        for (char[] tmp: chess) Arrays.fill(tmp, '.');

        // dfs
        return dfs2(chess, res, 0, n, cols, diag1, diag2);
    }

    private int dfs2(char[][] chess, List<List<String>> res, int depth, int n,
                    boolean[] cols, boolean[] diag1, boolean[] diag2) {
        // base case
        if (depth == n) {
            return 1;
        }

        // recursive case
        int num = 0;
        for (int j = 0; j < n; ++j) {
            if (!cols[j] && !diag1[depth + j] && !diag2[j - depth + (n - 1)]) {

                cols[j] = true;
                diag1[depth + j] = true;
                diag2[j - depth + (n - 1)] = true;
                chess[depth][j] = 'Q';

                num += dfs2(chess, res, depth+1, n, cols, diag1, diag2);

                chess[depth][j] = '.';
                diag2[j - depth + (n - 1)] = false;
                diag1[depth + j] = false;
                cols[j] = false;
            }
        }

        return num;
    }
}
