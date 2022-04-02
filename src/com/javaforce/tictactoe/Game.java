package com.javaforce.tictactoe;

public class Game {

    private Board board;

    public Game() {
        board = new Board();
        board.initNewBoard();
    }
}