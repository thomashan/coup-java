package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static com.thomashan.coup.PlayerBuilder.build;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompletedStateTest {
    private CompletedState completedState;
    private Player player;
    private Players players;

    @BeforeEach
    public void setUp() {
        player = build();
        players = Players.create(player);

        completedState = CompletedState.of(players, player, Collections.emptyList());
    }

    @Test
    public void testGetActionHistory() {
        assertTrue(completedState.getActionHistory().isEmpty());
    }

    @Test
    public void testGetPlayers() {
        assertEquals(players, completedState.getPlayers());
    }

    @Test
    public void testGetPlayer() {
        assertEquals(player, completedState.getPlayer());
    }

    @Test
    public void testGetActionablePlayers() {
        assertTrue(completedState.getActionablePlayers().isEmpty());
    }

    @Test
    public void testIsComplete() {
        assertTrue(completedState.isComplete());
    }

    @Test
    public void testGetAllowableActionTypes() {
        assertTrue(completedState.getAllowableActionTypes().isEmpty());
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
        public Class getActionTypeClass() {
            return null;
        }

        @Override
        public Optional<Player> getTarget() {
            return Optional.empty();
        }
    }
}
