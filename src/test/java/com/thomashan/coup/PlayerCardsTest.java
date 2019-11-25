package com.thomashan.coup;

import com.thomashan.collection.immutable.ImmutableList;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static com.thomashan.coup.Card.CAPTAIN;
import static com.thomashan.coup.Card.DUKE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerCardsTest {
    @Test
    public void testOf_ReturnPlayerCards() {
        assertEquals(PlayerCards.class, PlayerCards.of(AMBASSADOR, ASSASSIN).getClass());
    }

    @Test
    public void testGetActivePlayerCards_GivenSameCard_ReturnSingleElement() {
        assertEquals(1, PlayerCards.of(AMBASSADOR, AMBASSADOR).getActivePlayerCards().size());
    }

    @Test
    public void testGetActivePlayerCards_GivenDifferentCards_ReturnAllElement() {
        assertEquals(2, PlayerCards.of(AMBASSADOR, ASSASSIN).getActivePlayerCards().size());
    }

    @Test
    public void testGet() {
        assertIterableEquals(ImmutableList.of(PlayerCard.of(AMBASSADOR), PlayerCard.of(AMBASSADOR)), PlayerCards.of(AMBASSADOR, AMBASSADOR).get());
    }

    @Test
    public void testReveal_GivenPlayerDoesNotHaveTheCard_ThrowsAnException() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> PlayerCards.of(AMBASSADOR, AMBASSADOR).reveal(PlayerCard.of(ASSASSIN)));
        assertEquals("Player does not have the card", throwable.getMessage());
    }

    @Test
    public void testReveal_GivenPlayerHasTheCardButDifferentRevealState_ThrowsAnException() {
        PlayerCards playerCards = PlayerCards.of(AMBASSADOR, ASSASSIN).reveal(PlayerCard.of(ASSASSIN));

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> playerCards.reveal(PlayerCard.of(ASSASSIN)));
        assertEquals("Player does not have the card", throwable.getMessage());
    }

    @Test
    public void testReveal_GivenCardIsAlreadyRevealed_ThrowsAnException() {
        PlayerCards playerCards = PlayerCards.of(AMBASSADOR, ASSASSIN).reveal(PlayerCard.of(ASSASSIN));

        Throwable throwable = assertThrows(IllegalStateException.class, () -> playerCards.reveal(PlayerCard.of(ASSASSIN).reveal()));
        assertEquals("Card is already revealed", throwable.getMessage());
    }

    @Test
    public void testPlus_ReturnsPlayerCards() {
        assertEquals(PlayerCards.class, PlayerCards.of(AMBASSADOR, AMBASSADOR).plus(PlayerCard.of(AMBASSADOR)).getClass());
    }

    @Test
    public void testPlus_ReturnsNewInstanceOfPlayerCards() {
        PlayerCards playerCards = PlayerCards.of(AMBASSADOR, AMBASSADOR);

        assertNotSame(playerCards, playerCards.plus(PlayerCard.of(AMBASSADOR)));
    }

    @Test
    public void testPlus_AddsNewPlayerCard() {
        assertEquals(3, PlayerCards.of(AMBASSADOR, AMBASSADOR).plus(PlayerCard.of(AMBASSADOR)).get().size());
    }

    @Test
    public void testMinus_GivenPlayerDoesNotHaveTheCard_ThrowsAnException() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> PlayerCards.of(AMBASSADOR, AMBASSADOR).minus(PlayerCard.of(ASSASSIN)));
        assertEquals("Player does not have the card", throwable.getMessage());
    }

    @Test
    public void testMinus_GivenPlayerHasTheCardButDifferentRevealState_ThrowsAnException() {
        PlayerCards playerCards = PlayerCards.of(AMBASSADOR, ASSASSIN).reveal(PlayerCard.of(ASSASSIN));

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> playerCards.minus(PlayerCard.of(ASSASSIN)));
        assertEquals("Player does not have the card", throwable.getMessage());
    }

    @Test
    public void testMinus_GivenPlayerHasPlayerCard_ReturnsPlayerCards() {
        assertEquals(PlayerCards.class, PlayerCards.of(AMBASSADOR, AMBASSADOR).minus(PlayerCard.of(AMBASSADOR)).getClass());
    }

    @Test
    public void testMinus_GivenPlayerHasPlayerCard_ReturnsNewInstanceOfPlayerCards() {
        PlayerCards playerCards = PlayerCards.of(AMBASSADOR, AMBASSADOR);

        assertNotSame(playerCards, playerCards.minus(PlayerCard.of(AMBASSADOR)));
    }

    @Test
    public void testMinus_GivenPlayerHasPlayerCard_RemovesPlayerCard() {
        assertIterableEquals(ImmutableList.of(PlayerCard.of(AMBASSADOR), PlayerCard.of(CAPTAIN)), PlayerCards.of(AMBASSADOR, DUKE).plus(PlayerCard.of(ASSASSIN)).plus(PlayerCard.of(CAPTAIN)).minus(PlayerCard.of(DUKE)).minus(PlayerCard.of(ASSASSIN)).get());
    }
}
