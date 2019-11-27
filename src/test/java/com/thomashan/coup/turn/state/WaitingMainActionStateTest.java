package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static com.thomashan.coup.PlayerBuilder.newBuilder;
import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.FOREIGN_AID;
import static com.thomashan.coup.action.MainActionType.INCOME;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WaitingMainActionStateTest extends WaitingMainActionStateTestCase {
    @Test
    public void testGetAllowableActionTypes() {
        assertIterableEquals(Arrays.asList(TAX, STEAL, EXCHANGE, INCOME, FOREIGN_AID), createWaitingMainActionState().getAllowableActionTypes());
    }

    @Test
    public void testGetBlockAction() {
        assertFalse(createWaitingMainActionState().getBlockAction().isPresent());
    }

    @Test
    public void testGetBlockActionChallengedBy() {
        assertFalse(createWaitingMainActionState().getBlockActionChallengedBy().isPresent());
    }

    @Test
    public void testGetBlockChallengeActionType() {
        assertFalse(createWaitingMainActionState().getBlockChallengeActionType().isPresent());
    }

    @Test
    public void testGetChallengeActionType() {
        assertFalse(createWaitingMainActionState().getChallengeActionType().isPresent());
    }

    @Test
    public void testGetMainActionChallengedBy() {
        assertFalse(createWaitingMainActionState().getMainActionChallengedBy().isPresent());
    }

    @Test
    public void testGetActionHistory() {
        assertTrue(createWaitingMainActionState().getActionHistory().isEmpty());
    }

    @Test
    public void testGetTarget() {
        assertFalse(createWaitingMainActionState().getTarget().isPresent());
    }

    @Test
    public void testIsComplete() {
        assertFalse(createWaitingMainActionState().isComplete());
    }

    @Test
    public void testPerformAction_ThrowsException_IfActionPlayerIsDifferentToState() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> createWaitingMainActionState().performAction(MainAction.of(newBuilder().build(), INCOME)));
        assertEquals("Trying to perform action with different player", throwable.getMessage());
    }

    @Test
    public void testPerformAction_ThrowsException_IfActionIsNotAllowed() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> createWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, newBuilder().build())));
        assertEquals("The action is not allowed", throwable.getMessage());
    }

    @Test
    public void testPerformAction_GivenCoupActionAndNoTarget_ThrowsException() {
        setUpPlayerCoins(7);

        MainAction coup = mock(MainAction.class);
        when(coup.getActionType()).thenReturn(COUP);
        when(coup.getPlayer()).thenReturn(getPlayer());
        when(coup.getTarget()).thenReturn(empty());

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> createWaitingMainActionState().performAction(coup));
        assertEquals("Coup must specify target", throwable.getMessage());
    }

    @Test
    public void testPerformAction_ReturnsWaitingRevealCardState_IfCoupAction() {
        setUpPlayerCoins(7);
        TurnState turnState = createWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, newBuilder().build()));

        assertEquals(WaitingRevealCardState.class, turnState.getClass());
    }

    @Test
    public void testPerformAction_ReturnsWaitingChallengeMainActionState_IfNotCoupOrIncomeAction() {
        assertEquals(WaitingChallengeMainActionState.class, createWaitingMainActionState().performAction(MainAction.of(getPlayer(), STEAL, newBuilder().build())).getClass());
    }
}
