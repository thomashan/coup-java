package com.thomashan.coup;

import java.util.List;

public interface Deck {
    Card getTopOfDeck();

    List<Card> getCards();

    Deck shuffle();

    /**
     * Return the drawn card and remove from the deck.
     *
     * @return DrawnCard which has the drawn card and the new Deck.
     */
    DrawnCard draw();
}
