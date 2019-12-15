package com.thomashan.coup.card;

public final class DrawnCard {
    private final Card card;
    private final Deck deck;

    private DrawnCard(Card card, Deck deck) {
        this.card = card;
        this.deck = deck;
    }

    public static DrawnCard of(Card card, Deck deck) {
        return new DrawnCard(card, deck);
    }

    public Card getCard() {
        return card;
    }

    public Deck getDeck() {
        return deck;
    }
}
