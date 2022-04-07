package com.javaforce.tictactoe;

import javax.swing.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardVersion3Test {
    @Test
    public void testEmptyBoard_ShouldTest_Board_WhenEmpty() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        assertEquals(false, board.isWinner());
    }

    @Test
    public void testVerticalWinner_ShouldTestVertically_WhenInputMatches() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        board.setSquare(1);
        board.setSquare(2);
        board.setSquare(3);

        assertEquals(true, board.isWinner());
    }
    
    @Test
    public void testHorizontalWinner_ShouldTestHorizontally_WhenInputMatches() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        board.setSquare(1);
        board.setSquare(4);
        board.setSquare(7);

        assertEquals(true, board.isWinner());
    }
    
    @Test
    public void testDiagonalWinner_ShouldTestDiagonally_WhenInputMatches() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        board.setSquare(1);
        board.setSquare(5);
        board.setSquare(9);

        assertEquals(true, board.isWinner());
    }
    
    @Test
    public void testNoWinner_ShouldTest_WhenInput_DoesNotMatch() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        board.setSquare(1);
        board.setSquare(2);
        board.setSquare(4);
        board.setSquare(5);

        assertEquals(false, board.isWinner());
    }
}
