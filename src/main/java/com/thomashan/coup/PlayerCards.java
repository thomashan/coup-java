package com.thomashan.coup;


import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.collection.immutable.ImmutableSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class PlayerCards {
    private final transient ImmutableList<PlayerCard> playerCards;

    private PlayerCards(ImmutableList<PlayerCard> playerCards) {
        this.playerCards = playerCards;
    }

    public static PlayerCards of(Card card1, Card card2) {
        return new PlayerCards(ImmutableList.of(PlayerCard.of(card1), PlayerCard.of(card2)));
    }

    public List<PlayerCard> get() {
        return playerCards;
    }

    public Set<PlayerCard> getActivePlayerCards() {
        return ImmutableSet.of(playerCards.stream()
                .filter(cards -> cards.isHidden())
                .collect(Collectors.toSet()));
    }

    public boolean isActive() {
        return playerCards.stream()
                .anyMatch(cards -> cards.isHidden());
    }

    public PlayerCards reveal(PlayerCard playerCardToReveal) {
        checkPlayerHasCard(playerCardToReveal);
        checkIfAlreadyRevealedCard(playerCardToReveal);

        return new PlayerCards(playerCards.addOrSet(playerCards.indexOf(playerCardToReveal), playerCardToReveal.reveal()));
    }

    private void checkPlayerHasCard(PlayerCard playerCard) {
        if (!playerCards.contains(playerCard)) {
            throw new IllegalArgumentException(String.format("Player does not have the card", playerCard.toString()));
        }
    }

    private void checkIfAlreadyRevealedCard(PlayerCard playerCard) {
        if (playerCard.isRevealed()) {
            throw new IllegalStateException("Card is already revealed");
        }
    }

    public PlayerCards plus(PlayerCard playerCard) {
        return new PlayerCards(ImmutableList.of(get()).plus(playerCard));
    }

    public PlayerCards minus(PlayerCard playerCard) {
        checkPlayerHasCard(playerCard);

        return new PlayerCards(ImmutableList.of(get()).minus(playerCard));
    }
}
