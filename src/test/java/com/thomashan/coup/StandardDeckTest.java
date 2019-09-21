package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static com.thomashan.coup.Card.CAPTAIN;
import static com.thomashan.coup.Card.CONTESSA;
import static com.thomashan.coup.Card.DUKE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StandardDeckTest {
    private Deck deck = new StandardDeck();

    @Test
    public void testNullConstructor_ThreeOfEachCard() {
        assertEquals(3, deck.getCards().stream().filter(card -> card == AMBASSADOR).count());
        assertEquals(3, deck.getCards().stream().filter(card -> card == ASSASSIN).count());
        assertEquals(3, deck.getCards().stream().filter(card -> card == CAPTAIN).count());
        assertEquals(3, deck.getCards().stream().filter(card -> card == CONTESSA).count());
        assertEquals(3, deck.getCards().stream().filter(card -> card == DUKE).count());
    }

    @Test
    public void testNullConstructor_InitialSizeIs15() {
        assertEquals(15, deck.getCards().size());
    }

    @Test
    public void testNullConstructor_IsRandom() {
        Deck otherDeck = new StandardDeck();

        assertNotEquals(deck.getCards(), otherDeck.getCards());
    }

    @Test
    public void testShuffle() {
        Deck shuffledDeck = deck.shuffle();

        assertNotEquals(deck.getCards(), shuffledDeck.getCards());
    }

    @Test
    public void testDraw_ReturnTopOfDeck() {
        Card topOfDeck = deck.getTopOfDeck();

        DrawnCard drawnCard = deck.draw();

        assertEquals(topOfDeck, drawnCard.getCard());
    }

    @Test
    public void testDraw_ReturnDeckWithDrawnCardRemoved() {
        DrawnCard drawnCard = deck.draw();

        assertEquals(14, drawnCard.getDeck().getCards().size());
    }
}
