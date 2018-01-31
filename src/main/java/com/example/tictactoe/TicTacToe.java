package com.example.tictactoe;

public class TicTacToe {

    private static final int SIZE = 3;
    private Character[][] board = {{'\0', '\0', '\0'},
                                   {'\0', '\0', '\0'},
                                   {'\0', '\0', '\0'}};
    private char lastPlayer = '\0';

    public String play(int column, int row) {
        checkAxis(column, "X value is outside the board!");
        checkAxis(row, "Y value is outside the board!");
        lastPlayer = nextPlayer();
        setField(column, row, lastPlayer);
        if (isWinner()) {
            return lastPlayer + " is the Winner";
        }
        return "No winner";
    }

    private boolean isWinner() {
        int playerTotal = lastPlayer * SIZE;
        for (int i = 0; i < SIZE; i++)
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal) {
                return true;
            } else if (board[i][0] + board[i][1] + board[i][2] == playerTotal) {
                return true;
            }
        return false;
    }

    private void setField(int column, int row, char lastPlayer) {
        if (board[column - 1][row - 1] != '\0') {
            throw new RuntimeException("Field is occupied!");
        } else {
            board[column - 1][row - 1] = lastPlayer;
        }
    }

    private void checkAxis(int axis, String message) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException(message);
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }
}
