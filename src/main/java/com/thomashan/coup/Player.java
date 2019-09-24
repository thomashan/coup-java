package com.thomashan.coup;

public class Player {
    private final int coins;
    private final PlayerCard playerCard1;
    private final PlayerCard playerCard2;

    private Player(int coins, PlayerCard playerCard1, PlayerCard playerCard2) {
        this.coins = coins;
        this.playerCard1 = playerCard1;
        this.playerCard2 = playerCard2;
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

    public static Player of(Card card1, Card card2) {
        return new Player(2, PlayerCard.of(card1), PlayerCard.of(card2));
    }
}
