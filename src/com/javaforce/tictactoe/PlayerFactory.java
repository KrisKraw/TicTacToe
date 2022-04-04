package com.javaforce.tictactoe;

import java.util.Map;
import java.util.TreeMap;

public class PlayerFactory {

    // FIELDS \\
    private static final Map<String, Player> playerMap = new TreeMap<>();
    private static int playerCounter;

    private PlayerFactory () {

    }

    // BUSINESS METHODS \\
    public static Player createPlayer(String name, PieceType pieceType) {

        Player player = null;

        if(playerMap.size() > 1) { // Once we have two players we won't be able to create anymore.
            System.out.println(playerMap);
            throw new IllegalArgumentException("Error: There are already two players.");
        }

        if (playerMap.isEmpty()) { // If player map empty, create player one.
            player = new Player(name, pieceType);
            playerCounter++;
            playerMap.put("Player-" + playerCounter, player);
        } else if (!playerMap.get("Player-1").getPieceType().equals(pieceType)){ // If map is not empty and the chosen PieceType is not already taken, make player two.
            player = new Player(name, pieceType);
            playerCounter++;
            playerMap.put("Player-" + playerCounter, player);
        } else { // Prevent players from choosing same piece type.
            throw new IllegalArgumentException("Error: The following option of ["
                    + pieceType + "] has already been taken." );
        }

        return player;
    }

    // GETTERS & SETTERS \\
    public static Map<String, Player> getPlayerMap() {
        return playerMap;
    }
}