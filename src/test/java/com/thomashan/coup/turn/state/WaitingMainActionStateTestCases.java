package com.thomashan.coup.turn.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import org.junit.jupiter.api.BeforeEach;

import static com.thomashan.coup.PlayerBuilder.build;

public class WaitingMainActionStateTestCases {
    private WaitingMainActionState waitingMainActionState;
    private Player player;
    private Players players;

    @BeforeEach
    protected void setUpDefaultPlayer() {
        player = build();
        players = Players.create(player);
        waitingMainActionState = WaitingMainActionState.of(players, player);
    }

    protected void setUpPlayerCoins(int coins) {
        player = build(coins);
        players = Players.create(player);
        waitingMainActionState = WaitingMainActionState.of(players, player);
    }

    protected void setUpActivePlayer(boolean active) {
        player = build(active);
        players = Players.create(player);
        waitingMainActionState = WaitingMainActionState.of(players, player);
    }

    protected Player getPlayer() {
        return player;
    }

    protected Players getPlayers() {
        return players;
    }

    protected WaitingMainActionState getWaitingMainActionState() {
        return waitingMainActionState;
    }
}
