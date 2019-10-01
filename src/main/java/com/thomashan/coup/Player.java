package com.thomashan.coup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Player {
    private final int coins;
    private final PlayerCard playerCard1;
    private final PlayerCard playerCard2;

    private Player(int coins, PlayerCard playerCard1, PlayerCard playerCard2) {
        this.coins = coins;
        this.playerCard1 = playerCard1;
        this.playerCard2 = playerCard2;
    }

    public Player revealCard1() {
        return new Player(coins, playerCard1.reveal(), playerCard2);
    }

    public Player revealCard2() {
        return new Player(coins, playerCard1, playerCard2.reveal());
    }

    public boolean isActive() {
        return playerCard1.isHidden() && playerCard2.isHidden();
    }

    public int getCoins() {
        return coins;
    }

    public PlayerCard getPlayerCard1() {
        return playerCard1;
    }

    public PlayerCard getPlayerCard2() {
        return playerCard2;
    }

    public Player income() {
        return new Player(this.coins + 1, playerCard1, playerCard2);
    }

    public Player tax() {
        return new Player(this.coins + 3, playerCard1, playerCard2);
    }

    public Player stealFromOtherPlayer(int otherPlayerCoins) {
        if (otherPlayerCoins < 2) {
            if (otherPlayerCoins == 0) {
                throw new IllegalArgumentException("Stealing from a player with no coins");
            }

            return new Player(this.coins + otherPlayerCoins, playerCard1, playerCard2);
        }

        return new Player(this.coins + 2, playerCard1, playerCard2);
    }

    public Player stolenToOtherPlayer() {
        if (getCoins() < 2) {
            if (getCoins() == 0) {
                throw new IllegalArgumentException("Someone is trying to steal from you but you have no coins");
            }

            return new Player(this.coins - getCoins(), playerCard1, playerCard2);
        }

        return new Player(this.coins - 2, playerCard1, playerCard2);
    }

    public List<MainActionType> getAllowableMainActions() {
        return Arrays.stream(MainActionType.values())
                .filter(action -> action.isAllowable(getCoins()))
                .collect(Collectors.toList());
    }

    public static Player of(Card card1, Card card2) {
        return new Player(2, PlayerCard.of(card1), PlayerCard.of(card2));
    }
}
