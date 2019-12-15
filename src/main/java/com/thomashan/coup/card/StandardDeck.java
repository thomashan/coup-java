package com.thomashan.coup.card;

import com.thomashan.collection.immutable.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.card.Card.ASSASSIN;
import static com.thomashan.coup.card.Card.CAPTAIN;
import static com.thomashan.coup.card.Card.CONTESSA;
import static com.thomashan.coup.card.Card.DUKE;

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

    public static StandardDeck create() {
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
        return cards.get(getLastCardIndex());
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
        Card removedCard = getTopOfDeck();
        ImmutableList<Card> newCards = ImmutableList.of(new LinkedList(cards.minus(removedCard)));

        return DrawnCard.of(removedCard, new StandardDeck(newCards));
    }

    @Override
    public DrawnCard draw(Card card) {
        return DrawnCard.of(card, minus(card));
    }


    @Override
    public Deck plus(Card card) {
        return new StandardDeck(cards.plus(card));
    }

    @Override
    public Deck minus(Card card) {
        checkCardExistsInDeck(card);

        return new StandardDeck(ImmutableList.of(new LinkedList(cards.minus(card))));
    }

    private void checkCardExistsInDeck(Card card) {
        if (!getCards().contains(card)) {
            throw new IllegalArgumentException("The card " + card + " doesn't exist");
        }
    }
}
