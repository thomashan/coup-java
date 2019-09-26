package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.thomashan.coup.Action.ASSASSINATE;
import static com.thomashan.coup.Action.COUP;
import static com.thomashan.coup.Action.EXCHANGE;
import static com.thomashan.coup.Action.FOREIGN_AID;
import static com.thomashan.coup.Action.INCOME;
import static com.thomashan.coup.Action.STEAL;
import static com.thomashan.coup.Action.TAX;
import static com.thomashan.coup.Card.AMBASSADOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
    @Test
    public void testStolenToOtherPlayer_PlayerHas3Coins() {
        assertEquals(1, createPlayer(3).stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHas2Coins() {
        assertEquals(0, createPlayer(2).stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHas1Coin() {
        assertEquals(0, createPlayer(1).stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHasNoCoins() {
        assertThrows(IllegalArgumentException.class, () -> createPlayer(0).stolenToOtherPlayer());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas3Coins() {
        assertEquals(4, createPlayer(2).stealFromOtherPlayer(3).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas2Coins() {
        assertEquals(4, createPlayer(2).stealFromOtherPlayer(2).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas1Coin() {
        assertEquals(3, createPlayer(2).stealFromOtherPlayer(1).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHasNoCoins() {
        assertThrows(IllegalArgumentException.class, () -> createPlayer(2).stealFromOtherPlayer(0));
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith2Coins() {
        assertIterableEquals(Arrays.asList(TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), createPlayer(2).getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith3Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), createPlayer(3).getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith6Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), createPlayer(6).getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith7Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID, COUP), createPlayer(7).getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith9Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID, COUP), createPlayer(9).getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCoup_PlayerWith10Coins() {
        assertIterableEquals(Arrays.asList(COUP), createPlayer(10).getAllowableActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCoup_PlayerWith11Coins() {
        assertIterableEquals(Arrays.asList(COUP), createPlayer(11).getAllowableActions());
    }

    private Player createPlayer(int coins) {
        Player player = createPlayerWithNumberOfCoins(coins);

        assert player.getCoins() == coins;

        return player;
    }

    private Player createPlayerWithNumberOfCoins(int coins) {
        Player player = Player.of(AMBASSADOR, AMBASSADOR);

        if (coins < 2) {
            player = player.stolenToOtherPlayer();
            if (coins == 0) {
                return player;
            }

            return player.income();
        } else {
            for (int i = 0; i < coins - 2; i++) {
                player = player.income();
            }

            return player;
        }
    }
}
