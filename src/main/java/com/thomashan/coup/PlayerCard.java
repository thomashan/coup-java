package com.thomashan.coup;

import org.immutables.value.Value;

@Value.Immutable
public final class PlayerCard {
    private final Card card;
    private final boolean revealed;

    private PlayerCard(Card card) {
        this.card = card;
        this.revealed = false;
    }

    private PlayerCard(Card card, boolean revealed) {
        this.card = card;
        this.revealed = revealed;
    }

    public static PlayerCard of(Card card) {
        return new PlayerCard(card);
    }

    public Card getCard() {
        return card;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public boolean isHidden() {
        return !isRevealed();
    }

    public PlayerCard reveal() {
        checkIfAlreadyRevealedCard();

        return new PlayerCard(card, true);
    }

    private void checkIfAlreadyRevealedCard() {
        if (isRevealed()) {
            throw new IllegalStateException("Card is already revealed");
        }
    }

    @Override
    public int hashCode() {
        return card.hashCode() + 2 * Boolean.hashCode(revealed);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerCard)) {
            return false;
        }

        PlayerCard other = (PlayerCard) obj;
        return card.equals(other.getCard())
                && revealed == other.isRevealed();
    }
}
