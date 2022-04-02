package com.javaforce.tictactoe;

import java.util.Map;
import java.util.TreeMap;

public class PlayerFactory {

    // FIELDS \\
    private static Map<Integer, Player> playerMap = new TreeMap<>();
    private static int playerCounter;

    private PlayerFactory () {

    }

    // BUSINESS METHODS \\
    public static Player createPlayer(String name, PieceType pieceType) {

        Player player = null;

        if (playerMap.isEmpty()) {
            player = new Player(name, pieceType);
            playerCounter++;
            playerMap.put(playerCounter, player);
        } else if (!playerMap.get(1).getPieceType().equals(pieceType)){
            player = new Player(name, pieceType);
            playerCounter++;
            playerMap.put(playerCounter, player);
        } else {
            throw new IllegalArgumentException("Error: The following option of ["
                    + pieceType + "] has already been taken." );
        }

        return player;
    }

    // GETTERS & SETTERS \\
    public static Map<Integer, Player> getPlayerMap() {
        return playerMap;
    }
}