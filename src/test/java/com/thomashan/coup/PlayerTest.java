package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.thomashan.coup.MainActionType.ASSASSINATE;
import static com.thomashan.coup.MainActionType.COUP;
import static com.thomashan.coup.MainActionType.EXCHANGE;
import static com.thomashan.coup.MainActionType.FOREIGN_AID;
import static com.thomashan.coup.MainActionType.INCOME;
import static com.thomashan.coup.MainActionType.STEAL;
import static com.thomashan.coup.MainActionType.TAX;
import static com.thomashan.coup.PlayerBuilder.build;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
    @Test
    public void testStolenToOtherPlayer_PlayerHas3Coins() {
        assertEquals(1, build(3).stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHas2Coins() {
        assertEquals(0, build(2).stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHas1Coin() {
        assertEquals(0, build(1).stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHasNoCoins() {
        assertThrows(IllegalArgumentException.class, () -> build(0).stolenToOtherPlayer());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas3Coins() {
        assertEquals(4, build(2).stealFromOtherPlayer(3).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas2Coins() {
        assertEquals(4, build(2).stealFromOtherPlayer(2).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas1Coin() {
        assertEquals(3, build(2).stealFromOtherPlayer(1).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHasNoCoins() {
        assertThrows(IllegalArgumentException.class, () -> build(2).stealFromOtherPlayer(0));
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith2Coins() {
        assertIterableEquals(Arrays.asList(TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), build(2).getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith3Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), build(3).getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith6Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), build(6).getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith7Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID, COUP), build(7).getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith9Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID, COUP), build(9).getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCoup_PlayerWith10Coins() {
        assertIterableEquals(Arrays.asList(COUP), build(10).getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCoup_PlayerWith11Coins() {
        assertIterableEquals(Arrays.asList(COUP), build(11).getAllowableMainActions());
    }
}
