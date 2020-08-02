package com.jetbrains.OOD.TicTacToeDesign;

/*
设计井字棋游戏。

棋盘的尺寸为3
X 总是先行动走出第一步
如果一个位置已经被占，且一名玩家打算占领该位置，一个AlreadyTakenException信息将被抛出
如果一名玩家胜利，且有玩家打算继续行动，一个GameEndException信息将被抛出
如果所有的地方都已被占领，你需要输出"it's a draw"
 */

import java.util.Arrays;

public class TicTacToe {
    private char[][] board;
    private char currentMove;
    private boolean gameEnd;

    public TicTacToe() {
        board = new char[3][3];
        currentMove = 'X';
    }

    public void initialize() {
        gameEnd = false;
        currentMove = 'X';
        for (char[] arr : board) {
            Arrays.fill(arr, '-');
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == '-' ) return false;
            }
        }
        return true;
    }

    private void changePlayer() {
        currentMove = (currentMove == 'X' ? 'O' : 'X');
    }

    private boolean checkWin(int x, int y, char currentMove) {
        int n = board.length;

        // check the x row
        for (int i = 0; i < n; ++i) {
            if (board[x][i] != currentMove) break;
            if (i == n - 1) return true;
        }
        // check y column
        for (int i = 0; i < n; ++i) {
            if (board[i][y] != currentMove) break;
            if (i == n - 1) return true;
        }

        // check diag
        if (x == y) {
            for (int i = 0; i < n; ++i) {
                if (board[i][i] != currentMove) break;
                if (i == n - 1) return true;
            }
        }

        if (x + y == n - 1) {
            for (int i = 0; i < n; ++i) {
                if (board[i][(n - 1) - i] != currentMove) break;
                if (i == n - 1) return true;
            }
        }

        return false;
    }

    // true means this move wins the game, false means otherwise
    public void move(int row, int col) throws AlreadyTakenException, GameEndException {
        if (gameEnd) {
            throw new GameEndException();
        }

        // step 1: make a move
        if (board[row][col] != '-') {
            throw new AlreadyTakenException();
        }
        board[row][col] = currentMove;

        // step 2: check win
        boolean win = checkWin(row, col, currentMove);
        if (win) {
            gameEnd = true;
            System.out.println("The current player " + currentMove + " is winner!");
        }

        // step 3: change player
        changePlayer();
    }

    private class AlreadyTakenException extends Exception {
        public AlreadyTakenException() {
            super("This place has been taken");
        }
    }

    private class GameEndException extends Exception {
        public GameEndException() {
            super("Game has been ended, cannot make any more moves");
        }
    }
}