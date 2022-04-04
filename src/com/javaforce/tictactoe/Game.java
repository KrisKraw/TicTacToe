package com.javaforce.tictactoe;

import javax.swing.*;
import java.awt.*;

public class Game {

    private JFrame gameFrame;
    private Board board;
    private SplashScreen SplashScreen;

    public Game() {
        initGame();
    }

    public void initGame() {
        gameFrame = new JFrame("TicTacToe by JavaForce");
        gameFrame.setSize(TicTacToe.getBoardWidth(),TicTacToe.getBoardHeight());
        gameFrame.setLayout(new FlowLayout());
        gameFrame.getContentPane().setBackground(Color.WHITE);
        gameFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);

        SplashScreen splashScreen = new SplashScreen(gameFrame);
        splashScreen.showLogo();
        try {
            Thread.sleep(3000);
        } catch(Exception e) { e.printStackTrace(); } ;
        splashScreen.hideLogo();

        board = new Board(gameFrame);
        board.initNewBoard();
        board.initControlPanel();

        board.boardScrambler.start();

        board.showPlayButton();

        //boardScrambler.showBoardScrambler = false;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}