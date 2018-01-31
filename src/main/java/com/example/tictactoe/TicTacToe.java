package com.example.tictactoe;

public class TicTacToe {

    private Character[][] board = {{'\0', '\0', '\0'},
                                   {'\0', '\0', '\0'},
                                   {'\0', '\0', '\0'}};

    public void play(int column, int row) {

        if (column < 1 || column > 3) {
            throw new RuntimeException("X value is outside the board!");
        } else if (row < 1 || row > 3) {
            throw new RuntimeException("Y value is outside the board!");
        }
        if (board[column - 1][row - 1] != '\0') {
            throw new RuntimeException("Field is occupied!");
        } else {
            board[column - 1][row - 1] = 'X';
        }
    }
}
