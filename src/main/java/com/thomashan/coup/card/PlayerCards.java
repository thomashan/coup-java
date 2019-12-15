package com.thomashan.coup.card;


import com.thomashan.collection.immutable.ImmutableList;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
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

    public List<Card> getActiveCards() {
        return playerCards.stream()
                .filter(playerCard -> playerCard.isHidden())
                .map(playerCard -> playerCard.getCard())
                .collect(Collectors.toList());
    }

    public Set<Card> getActiveCardSet() {
        return new HashSet<>(getActiveCards());
    }

    public boolean isActive() {
        return playerCards.stream()
                .anyMatch(cards -> cards.isHidden());
    }

    public PlayerCards reveal(Card cardToReveal) {
        PlayerCard playerCardToReveal = PlayerCard.of(cardToReveal);
        checkPlayerHasActiveCard(playerCardToReveal);

        return new PlayerCards(playerCards.addOrSet(playerCards.indexOf(playerCardToReveal), playerCardToReveal.reveal()));
    }

    public PlayerCards plus(PlayerCard playerCard) {
        return new PlayerCards(ImmutableList.of(get()).plus(playerCard));
    }

    public PlayerCards minus(PlayerCard playerCard) {
        checkPlayerHasActiveCard(playerCard);

        return new PlayerCards(playerCards.minus(playerCard));
    }

    public PlayerCards minus(Card card) {
        return minus(PlayerCard.of(card));
    }

    private void checkPlayerHasActiveCard(PlayerCard playerCard) {
        if (!playerCards.stream().filter(PlayerCard::isHidden).map(PlayerCard::getCard).anyMatch(c -> c == playerCard.getCard())) {
            throw new IllegalArgumentException(String.format("Player does not have the card %s which is active", playerCard.getCard()));
        }
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        for (PlayerCard playerCard : playerCards) {
            stringBuffer.append("{");
            stringBuffer.append("card=");
            stringBuffer.append(playerCard.getCard());
            stringBuffer.append(",");
            stringBuffer.append("revealed=");
            stringBuffer.append(playerCard.isRevealed());
            stringBuffer.append("}");
        }
        stringBuffer.append("]");

        return stringBuffer.toString();
    }
}
