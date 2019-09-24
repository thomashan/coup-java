package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {
    @Test
    public void testCreate_DrawCorrectNumberOfCards() {
        assertEquals(11, Game.create(2).getDeck().getNumberOfCards());
    }

    @Test
    public void testCreate_CreateCorrectNumberOfPlayers() {
        assertEquals(2, Game.create(2).getPlayers().getNumberOfPlayers());
    }

    @Test
    public void testCreate_MinimumNumberOfPlayersException() {
        assertThrows(IllegalArgumentException.class, () -> Game.create(1));
    }

    @Test
    public void testCreate_MaximumNumberOfPlayersException() {
        assertThrows(IllegalArgumentException.class, () -> Game.create(7));
    }
}
