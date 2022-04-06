package com.javaforce.tictactoe;

import javax.swing.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardVersion3Test {
    @Test
    public void testEmptyBoard() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        assertEquals(false, board.isWinner());
    }

    @Test
    public void testVerticalWinner() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        board.setSquare(1);
        board.setSquare(2);
        board.setSquare(3);

        assertEquals(true, board.isWinner());
    }
    
    @Test
    public void testHorizontalWinner() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        board.setSquare(1);
        board.setSquare(4);
        board.setSquare(7);

        assertEquals(true, board.isWinner());
    }
    
    @Test
    public void testDiagonalWinner() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        board.setSquare(1);
        board.setSquare(5);
        board.setSquare(9);

        assertEquals(true, board.isWinner());
    }
    
    @Test
    public void testNoWinner() {
        BoardVersion3 board = new BoardVersion3(new JFrame());
        board.initNewBoard();

        board.setSquare(1);
        board.setSquare(2);
        board.setSquare(4);
        board.setSquare(5);

        assertEquals(false, board.isWinner());
    }
}
