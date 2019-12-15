package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitingMainActionStateToWaitingChallengeMainActionStateTest extends WaitingMainActionStateTestCase {
    @Test
    public void testPerformAction_Assassinate_ReturnsWaitingChallengeMainActionState() {
        setUpPlayer(newBuilder().coins(3));

        assertEquals(WaitingChallengeMainActionState.class, createWaitingMainActionState().performAction(MainAction.of(getPlayer(), ASSASSINATE, newBuilder().build())).getClass());
    }

    @Test
    public void testPerformAction_Tax_ReturnsWaitingChallengeMainActionState() {
        assertEquals(WaitingChallengeMainActionState.class, createWaitingMainActionState().performAction(MainAction.of(getPlayer(), TAX)).getClass());
    }

    @Test
    public void testPerformAction_Steal_ReturnsWaitingChallengeMainActionState() {
        assertEquals(WaitingChallengeMainActionState.class, createWaitingMainActionState().performAction(MainAction.of(getPlayer(), STEAL, newBuilder().build())).getClass());
    }

    @Test
    public void testPerformAction_Exchange_ReturnsWaitingChallengeMainActionState() {
        assertEquals(WaitingChallengeMainActionState.class, createWaitingMainActionState().performAction(MainAction.of(getPlayer(), EXCHANGE)).getClass());
    }

}
