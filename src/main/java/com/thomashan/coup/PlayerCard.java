package com.thomashan.coup;

public class PlayerCard {
    private final Card card;
    private final boolean shown;

    private PlayerCard(Card card) {
        this.card = card;
        this.shown = false;
    }

    private PlayerCard(Card card, boolean shown) {
        this.card = card;
        this.shown = shown;
    }

    public static PlayerCard of(Card card) {
        return new PlayerCard(card);
    }

    public Card getCard() {
        return card;
    }

    public boolean isShown() {
        return shown;
    }

    public boolean isHidden() {
        return !isShown();
    }

    public PlayerCard reveal() {
        return new PlayerCard(card, true);
    }
}
