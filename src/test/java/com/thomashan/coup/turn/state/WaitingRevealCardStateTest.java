package com.thomashan.coup.turn.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.PlayerBuilder;
import com.thomashan.coup.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.thomashan.coup.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingRevealCardStateTest extends TurnStateTestCase {
    private WaitingRevealCardState waitingRevealCardState;
    private Players players;
    private Player player;
    private Player revealer;

    @BeforeEach
    public void setUp() {
        PlayerBuilder playerBuilder = newBuilder();
        player = playerBuilder.build();
        players = Players.create(player);
        revealer = playerBuilder.build();
        waitingRevealCardState = WaitingRevealCardState.of(players, player, anyMainMethod(), Collections.emptyList(), revealer);
    }

    @Test
    public void testGetActionHistory() {
        assertTrue(waitingRevealCardState.getActionHistory().isEmpty());
    }

    @Test
    public void testGetPlayers() {
        assertEquals(players, waitingRevealCardState.getPlayers());
    }

    @Test
    public void testGetPlayer() {
        assertEquals(player, waitingRevealCardState.getPlayer());
    }

    @Test
    public void testGetActionablePlayers() {
        assertEquals(1, waitingRevealCardState.getActionablePlayers().size());
        assertTrue(waitingRevealCardState.getActionablePlayers().contains(revealer));
    }
}
