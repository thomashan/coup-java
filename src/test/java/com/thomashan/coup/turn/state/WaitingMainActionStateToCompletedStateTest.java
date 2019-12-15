package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.MainActionType.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingMainActionStateToCompletedStateTest extends WaitingMainActionStateTestCase {
    @Test
    public void testPerformAction_IncomeAction_ReturnsCompletedState() {
        assertEquals(CompletedState.class, createWaitingMainActionState().performAction(MainAction.of(getPlayer(), INCOME)).getClass());
    }

    @Test
    public void testPerformAction_IncomeAction_PlayersContainsNewPlayer() {
        TurnState turnState = createWaitingMainActionState().performAction(MainAction.of(getPlayer(), INCOME));

        assertTrue(turnState.getPlayers().get().contains(turnState.getPlayer()));
        assertFalse(turnState.getPlayers().get().contains(getPlayer()));
    }

    @Test
    public void testPerformAction_IncomeAction_NewPlayerWithOneMoreCoin() {
        TurnState turnState = createWaitingMainActionState().performAction(MainAction.of(getPlayer(), INCOME));

        assertEquals(getPlayer().getCoins() + 1, turnState.getPlayer().getCoins());
    }
}
