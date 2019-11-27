package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.thomashan.coup.PlayerBuilder.newBuilder;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.FOREIGN_AID;
import static com.thomashan.coup.action.MainActionType.INCOME;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {
    @Test
    public void testStolenToOtherPlayer_PlayerHas3Coins() {
        assertEquals(1, newBuilder().coins(3).build().stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHas2Coins() {
        assertEquals(0, newBuilder().coins(2).build().stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHas1Coin() {
        assertEquals(0, newBuilder().coins(1).build().stolenToOtherPlayer().getCoins());
    }

    @Test
    public void testStolenToOtherPlayer_PlayerHasNoCoins() {
        Player player = newBuilder().coins(0).build();
        assertThrows(IllegalArgumentException.class, () -> newBuilder().coins(0).build().stolenToOtherPlayer());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas3Coins() {
        assertEquals(4, newBuilder().coins(2).build().stealFromOtherPlayer(3).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas2Coins() {
        assertEquals(4, newBuilder().coins(2).build().stealFromOtherPlayer(2).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHas1Coin() {
        assertEquals(3, newBuilder().coins(2).build().stealFromOtherPlayer(1).getCoins());
    }

    @Test
    public void testStealFromOtherPlayer_OtherPlayerHasNoCoins() {
        assertThrows(IllegalArgumentException.class, () -> newBuilder().coins(2).build().stealFromOtherPlayer(0));
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith2Coins() {
        assertIterableEquals(Arrays.asList(TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), newBuilder().coins(2).build().getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith3Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), newBuilder().coins(3).build().getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith6Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), newBuilder().coins(6).build().getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith7Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID, COUP), newBuilder().coins(7).build().getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCorrectActions_PlayerWith9Coins() {
        assertIterableEquals(Arrays.asList(ASSASSINATE, TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID, COUP), newBuilder().coins(9).build().getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCoup_PlayerWith10Coins() {
        assertIterableEquals(Collections.singletonList(COUP), newBuilder().coins(10).build().getAllowableMainActions());
    }

    @Test
    public void testGetAllowableActions_ReturnsCoup_PlayerWith11Coins() {
        assertIterableEquals(Collections.singletonList(COUP), newBuilder().coins(11).build().getAllowableMainActions());
    }
}
