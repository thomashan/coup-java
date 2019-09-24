package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    @Test
    public void testCreate_DrawCorrectNumberOfCards() {
        Game game = Game.create(2);

        assertEquals(11, game.getDeck().getCards().size());
    }
}
