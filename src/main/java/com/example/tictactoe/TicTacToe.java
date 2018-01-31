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
        } else if (isDraw()) {
            return "It is a draw";
        } else {
            return "No winner";
        }
    }

    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinner() {
        int playerTotal = lastPlayer * SIZE;
        char diagonal1 = '\0';
        char diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal ||
                board[i][0] + board[i][1] + board[i][2] == playerTotal) {
                return true;
            }
            if (diagonal1 == playerTotal || diagonal2 == playerTotal) {
                return true;
            }
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
