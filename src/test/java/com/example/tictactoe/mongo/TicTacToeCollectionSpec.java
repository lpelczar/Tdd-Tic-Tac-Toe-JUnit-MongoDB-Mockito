package com.example.tictactoe.mongo;

import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TicTacToeCollectionSpec {

    private TicTacToeCollection collection;

    @Before
    public void before() throws UnknownHostException {
        collection = spy(new TicTacToeCollection());
    }

    @Test
    public void whenInstantiatedThenMongoDbNameIsTicTacToe() {
        assertEquals("tic-tac-toe", collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() {
        assertEquals("game", collection.getMongoCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave() {
        TicTacToeBean bean = new TicTacToeBean(3, 2, 1, 'Y');
        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.saveMove(bean);
        verify(mongoCollection, times(1)).save(bean);
    }
}
