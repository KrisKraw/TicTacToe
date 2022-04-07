package com.javaforce.tictactoe;

import java.util.*;

public class Board {

    private Map<Integer, Square> squares;
    private static PieceType currentPiece = PieceType.O;
    private Player gameWinner;
    private boolean gameOver = false;
    private int[] winningRow;
    private boolean draw = false;
    private Map<Integer, Player> players = new HashMap<>();
    private static Player currentPlayer;
    public static Player playerOne;// ADDED: Used for our checks now, instead of PieceType
    public static Player playerTwo; // ADDED: Used for our checks now, instead of PieceType
    public static boolean playingAlexa = false;

    public Board(Game game) {
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
        for (Map.Entry<Integer,Square> square : getSquares().entrySet()) {
            if(square.getValue().getOwner() == PieceType.E) {
                return false;
            }
        }
        return true;
    }

    public boolean isWinner() {
        boolean winner = false;
        if(Game.diffGameVersion) {
            int count;
            // Horizontal Win Loop.
            for (int i = 0; i < 3; i++) {
                count = 0;
                for (int j = 1 + 3 * i; j <= 3 + 3 * i; j++) {
                    if (squares.get(j).getOwner() != PieceType.E) {
                        count++;
                    }
                }
                if (count == 3) {
                    winner = true;
                    winningRow = new int[]{1 + 3 * i, 2 + 3 * i, 3 + 3 * i};
                }
            }

            // Vertical Win Loop.
            for (int i = 0; i < 3; i++) {
                count = 0;
                for (int j = 1 + i; j <= 9; j += 3) {
                    if (squares.get(j).getOwner() != PieceType.E) {
                        count++;
                    }
                }
                if (count == 3) {
                    winner = true;
                    winningRow = new int[]{1 + i, 4 + i, 7 + i};
                }
            }

            // Diagonal Win Loop.
            count = 0;
            for (int j = 1; j <= 9; j += 4) {
                if (squares.get(j).getOwner() != PieceType.E) {
                    count++;
                }
            }
            if (count == 3) {
                winner = true;
                winningRow = new int[]{1, 5, 9};
            }

            count = 0;
            for (int j = 3; j <= 7; j += 2) {
                if (squares.get(j).getOwner() != PieceType.E) {
                    count++;
                }
            }
            if (count == 3) {
                winner = true;
                winningRow = new int[]{3, 5, 7};
            }
        }
        else {
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
        }
        return winner;
    }

    public boolean isWinner(boolean diffGameVersion) {
        int count;
        boolean winner = false;
        // Horizontal Win Loop.
        for (int i = 0; i < 3; i++) {
            count = 0;
            for (int j = 1 + 3 * i; j <= 3 + 3 * i; j++) {
                if (squares.get(j).getOwner() != PieceType.E) {
                    count++;
                }
            }
            if (count == 3) {
                winner = true;
                winningRow = new int[]{1 + 3 * i, 2 + 3 * i, 3 + 3 * i};
            }
        }

        // Vertical Win Loop.
        for (int i = 0; i < 3; i++) {
            count = 0;
            for (int j = 1 + i; j <= 9; j += 3) {
                if (squares.get(j).getOwner() != PieceType.E) {
                    count++;
                }
            }
            if (count == 3) {
                winner = true;
                winningRow = new int[]{1 + i, 4 + i, 7 + i};
            }
        }

        // Diagonal Win Loop.
        count = 0;
        for (int j = 1; j <= 9; j += 4) {
            if (squares.get(j).getOwner() != PieceType.E) {
                count++;
            }
        }
        if (count == 3) {
            winner = true;
            winningRow = new int[]{1, 5, 9};
        }

        count = 0;
        for (int j = 3; j <= 7; j += 2) {
            if (squares.get(j).getOwner() != PieceType.E) {
                count++;
            }
        }
        if (count == 3) {
            winner = true;
            winningRow = new int[]{3, 5, 7};
        }
        System.out.println("winner: " + getCurrentPlayer().getPlayerName() + "  " + winner);
        return winner;
    }

    public Map<Integer, Square> getSquares() {
        return squares;
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

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        Board.currentPlayer = currentPlayer;
    }
}