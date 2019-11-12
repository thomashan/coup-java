package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StandardGameTest {
    @Test
    public void testCreate_DrawCorrectNumberOfCards() {
        assertEquals(11, StandardGame.create(2).getDeck().getNumberOfCards());
    }

    @Test
    public void testCreate_CreateCorrectNumberOfPlayers() {
        StandardGame game = StandardGame.create(2);
        assertEquals(2, StandardGame.create(2).getPlayers().getNumberOfPlayers());
    }

    @Test
    public void testCreate_MinimumNumberOfPlayersException() {
        assertThrows(IllegalArgumentException.class, () -> StandardGame.create(1));
    }

    @Test
    public void testCreate_MaximumNumberOfPlayersException() {
        assertThrows(IllegalArgumentException.class, () -> StandardGame.create(7));
    }
}
