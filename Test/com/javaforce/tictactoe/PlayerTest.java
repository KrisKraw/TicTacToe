package com.javaforce.tictactoe;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

public class PlayerTest {

    @Test
    public void createPlayer_shouldInitializeNewPlayer_whenNewPlayerInstantiated() {
        PlayerFactory.createPlayer("Ryan", PieceType.X, Player.PlayerType.HUMAN);
        PlayerFactory.createPlayer("Bryan", PieceType.O, Player.PlayerType.HUMAN);
        for (Map.Entry player : PlayerFactory.getPlayerMap().entrySet()) {
            System.out.println(player);
        }
    }

    @Test
    public void newPlayer_shouldBeComputerPlayerType_whenComputerTypePassedIn() {
        Player player = new Player("Ryan", PieceType.X, Player.PlayerType.COMPUTER);
        System.out.println(player);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayer_shouldThrowIllegalArgumentException_whenPieceTypeIsAlreadyTaken() {
        PlayerFactory.createPlayer("Ryan", PieceType.X, Player.PlayerType.HUMAN);
        PlayerFactory.createPlayer("Bryan", PieceType.X, Player.PlayerType.HUMAN);
    }

    @Test
    public void testCreatePlayerOne_IdShouldBe1_whenCreatingPlayerOne() {
        Player playerOne = PlayerFactory.createPlayer("Jake", PieceType.O, Player.PlayerType.HUMAN);
        assertEquals(playerOne.getPlayerId(), 1);
    }

    @Test
    public void testCreatePlayerTwo_IdShouldBe2_whenCreatingPlayerTwo() {
        Player playerOne = PlayerFactory.createPlayer("Ryan", PieceType.X, Player.PlayerType.HUMAN); // Create playerOne
        Player playerTwo = PlayerFactory.createPlayer("Jake", PieceType.O, Player.PlayerType.HUMAN); // Create playerTwo
        assertEquals(playerTwo.getPlayerId(), 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPlayerLimit_shouldOnlyAllowTwoPlayersMax_whenUsingCreatePlayerMethod() {
        PlayerFactory.createPlayer("Ryan", PieceType.X, Player.PlayerType.HUMAN); // Create playerOne
        PlayerFactory.createPlayer("Jake", PieceType.O, Player.PlayerType.HUMAN); // Create playerTwo
        PlayerFactory.createPlayer("Josh", PieceType.E, Player.PlayerType.HUMAN); // Create playerThree
    }


}