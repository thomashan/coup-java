package com.thomashan.coup;

public class Player {
    private final int coins;
    private final PlayerCard playerCard1;
    private final PlayerCard playerCard2;

    private Player(PlayerCard playerCard1, PlayerCard playerCard2, int coins) {
        this.playerCard1 = playerCard1;
        this.playerCard2 = playerCard2;
        this.coins = coins;
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

    public static Player of(PlayerCard playerCard1, PlayerCard playerCard2) {
        return new Player(playerCard1, playerCard2, 2);
    }
}
