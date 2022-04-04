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

        /* Added */
        PlayerFactory.createPlayer("Ryan", PieceType.X); // Will need to use player input here for values to pass in.
        PlayerFactory.createPlayer("Tim", PieceType.O); // Will need to use player input here for values to pass in.

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

        board = new BoardVersion3(gameFrame);
        board.initNewBoard();
        added_splash_screen_and_name_prompts
        board.initControlPanel();

        /*board = new BoardV2(gameFrame);
        board.initNewBoard();*/

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