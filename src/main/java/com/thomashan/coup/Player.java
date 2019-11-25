package com.thomashan.coup;

import com.thomashan.coup.action.MainActionType;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Player revealCard(PlayerCard playerCard) {
        return new Player(coins, playerCards.reveal(playerCard));
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

    public Set<PlayerCard> getActivePlayerCards() {
        return playerCards.getActivePlayerCards();
    }

    public Player income() {
        return new Player(this.coins + 1, playerCards);
    }

    public Player tax() {
        return new Player(this.coins + 3, playerCards);
    }

    public Player stealFromOtherPlayer(int otherPlayerCoins) {
        if (otherPlayerCoins < 2) {
            if (otherPlayerCoins == 0) {
                throw new IllegalArgumentException("Stealing from a player with no coins");
            }

            return new Player(this.coins + otherPlayerCoins, playerCards);
        }

        return new Player(this.coins + 2, playerCards);
    }

    public Player stolenToOtherPlayer() {
        if (getCoins() < 2) {
            if (getCoins() == 0) {
                throw new IllegalArgumentException("Someone is trying to steal from you but you have no coins");
            }

            return new Player(this.coins - getCoins(), playerCards);
        }

        return new Player(this.coins - 2, playerCards);
    }

    public List<MainActionType> getAllowableMainActions() {
        return Arrays.stream(MainActionType.values())
                .filter(action -> action.isAllowable(getCoins()))
                .collect(Collectors.toList());
    }
}
