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
        if (isWinner() == false) {
            //check for empty squares
            for (int i = 1; i < 10; i++) {
                Square sq = getSquares().get(i);
                if(sq.getOwner() == PieceType.E) {
                    // There's vacancy
                    return false;
                }
            }
            // There's no vacancy and no winner therefore a draw.
            return true;
        }
        else {
            return false;
        }
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

    public boolean isWinner2() {
        Map<Integer,Integer> maps = new HashMap();
        maps.put(4,3);
        maps.put(5,3);
        maps.put(6,3);

        //for(int i=1;i<9;i=i+3) {
            for (int i = 1; i < 9; i = i + 3) {
                for (int ii = 1; ii < 3; ii = ii + 3) {
                    System.out.println(i + "  " + ii);
                    if (maps.containsKey(i) && maps.containsKey(i + 1) && maps.containsKey(i + 2)) {
                        System.out.println("HEREEEEEEEEEEEEEEEEEE");
                    }
                }
            }

        for (int i = 1; i < 4; i++) {
            for (int ii = 1; ii < 9; ii = ii + 3) {
                System.out.println(i + "  " + ii);
                if (maps.containsKey(i) && maps.containsKey(i + 1) && maps.containsKey(i + 2)) {
                    System.out.println("HEREEEEEEEEEEEEEEEEEE");
                }
            }
        }

        for (int i = 1; i < 3; i = i + 2) {
            for (int ii = i; ii <= 9; ii = ii + 4) {
                System.out.println(i + "  " + ii);
                if (maps.containsKey(i) && maps.containsKey(i + 1) && maps.containsKey(i + 2)) {
                    System.out.println("HEREEEEEEEEEEEEEEEEEE");
                }
            }
        }

        for (int i = 3; i < 4; i = i + 2) {
            for (int ii = i; ii < 9; ii = ii + 2) {
                System.out.println(i + "  " + ii);
                if (maps.containsKey(i) && maps.containsKey(i + 1) && maps.containsKey(i + 2)) {
                    System.out.println("HEREEEEEEEEEEEEEEEEEE");
                }
            }
        }
        //}
        return false;
    }

    public static PieceType getCurrentPiece() {
        return currentPiece;
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