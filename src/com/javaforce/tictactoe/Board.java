package com.javaforce.tictactoe;

import javax.swing.*;
import java.util.*;

public class Board {

    private Game game;
    private Map<Integer, Square> squares;
    private static PieceType currentPiece = PieceType.O;
    private Player gameWinner;
    private boolean gameOver = false;
    private int[] winningRow;
    private boolean draw = false;
    private Map<Integer, Player> players = new HashMap<>();
    private JTextField inputedPlayerName = new JTextField(10);
    private static Player currentPlayer;
    public static Player playerOne;// ADDED: Used for our checks now, instead of PieceType
    public static Player playerTwo; // ADDED: Used for our checks now, instead of PieceType.

    public Board(Game game) {
        this.game = game;
        squares = new HashMap<>();
    }

    public void setNextCurrentPlayer() {

        playerOne = PlayerFactory.playerOne;
        playerTwo = PlayerFactory.playerTwo;

        // Swaps players to opposite player
        if (playerOne.equals(getCurrentPlayer())) {
            setCurrentPlayer(playerTwo);
        } else if (playerTwo.equals(getCurrentPlayer())) {
            setCurrentPlayer(playerOne);
        } else {
            setCurrentPlayer(playerOne); // If no player yet (start of game) current player is set to playerOne.
        }

    }

    public boolean isDraw() {
        return false;
    }

    public boolean isWinner() {
        boolean winner = false;
        if (squares.get(1).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(4).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(7).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{1, 4, 7};
        }
        if (squares.get(2).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(5).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(8).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{2, 5, 8};
        }
        if (squares.get(3).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(6).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(9).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{3, 6, 9};
        }
        if (squares.get(1).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(2).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(3).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{1, 2, 3};
        }
        if (squares.get(4).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(5).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(6).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{4, 5, 6};
        }
        if (squares.get(7).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(8).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(9).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{7, 8, 9};
        }
        if (squares.get(1).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(5).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(9).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{1, 5, 9};
        }
        if (squares.get(3).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(5).getOwner() == getCurrentPlayer().getPieceType()
                && squares.get(7).getOwner() == getCurrentPlayer().getPieceType()) {
            winner = true;
            winningRow = new int[]{3, 5, 7};
        }
        System.out.println("winner: " + getCurrentPlayer().getPlayerName() + "  " + winner);
        return winner;
    }

    public boolean getDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public Map<Integer, Square> getSquares() {
        return squares;
    }

    public void setSquares(Map<Integer, Square> squares) {
        this.squares = squares;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int[] getWinningRow() {
        return winningRow;
    }

    public void setWinningRow(int[] winningRow) {
        this.winningRow = winningRow;
    }

    public JTextField getInputedPlayerName() {
        return inputedPlayerName;
    }

    public void setInputedPlayerName(JTextField inputedPlayerName) {
        this.inputedPlayerName = inputedPlayerName;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        Board.currentPlayer = currentPlayer;
    }
}