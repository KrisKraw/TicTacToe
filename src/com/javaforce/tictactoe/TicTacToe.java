package com.javaforce.tictactoe;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe {

    private static int BoardWidth = 600;
    private static int BoardHeight = 800;
    private static Color boardColor = Color.green;
    private static int squareSize = (BoardWidth / 3);
    private static Color squareColor = Color.white;
    private static Border border = BorderFactory.createLineBorder(Color.black);
    private static Font gameFont = new Font("Courier", Font.BOLD,30);
    public static List<String> yourMoves = new ArrayList<>();

    public static void main(String[] args) {

        yourMoves.add("mark you're square");
        yourMoves.add("go for it");
        yourMoves.add("you're up");
        yourMoves.add("think carefully");
        yourMoves.add("you're on deck");
        yourMoves.add("go for broke");

        Game game = new Game();
        game.gameStart();
    }

    public static String getRandomMove() {
        return yourMoves.get(new Random().nextInt(6));
    }

    public static int getBoardWidth() {
        return BoardWidth;
    }

    public static int getBoardHeight() { return BoardHeight; }

    public static int getSquareSize() {
        return squareSize;
    }

    public static Color getBoardColor() {
        return boardColor;
    }

    public static Color getSquareColor() {
        return squareColor;
    }

    public static Font getGameFont() { return gameFont; }
}