package com.thomashan.coup.card;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.card.Card.AMBASSADOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerCardTest {
    @Test
    public void testOf_ReturnsPlayerCard() {
        assertEquals(PlayerCard.class, PlayerCard.of(AMBASSADOR).getClass());
    }

    @Test
    public void testReveal_ReturnsANewInstanceOfPlayerCard() {
        assertNotSame(PlayerCard.of(AMBASSADOR), PlayerCard.of(AMBASSADOR).reveal());
    }

    @Test
    public void testReveal_GivenItIsRevealed_ThrowsAnException() {
        Throwable throwable = assertThrows(IllegalStateException.class, () -> PlayerCard.of(AMBASSADOR).reveal().reveal());
        assertEquals("Card is already revealed", throwable.getMessage());
    }

    @Test
    public void testIsRevealed_GivenItIsHidden_ReturnsFalse() {
        assertFalse(PlayerCard.of(AMBASSADOR).isRevealed());
    }

    @Test
    public void testIsRevealed_GivenItIsNotHidden_ReturnsTure() {
        assertTrue(PlayerCard.of(AMBASSADOR).reveal().isRevealed());
    }

    @Test
    public void testIsHidden_GivenItIsHidden_ReturnsTrue() {
        assertTrue(PlayerCard.of(AMBASSADOR).isHidden());
    }

    @Test
    public void testIsHidden_GivenItIsNotHidden_ReturnsFalse() {
        assertFalse(PlayerCard.of(AMBASSADOR).reveal().isHidden());
    }

    @Test
    public void testGetCard() {
        assertEquals(AMBASSADOR, PlayerCard.of(AMBASSADOR).getCard());
    }

    @Test
    public void testEquals() {
        assertEquals(PlayerCard.of(AMBASSADOR), PlayerCard.of(AMBASSADOR));
    }

    @Test
    public void testHashcode() {
        assertEquals(PlayerCard.of(AMBASSADOR), PlayerCard.of(AMBASSADOR));
    }

}
