package com.javaforce.tictactoe;

import javax.swing.*;
import java.awt.*;

public class Game {

    private JFrame gameFrame;
    private BoardVersion3 board;
    private SplashScreen SplashScreen;

    public Game() {
        initGame();
    }

    public void initGame() {
        gameFrame = new JFrame("TicTacToe by JavaForce");
        gameFrame.setSize(TicTacToe.getBoardWidth(),TicTacToe.getBoardHeight());
        gameFrame.setLayout(new FlowLayout());
        gameFrame.setBackground(TicTacToe.getBoardColor());
        gameFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new BoardVersion3(gameFrame);
        board.initNewBoard();

        gameFrame.setVisible(true);

        //Board.SplashScreen splashScreen = new Board.SplashScreen();
        //splashScreen.start();
    }
}