package com.javaforce.tictactoe;

import javax.swing.*;
import java.util.*;

public class Board {

    private Game game;
    private Map<Integer,Square> squares;
    private static PieceType currentPiece = PieceType.X;
    private PieceType gameWinner;
    private boolean gameOver = false;
    private int[] winningRow;
    private boolean draw = false;
    private Map<Integer,Player> players = new HashMap<>();
    private JTextField inputedPlayerName = new JTextField(10);
    private Player currentPlayer;

    public Board(Game game) {
        this.game = game;
        squares = new HashMap<>();
    }

    public void setNextCurrentPlayer() {
        if(null == currentPlayer || currentPlayer.getPlayerId() == 1) {
            currentPlayer = PlayerFactory.getPlayerMap().get(1);
        } else {
            currentPlayer = PlayerFactory.getPlayerMap().get(0);
        }
    }
    public boolean isDraw() {
        return false;
    }

    public boolean isWinner() {
        boolean winner = false;
        if(squares.get(5).getOwner() == getCurrentPiece()) {
            if(squares.get(1).getOwner() == getCurrentPiece() && squares.get(9).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{1,5,9};
            } else if(squares.get(3).getOwner() == getCurrentPiece() && squares.get(7).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{3,5,7};
            } else if(squares.get(2).getOwner() == getCurrentPiece() && squares.get(8).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{2,5,8};
            } else if(squares.get(4).getOwner() == getCurrentPiece() && squares.get(6).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{4,5,6};
            }
        } else if(squares.get(1).getOwner() == getCurrentPiece()) {
            if(squares.get(2).getOwner() == getCurrentPiece() && squares.get(3).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{1,2,3};
            } else if(squares.get(4).getOwner() == getCurrentPiece() && squares.get(7).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{1,4,7};
            }
        } else if(squares.get(9).getOwner() == getCurrentPiece()) {
            if(squares.get(3).getOwner() == getCurrentPiece() && squares.get(6).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{3,6,9};
            } else if(squares.get(7).getOwner() == getCurrentPiece() && squares.get(8).getOwner() == getCurrentPiece()) {
                winner = true;
                winningRow = new int[]{7,8,9};
            }
        }
        System.out.println("winner: " + getCurrentPiece() + "  " + winner);
        gameWinner = getCurrentPiece();
        return winner;
    }

    public static PieceType getCurrentPiece() {
        return currentPiece;
    }

    public static void setCurrentPiece(PieceType currentPiece) {
        Board.currentPiece = currentPiece;
    }

    public boolean getDraw() { return draw; }

    public void setDraw(boolean draw) { this.draw = draw; }

    public Map<Integer, Square> getSquares() {
        return squares;
    }

    public void setSquares(Map<Integer, Square> squares) {
        this.squares = squares;
    }

    public PieceType getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(PieceType gameWinner) {
        this.gameWinner = gameWinner;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}