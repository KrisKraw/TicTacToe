package com.javaforce.tictactoe;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

public class PlayerTest {

    @Test
    public void createPlayer_shouldInitializeNewPlayer_whenNewPlayerInstantiated() {
        PlayerFactory.createPlayer("Ryan", PieceType.X);
        PlayerFactory.createPlayer("Bryan", PieceType.O);
        for (Map.Entry player : PlayerFactory.getPlayerMap().entrySet()) {
            System.out.println(player);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayer_shouldThrowIllegalArgumentException_whenPieceTypeIsAlreadyTaken() {
        PlayerFactory.createPlayer("Ryan", PieceType.X);
        PlayerFactory.createPlayer("Bryan", PieceType.X);
    }

    @Test
    public void testCreatePlayerOne_IdShouldBe1_whenCreatingPlayerOne() {
        Player playerOne = PlayerFactory.createPlayer("Jake", PieceType.O);
        assertEquals(playerOne.getPlayerId(), 1);
    }

    @Test
    public void testCreatePlayerTwo_IdShouldBe2_whenCreatingPlayerTwo() {
        Player playerOne = PlayerFactory.createPlayer("Ryan", PieceType.X); // Create playerOne
        Player playerTwo = PlayerFactory.createPlayer("Jake", PieceType.O); // Create playerTwo
        assertEquals(playerTwo.getPlayerId(), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPlayerLimit_shouldOnlyAllowTwoPlayersMax_whenUsingCreatePlayerMethod() {
        PlayerFactory.createPlayer("Ryan", PieceType.X); // Create playerOne
        PlayerFactory.createPlayer("Jake", PieceType.O); // Create playerTwo
        PlayerFactory.createPlayer("Josh", PieceType.E); // Create playerThree
    }


}