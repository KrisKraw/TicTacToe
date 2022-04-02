package com.javaforce.tictactoe;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlayerTest {

    @Test
    public void createPlayer_shouldInitializeNewPlayer_whenNewPlayerInstantiated() {
        PlayerFactory.createPlayer("Ryan", PieceType.X);
        PlayerFactory.createPlayer("Bryan", PieceType.O);
        PlayerFactory.getPlayerMap().get(1).win();
        PlayerFactory.getPlayerMap().forEach((k, v) -> System.out.println(v));
        PlayerFactory.getPlayerMap().get(1).win();
        PlayerFactory.getPlayerMap().forEach((k, v) -> System.out.println(v));
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
    public void test() {
        Random rand = new Random();
        for (int i = 0; i < 50; i++) System.out.println(rand.nextInt(20 - 5 + 1) + 5);

        Map<Integer,String > newMap = new HashMap<>();
        newMap.put(1,"Ryan");
        newMap.put(2,"Jake");
        newMap.put(3,"Bryan");
        for (Map.Entry item : newMap.entrySet()) {
            System.out.println(item);
        }
    }

    @Test
    public void testPlayer_IdShouldBeUnique_whenCreatingSeparatePlayers() {
        PlayerFactory.createPlayer("Jake", PieceType.O);
        PlayerFactory.createPlayer("John", PieceType.X);
    }


}