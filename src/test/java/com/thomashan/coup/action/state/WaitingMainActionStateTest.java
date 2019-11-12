package com.thomashan.coup.action.state;

import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.thomashan.coup.PlayerBuilder.build;
import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.FOREIGN_AID;
import static com.thomashan.coup.action.MainActionType.INCOME;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingMainActionStateTest extends WaitingMainActionStateTestCases {
    @Test
    public void testGetAllowableActionTypes() {
        assertIterableEquals(Arrays.asList(TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), getWaitingMainActionState().getAllowableActionTypes());
    }

    @Test
    public void testGetBlockAction() {
        assertFalse(getWaitingMainActionState().getBlockAction().isPresent());
    }

    @Test
    public void testGetBlockActionChallengedBy() {
        assertFalse(getWaitingMainActionState().getBlockActionChallengedBy().isPresent());
    }

    @Test
    public void testGetBlockChallengeActionType() {
        assertFalse(getWaitingMainActionState().getBlockChallengeActionType().isPresent());
    }

    @Test
    public void testGetChallengeActionType() {
        assertFalse(getWaitingMainActionState().getChallengeActionType().isPresent());
    }

    @Test
    public void testGetMainActionChallengedBy() {
        assertFalse(getWaitingMainActionState().getMainActionChallengedBy().isPresent());
    }

    @Test
    public void testGetActionHistory() {
        assertTrue(getWaitingMainActionState().getActionHistory().isEmpty());
    }

    @Test
    public void testGetTarget() {
        assertFalse(getWaitingMainActionState().getTarget().isPresent());
    }

    @Test
    public void testIsComplete() {
        assertFalse(getWaitingMainActionState().isComplete());
    }

    @Test
    public void testPerformAction_ThrowsException_IfActionPlayerIsDifferentToState() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> getWaitingMainActionState().performAction(MainAction.of(build(), INCOME)));
        assertEquals("Trying to perform action with different player", throwable.getMessage());
    }

    @Test
    public void testPerformAction_ThrowsException_IfActionIsNotAllowed() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> getWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, build())));
        assertEquals("The action is not allowed", throwable.getMessage());
    }

    @Test
    public void testPerformAction_ReturnsWaitingRevealCardState_IfCoupAction() {
        setUp(7);
        ActionState actionState = getWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, build()));

        assertEquals(WaitingRevealCardState.class, actionState.getClass());
    }

    @Test
    public void testPerformAction_ReturnsWaitingChallengeMainActionState_IfNotCoupOrIncomeAction() {
        assertEquals(WaitingChallengeMainActionState.class, getWaitingMainActionState().performAction(MainAction.of(getPlayer(), STEAL, build())).getClass());
    }
}