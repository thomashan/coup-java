package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompletedStateTest extends TurnStateTestCase {
    private CompletedState completedState;

    @BeforeEach
    public void setUp() {
        completedState = CompletedState.of(getPlayers(), getPlayer(), getDeck(), anyMainMethod(), Collections.emptyList(), null);
    }

    @Test
    public void testGetActionHistory() {
        assertTrue(completedState.getActionHistory().isEmpty());
    }

    @Test
    public void testGetPlayers() {
        assertEquals(getPlayers(), completedState.getPlayers());
    }

    @Test
    public void testGetPlayer() {
        assertEquals(getPlayer(), completedState.getPlayer());
    }

    @Test
    public void testIsComplete() {
        assertTrue(completedState.isComplete());
    }

    @Test
    public void testGetAllowableActions() {
        assertTrue(completedState.getAllowableActions().isEmpty());
    }

    @Test
    public void testPerformAction_ThrowsUnsupportedOperationException() {
        Throwable throwable = assertThrows(UnsupportedOperationException.class, () -> completedState.performAction(new ActionTestImpl()));
        assertEquals("Can't perform any more action", throwable.getMessage());
    }

    private static final class ActionTestImpl implements Action {
        @Override
        public Player getPlayer() {
            return null;
        }

        @Override
        public ActionType getActionType() {
            return null;
        }

        @Override
        public Optional<Player> getTarget() {
            return Optional.empty();
        }

        @Override
        public boolean isCheckForActivePlayer() {
            return true;
        }
    }
}
