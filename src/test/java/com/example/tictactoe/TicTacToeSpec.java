package com.example.tictactoe;

import com.example.tictactoe.mongo.TicTacToeBean;
import com.example.tictactoe.mongo.TicTacToeCollection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TicTacToeSpec {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private TicTacToe ticTacToe;
    private TicTacToeCollection collection;

    @Before
    public final void before() {
        collection = mock(TicTacToeCollection.class);
        ticTacToe = new TicTacToe(collection);
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(5, 2);
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 6);
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(2,1);
        exception.expect(RuntimeException.class);
        ticTacToe.play(2, 1);
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThenO() {
        ticTacToe.play(1,1);
        assertEquals('O', ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1,1);
        assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1,1); // X
        ticTacToe.play(1,2); // O
        ticTacToe.play(2,1); // X
        ticTacToe.play(2,2); // O
        String actual = ticTacToe.play(3,1); // X
        assertEquals("X is the Winner", actual);
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2,1); // X
        ticTacToe.play(1,1); // O
        ticTacToe.play(3,1); // X
        ticTacToe.play(1,2); // O
        ticTacToe.play(2,2); // X
        String actual = ticTacToe.play(1,3); // O
        assertEquals("O is the Winner", actual);
    }

    @Test
    public void whenPlayAndTopBottomDiagonalLineThenWinner() {
        ticTacToe.play(1,1); // X
        ticTacToe.play(1,2); // O
        ticTacToe.play(2,2); // X
        ticTacToe.play(1,3); // O
        String actual = ticTacToe.play(3,3); // X
        assertEquals("X is the Winner", actual);
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(1,3); // X
        ticTacToe.play(1,1); // O
        ticTacToe.play(2,2); // X
        ticTacToe.play(1,2); // O
        String actual = ticTacToe.play(3,1); // X
        assertEquals("X is the Winner", actual);
    }

    @Test
    public void whenAllFieldsAreFilledThenDraw() {
        ticTacToe.play(1,1);
        ticTacToe.play(1,2);
        ticTacToe.play(1,3);
        ticTacToe.play(2,1);
        ticTacToe.play(2,3);
        ticTacToe.play(2,2);
        ticTacToe.play(3,1);
        ticTacToe.play(3,3);
        String actual = ticTacToe.play(3,2);
        assertEquals("It is a draw", actual);
    }

    @Test
    public void whenInstantiatedThenSetCollection() {
        assertNotNull(ticTacToe.getTicTacToeCollection());
    }

    @Test
    public void whenPlayThenSaveMoveIsInvoked() {
        TicTacToeBean move = new TicTacToeBean(1, 1, 3, 'X');
        ticTacToe.play(move.getX(), move.getY());
        verify(collection).saveMove(move);
    }
}
