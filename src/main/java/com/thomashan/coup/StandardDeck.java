package com.thomashan.coup;

import com.thomashan.collection.immutable.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static com.thomashan.coup.Card.CAPTAIN;
import static com.thomashan.coup.Card.CONTESSA;
import static com.thomashan.coup.Card.DUKE;

public final class StandardDeck implements Deck {
    private final ImmutableList<Card> cards;

    private StandardDeck() {
        List<Card> initialDeck = Arrays.asList(
                DUKE,
                DUKE,
                DUKE,
                ASSASSIN,
                ASSASSIN,
                ASSASSIN,
                AMBASSADOR,
                AMBASSADOR,
                AMBASSADOR,
                CAPTAIN,
                CAPTAIN,
                CAPTAIN,
                CONTESSA,
                CONTESSA,
                CONTESSA
        );

        Collections.shuffle(initialDeck);

        cards = ImmutableList.of(initialDeck);
    }

    private StandardDeck(List<Card> initialDeck) {
        List<Card> shuffledCards = new ArrayList<>(initialDeck);
        Collections.shuffle(shuffledCards);

        cards = ImmutableList.of(shuffledCards);
    }

    public static Deck create() {
        return new StandardDeck();
    }

    private int getLastCardIndex() {
        return cards.size() - 1;
    }

    @Override
    public int getNumberOfCards() {
        return cards.size();
    }

    @Override
    public Card getTopOfDeck() {
        return cards.get(cards.size() - 1);
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public Deck shuffle() {
        return new StandardDeck(cards);
    }

    @Override
    public DrawnCard draw() {
        Card removedCard = cards.get(getLastCardIndex());
        ImmutableList<Card> newCards = cards.minus(removedCard);

        return DrawnCard.of(removedCard, new StandardDeck(newCards));
    }

    @Override
    public Deck plus(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return new StandardDeck(newCards);
    }
}
