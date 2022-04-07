package com.javaforce.tictactoe;

public class Player {

    // FIELDS \\
    private static int playerCount = 0;

    private String playerName;
    private PieceType pieceType;
    private PlayerType playerType = PlayerType.HUMAN;
    private final int playerId;
    private int wins = 0;


    // CONSTRUCTORS \\
    public Player (String playerName, PieceType pieceType) {
        setPlayerName(playerName);
        setPieceType(pieceType);
        playerCount++;
        this.playerId = playerCount;
    }

    public Player (String playerName, PieceType pieceType, PlayerType playerType) {
        this(playerName, pieceType);
        setPlayerName(playerName);
        setPieceType(pieceType);
        setPlayerType(playerType);
    }

    // BUSINESS METHODS \\
    public void win() {
        System.out.println("Winner winner, chicken dinner!");
        incrementWins();
    }

    // GETTERS & SETTERS \\
    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

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

    public enum PlayerType {
        HUMAN,
        COMPUTER
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + getPlayerName() + '\'' +
                ", pieceType=" + getPieceType() +
                ", playerType=" + getPlayerType() +
                ", playerId=" + getPlayerId() +
                ", wins=" + getWins() +
                '}';
    }
}