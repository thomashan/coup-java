package com.thomashan.coup.player;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.card.Card.AMBASSADOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardPlayersTest {
    @Test
    public void testCreate_ReturnsEmptyPlayers() {
        assertEquals(0, StandardPlayers.create().getNumberOfPlayers());
    }

    @Test
    public void testAdd_AddsANewPlayer() {
        Players players = StandardPlayers.create()
                .addPlayer(Player.of(AMBASSADOR, AMBASSADOR));

        assertEquals(1, players.getNumberOfPlayers());
    }
}
