package com.jetbrains.classic.searchByAlgorithm.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sudoku {
    /*
        basic: valid Sudoku
        the key point is how to construct mapping data structure
            List<Set<Character>> rows = new ArrayList<>();
            for (int i = 0; i < 9; ++i) {
                rows.add(new HashSet<Character>());
            }
        another point that need to focus is how to convert the (i,j) to subBoxIndex:
            (i,j) => (i/3)*3 + j/3

        (i/3) * 3 + j / 3     represents ith sub box

        time: O(1)
        space: O(1)
     */
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;

        List<Set<Character>> rows = new ArrayList<>();
        List<Set<Character>> cols = new ArrayList<>();
        List<Set<Character>> subs = new ArrayList<>();

        for (int i = 0; i < 9; ++i) {
            rows.add(new HashSet<Character>());
            cols.add(new HashSet<Character>());
            subs.add(new HashSet<Character>());
        }

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int subBoxIndex = (i/3) * 3 + j / 3;
                    if (rows.get(i).contains(board[i][j]) || cols.get(j).contains(board[i][j]) || subs.get(subBoxIndex).contains(board[i][j])) {
                        return false;
                    }

                    rows.get(i).add(board[i][j]);
                    cols.get(j).add(board[i][j]);
                    subs.get(subBoxIndex).add(board[i][j]);
                }
            }
        }

        return true;
    }

    /*
        dfs: solve sudoku
     */
    /*
        analyze:
            valid Sudoku
                List<Set<Character>> rows, cols, and subs. to check if this is a valid sudoku
                (i, j) -> sub box index

            dfs to enumerate all of the sudoku and check if it is a valid one

            modify teh input matrix, need dfs backtracking to solve the problem

        algo:
            step 1:
                for i
                    for j
                        gather sudoku infor

            step 2:
                dfs()

                base case:
                    index == 81
                        return true;

                recursive part:
                    check current character is . or not..
                    try to fill the cell with 1...9 digit and explore further...


                boolean dfs()  return true/false  dfs pruning to skip rest of the exploration when we found the result.

         test:
         complexity:
            time: O(9^(mn))
            space: O(mn)
    */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] subs = new boolean[9][9];

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int subBoxIndex = (i/3) * 3 + j/3;
                    rows[i][num] = cols[j][num] = subs[subBoxIndex][num] = true;
                }
            }
        }

        dfs(0, board, rows, cols, subs);
    }

    private boolean dfs(int index, char[][] board, boolean[][] rows, boolean[][] cols, boolean[][] subs) {
        if (index == 81) return true;
        int row = index / 9;
        int col = index % 9;
        int sub = (row/3)*3 + col/3;

        if (board[row][col] != '.') {
            return dfs(index+1, board, rows, cols, subs);
        } else {
            for (char ch = '1'; ch <= '9'; ch++) {
                int num = ch - '1';
                if (!rows[row][num] && !cols[col][num] && !subs[sub][num]) {
                    board[row][col] = ch;
                    rows[row][num] = true;
                    cols[col][num] = true;
                    subs[sub][num] = true;

                    if (dfs(index+1, board, rows, cols, subs)) {
                        return true;
                    }

                    board[row][col] = '.';
                    rows[row][num] = false;
                    cols[col][num] = false;
                    subs[sub][num] = false;
                }
            }
        }

        return false;
    }




}
