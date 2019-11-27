package com.thomashan.coup.turn.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.BeforeEach;

import static com.thomashan.coup.PlayerBuilder.newBuilder;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.INCOME;

public abstract class TurnStateTestCase {
    private Player player;
    private Players players;

    @BeforeEach
    protected void setUpDefaultPlayer() {
        player = newBuilder().build();
        players = Players.create(player);
    }

    protected void setUpPlayerCoins(int coins) {
        player = newBuilder().coins(coins).build();
        players = Players.create(player);
    }

    protected void setUpActivePlayer(boolean active) {
        player = newBuilder().active(active).build();
        players = Players.create(player);
    }

    protected Player getPlayer() {
        return player;
    }

    protected Players getPlayers() {
        return players;
    }

    protected MainAction anyMainMethod() {
        return MainAction.of(newBuilder().build(), EXCHANGE);
    }

    protected MainAction anyChallengeableMainAction() {
        return anyMainMethod();
    }

    protected MainAction anyNonChallengeableMainAction() {
        return MainAction.of(newBuilder().build(), INCOME);
    }
}
