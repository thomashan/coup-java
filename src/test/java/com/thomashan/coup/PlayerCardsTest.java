package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerCardsTest {
    @Test
    public void testOf_ReturnPlayerCards() {
        assertEquals(PlayerCards.class, PlayerCards.of(AMBASSADOR, ASSASSIN).getClass());
    }
}
