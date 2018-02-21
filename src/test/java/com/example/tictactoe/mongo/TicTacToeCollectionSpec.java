package com.example.tictactoe.mongo;

import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class TicTacToeCollectionSpec {

    @Test
    public void whenInstantiatedThenMongoDbNameIsTicTacToe() throws UnknownHostException {
        TicTacToeCollection collection = new TicTacToeCollection();
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() throws UnknownHostException {
        TicTacToeCollection collection = new TicTacToeCollection();
        assertEquals("game", collection.getMongoCollection().getName());
    }
}
