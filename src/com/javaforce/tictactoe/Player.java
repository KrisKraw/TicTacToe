package com.javaforce.tictactoe;

public class Player {

    // FIELDS \\
    private static int playerCount = 0;

    private String playerName;
    private PieceType pieceType;
    private final int playerId;
    private int wins = 0;

    // CONSTRUCTORS \\
    public Player (String playerName, PieceType pieceType) {
        setPlayerName(playerName);
        setPieceType(pieceType);
        playerCount++;
        this.playerId = playerCount;
    }

    // BUSINESS METHODS \\
    public void win() {
        System.out.println("Winner winner, chicken dinner!");
        incrementWins();
    }

    // GETTERS & SETTERS \\
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        ++wins;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + getPlayerName() + '\'' +
                ", pieceType=" + getPieceType() +
                ", playerId=" + getPlayerId() +
                ", wins=" + getWins() +
                '}';
    }
}