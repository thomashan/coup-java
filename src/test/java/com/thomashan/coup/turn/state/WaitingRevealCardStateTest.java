package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.NoAction;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.PlayerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingRevealCardStateTest extends TurnStateTestCase {
    private WaitingRevealCardState waitingRevealCardState;
    private Player revealer;

    @BeforeEach
    public void setUp() {
        PlayerBuilder playerBuilder = newBuilder();
        revealer = playerBuilder.build();

        waitingRevealCardState = WaitingRevealCardState.of(getPlayers(), getPlayer(), getDeck(), anyMainMethod(), Collections.emptyList(), NoAction.get(), revealer, revealer);
    }

    @Test
    public void testGetActionHistory() {
        assertTrue(waitingRevealCardState.getActionHistory().isEmpty());
    }

    @Test
    public void testGetPlayers() {
        assertEquals(getPlayers(), waitingRevealCardState.getPlayers());
    }

    @Test
    public void testGetPlayer() {
        assertEquals(getPlayer(), waitingRevealCardState.getPlayer());
    }
}
