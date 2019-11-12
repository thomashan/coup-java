package com.thomashan.coup.action.state;

import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.MainActionType.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingMainActionStateToCompletedStateTest extends WaitingMainActionStateTestCases {
    @Test
    public void testPerformAction_IncomeAction_ReturnsCompletedState() {
        assertEquals(CompletedState.class, getWaitingMainActionState().performAction(MainAction.of(getPlayer(), INCOME)).getClass());
    }

    @Test
    public void testPerformAction_IncomeAction_PlayersContainsNewPlayer() {
        ActionState actionState = getWaitingMainActionState().performAction(MainAction.of(getPlayer(), INCOME));

        assertTrue(actionState.getPlayers().get().contains(actionState.getPlayer()));
        assertFalse(actionState.getPlayers().get().contains(getPlayer()));
    }

    @Test
    public void testPerformAction_IncomeAction_NewPlayerWithOneMoreCoin() {
        ActionState actionState = getWaitingMainActionState().performAction(MainAction.of(getPlayer(), INCOME));

        assertEquals(getPlayer().getCoins() + 1, actionState.getPlayer().getCoins());
    }
}
