package com.javaforce.tictactoe;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TicTacToe {

    private static int BoardWidth = 600;
    private static int BoardHeight = 800;
    private static Color boardColor = Color.black;
    private static int squareSize = (BoardWidth / 3);
    private static Color squareColor = Color.white;
    private static Border border = BorderFactory.createLineBorder(Color.black);

    public static void main(String[] args) {
        Game game = new Game();
    }

    public static int getBoardWidth() {
        return BoardWidth;
    }

    public static void setBoardWidth(int boardWidth) {
        TicTacToe.BoardWidth = boardWidth;
    }

    public static int getBoardHeight() { return BoardHeight; }

    public static void setBoardHeight(int boardHeight) { BoardHeight = boardHeight; }

    public static Border getBorder() {
        return border;
    }

    public static void setBorder(Border border) {
        TicTacToe.border = border;
    }

    public static int getSquareSize() {
        return squareSize;
    }

    public static void setSquareSize(int squareSize) {
        TicTacToe.squareSize = squareSize;
    }

    public static Color getBoardColor() {
        return boardColor;
    }

    public static void setBoardColor(Color boardColor) {
        TicTacToe.boardColor = boardColor;
    }

    public static Color getSquareColor() {
        return squareColor;
    }

    public static void setSquareColor(Color squareColor) {
        TicTacToe.squareColor = squareColor;
    }
}