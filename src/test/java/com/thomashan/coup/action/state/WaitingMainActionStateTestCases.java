package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import org.junit.jupiter.api.BeforeEach;

import static com.thomashan.coup.PlayerBuilder.build;

abstract class WaitingMainActionStateTestCases {
    private WaitingMainActionState waitingMainActionState;
    private Player player;
    private Players players;

    @BeforeEach
    protected void setUp() {
        player = build();
        players = Players.create(player);
        waitingMainActionState = WaitingMainActionState.of(players, player);
    }

    protected void setUp(int coins) {
        player = build(coins);
        players = Players.create(player);
        waitingMainActionState = WaitingMainActionState.of(players, player);
    }

    protected void setUp(boolean active) {
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
