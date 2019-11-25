package com.thomashan.coup;


import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.collection.immutable.ImmutableSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerCards {
    private final List<PlayerCard> playerCards;

    private PlayerCards(List<PlayerCard> playerCards) {
        this.playerCards = playerCards;
    }

    private PlayerCards(PlayerCard playerCard1, PlayerCard playerCard2) {
        this.playerCards = ImmutableList.of(playerCard1, playerCard2);
    }

    public static PlayerCards of(Card card1, Card card2) {
        return new PlayerCards(ImmutableList.of(PlayerCard.of(card1), PlayerCard.of(card2)));
    }

    public List<PlayerCard> get() {
        return playerCards;
    }

    public Set<Card> getActiveCards() {
        return ImmutableSet.of(playerCards.stream()
                .filter(cards -> cards.isHidden())
                .map(c -> c.getCard())
                .collect(Collectors.toSet()));
    }

    public boolean isAllShown() {
        return playerCards.stream()
                .allMatch(cards -> cards.isShown());
    }

    public boolean isAllHidden() {
        return playerCards.stream()
                .allMatch(cards -> cards.isHidden());
    }

    public PlayerCards revealCard1() {
        if (playerCards.get(0).isShown()) {
            throw new IllegalStateException("Card 1 is already revealed");
        }

        return new PlayerCards(playerCards.get(0).reveal(), playerCards.get(1));
    }

    public PlayerCards revealCard2() {
        if (playerCards.get(1).isShown()) {
            throw new IllegalStateException("Card 2 is already revealed");
        }

        return new PlayerCards(playerCards.get(0), playerCards.get(1).reveal());
    }

    public PlayerCards plus(PlayerCard playerCard) {
        return new PlayerCards(ImmutableList.of(playerCard).plus(playerCard));
    }

    public PlayerCards minus(PlayerCard playerCard) {
        if (!playerCards.contains(playerCard)) {
            throw new IllegalArgumentException(String.format("Player does not have the card", playerCard.toString()));
        }

        return new PlayerCards(ImmutableList.of(playerCard).plus(playerCard));
    }
}
