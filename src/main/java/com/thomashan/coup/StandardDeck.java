package com.thomashan.coup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static com.thomashan.coup.Card.CAPTAIN;
import static com.thomashan.coup.Card.CONTESSA;
import static com.thomashan.coup.Card.DUKE;

public class StandardDeck implements Deck {
    private final List<Card> cards;

    private StandardDeck() {
        List<Card> initialDeck = new ArrayList<>();
        initialDeck.add(DUKE);
        initialDeck.add(DUKE);
        initialDeck.add(DUKE);
        initialDeck.add(ASSASSIN);
        initialDeck.add(ASSASSIN);
        initialDeck.add(ASSASSIN);
        initialDeck.add(AMBASSADOR);
        initialDeck.add(AMBASSADOR);
        initialDeck.add(AMBASSADOR);
        initialDeck.add(CAPTAIN);
        initialDeck.add(CAPTAIN);
        initialDeck.add(CAPTAIN);
        initialDeck.add(CONTESSA);
        initialDeck.add(CONTESSA);
        initialDeck.add(CONTESSA);

        Collections.shuffle(initialDeck);

        cards = initialDeck;
    }

    private StandardDeck(List<Card> initialDeck) {
        List<Card> cardsToShuffle = new ArrayList<>(initialDeck);
        Collections.shuffle(cardsToShuffle);

        cards = cardsToShuffle;
    }

    private int getLastCardIndex() {
        return cards.size() - 1;
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
        Card removedCard = cards.remove(getLastCardIndex());

        return DrawnCard.of(removedCard, new StandardDeck(cards));
    }

    @Override
    public Deck add(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return new StandardDeck(newCards);
    }

    public static Deck create() {
        return new StandardDeck();
    }
}
