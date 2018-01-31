package com.example.tictactoe;

public class TicTacToe {

    public void play(int column, int row) {

        if (column < 1 || column > 3) {
            throw new RuntimeException("X value is outside the board!");
        }
    }
}
