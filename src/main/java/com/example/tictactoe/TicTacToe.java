package com.example.tictactoe;

import com.example.tictactoe.mongo.TicTacToeBean;
import com.example.tictactoe.mongo.TicTacToeCollection;

import java.net.UnknownHostException;

public class TicTacToe {

    private static final int SIZE = 3;
    private Character[][] board = {{'\0', '\0', '\0'},
                                   {'\0', '\0', '\0'},
                                   {'\0', '\0', '\0'}};
    private char lastPlayer = '\0';
    private TicTacToeCollection ticTacToeCollection;

    protected TicTacToeCollection getTicTacToeCollection() {
        return ticTacToeCollection;
    }

    public TicTacToe() throws UnknownHostException {
        this(new TicTacToeCollection());
    }

    protected TicTacToe(TicTacToeCollection collection) {
        ticTacToeCollection = collection;
    }

    public String play(int column, int row) {
        checkAxis(column, "X value is outside the board!");
        checkAxis(row, "Y value is outside the board!");
        lastPlayer = nextPlayer();
        setField(new TicTacToeBean(1, column, row, lastPlayer));
        if (isWinner(column, row)) {
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

    private boolean isWinner(int column, int row) {
        int playerTotal = lastPlayer * SIZE;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical = diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][row - 1];
            vertical += board[column - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
            if (horizontal == playerTotal ||
                vertical == playerTotal ||
                diagonal1 == playerTotal ||
                diagonal2 == playerTotal) {
                return true;
            }
        }
        return false;
    }

    private void setField(TicTacToeBean bean) {
        if (board[bean.getX() - 1][bean.getY() - 1] != '\0') {
            throw new RuntimeException("Field is occupied!");
        } else {
            board[bean.getX() - 1][bean.getY() - 1] = lastPlayer;
            getTicTacToeCollection().saveMove(bean);
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
