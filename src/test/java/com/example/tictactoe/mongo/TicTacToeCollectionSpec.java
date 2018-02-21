package com.example.tictactoe.mongo;

import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

public class TicTacToeCollectionSpec {

    private TicTacToeCollection collection;

    @Before
    public void before() throws UnknownHostException {
        collection = new TicTacToeCollection();
    }

    @Test
    public void whenInstantiatedThenMongoDbNameIsTicTacToe() {
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() {
        assertEquals("game", collection.getMongoCollection().getName());
    }
}
