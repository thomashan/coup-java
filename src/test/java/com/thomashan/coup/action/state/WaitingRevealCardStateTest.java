package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.thomashan.coup.PlayerBuilder.build;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingRevealCardStateTest {
    private WaitingRevealCardState waitingRevealCardState;
    private Players players;
    private Player player;
    private Player revealer;

    @BeforeEach
    public void setUp() {
        player = build();
        players = Players.create(player);
        revealer = build();
        waitingRevealCardState = WaitingRevealCardState.of(players, player, Collections.emptyList(), revealer);
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
