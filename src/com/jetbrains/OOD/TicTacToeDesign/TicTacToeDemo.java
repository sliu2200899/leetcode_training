package com.jetbrains.OOD.TicTacToeDesign;

public class TicTacToeDemo {
    public static void main(String[] args) {
        TicTacToe ttt = new TicTacToe();
        ttt.initialize();
        try {
//            ttt.move(0, 0); // X turn
//            ttt.move(1, 0); // O trun
//            ttt.move(1, 1); // X turn
//            ttt.move(2, 0); // O turn
//            ttt.move(2, 2); // X turn and win
//            ttt.move(0, 0);  //throw GameEndException
            ttt.move(0, 0); // X turn
//            ttt.move(0, 0); // throw AlreadyTakenException
            ttt.move(1, 0); // O turn
            ttt.move(1, 1); // X turn
            ttt.move(2, 0); // o turn
            ttt.move(2, 2); // X turn and win
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
