package com.thomashan.coup;

import java.util.List;

public interface Deck {
    Card getTopOfDeck();

    List<Card> getCards();

    Deck shuffle();

    /**
     * Return the drawn card and the new deck with drawn card removed.
     *
     * @return DrawnCard which has the drawn card and the new Deck with drawn card removed.
     */
    DrawnCard draw();

    /**
     * Return the new deck with the card added.
     *
     * @param card The card to add to the deck.
     * @return Deck the new deck with the card added.
     */
    Deck add(Card card);
}
