package com.jetbrains.zCompanyInterviewPrep.amazon.ood;

public class TicTacToe {
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int currentPlayer = (player == 1) ? 1 : -1;
        // update teh currentPlayer in rows and cols arrays
        rows[row] += currentPlayer;
        cols[col] += currentPlayer;

        // update diagonal
        if (row == col) {
            diagonal += currentPlayer;
        }

        // update anti diagonal
        if (col == (cols.length - 1 - row)) {
            antiDiagonal += currentPlayer;
        }

        int n = rows.length;

        // check if the current player wins
        if (Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(antiDiagonal) == n) {

            return player;
        }

        return 0;
    }

}
