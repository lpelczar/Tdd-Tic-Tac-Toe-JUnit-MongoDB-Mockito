package com.example.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicTacToeCollectionSpec {

    @Test
    public void whenInstantiatedThenMongoDbNameIsTicTacToe() {
        TicTacToeCollection collection = new TicTacToeCollection();
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }
}
