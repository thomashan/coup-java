package com.thomashan.coup.player;

import com.thomashan.coup.action.MainActionType;
import com.thomashan.coup.card.Card;
import com.thomashan.coup.card.PlayerCard;
import com.thomashan.coup.card.PlayerCards;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
public final class Player {
    private final int coins;
    private final PlayerCards playerCards;

    private Player(int coins, Card card1, Card card2) {
        this.coins = coins;
        this.playerCards = PlayerCards.of(card1, card2);
    }

    private Player(int coins, PlayerCards playerCards) {
        this.coins = coins;
        this.playerCards = playerCards;
    }

    public static Player of(Card card1, Card card2) {
        return new Player(2, card1, card2);
    }

    public Player revealCard(Card card) {
        return new Player(getCoins(), playerCards.reveal(card));
    }

    public Player minus(Card card) {
        return new Player(getCoins(), getPlayerCards().minus(PlayerCard.of(card)));
    }

    public Player plus(Card card) {
        return new Player(getCoins(), playerCards.plus(PlayerCard.of(card)));
    }

    public boolean isActive() {
        return playerCards.isActive();
    }

    public int getCoins() {
        return coins;
    }

    public PlayerCards getPlayerCards() {
        return playerCards;
    }

    public List<Card> getActiveCards() {
        return playerCards.getActiveCards();
    }

    public Set<Card> getActiveCardSet() {
        return playerCards.getActiveCardSet();
    }

    public Player income() {
        return new Player(getCoins() + 1, playerCards);
    }

    public Player foreignAid() {
        return new Player(getCoins() + 2, playerCards);
    }

    public Player tax() {
        return new Player(getCoins() + 3, playerCards);
    }

    public Player useCoinsForAssassination() {
        return new Player(getCoins() - 3, playerCards);
    }

    public Player stealFromOtherPlayer(int otherPlayerCoins) {
        if (otherPlayerCoins < 2) {
            if (otherPlayerCoins == 0) {
                throw new IllegalArgumentException("Stealing from a player with no coins");
            }

            return new Player(getCoins() + otherPlayerCoins, playerCards);
        }

        return new Player(getCoins() + 2, playerCards);
    }

    public Player stolenToOtherPlayer() {
        if (getCoins() < 2) {
            if (getCoins() == 0) {
                throw new IllegalArgumentException("Someone is trying to steal from you but you have no coins");
            }

            return new Player(getCoins() - getCoins(), playerCards);
        }

        return new Player(getCoins() - 2, playerCards);
    }

    public List<MainActionType> getAllowableMainActions() {
        return Arrays.stream(MainActionType.values())
                .filter(action -> action.isAllowable(getCoins()))
                .collect(Collectors.toList());
    }
}
