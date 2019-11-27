package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.build;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitingMainActionStateToWaitingChallengeMainActionStateTest extends WaitingMainActionStateTestCases {
    @Test
    public void testPerformAction_Assassinate_ReturnsWaitingChallengeMainActionState() {
        setUpPlayerCoins(3);

        assertEquals(WaitingChallengeMainActionState.class, getWaitingMainActionState().performAction(MainAction.of(getPlayer(), ASSASSINATE, build())).getClass());
    }

    @Test
    public void testPerformAction_Tax_ReturnsWaitingChallengeMainActionState() {
        assertEquals(WaitingChallengeMainActionState.class, getWaitingMainActionState().performAction(MainAction.of(getPlayer(), TAX)).getClass());
    }

    @Test
    public void testPerformAction_Steal_ReturnsWaitingChallengeMainActionState() {
        assertEquals(WaitingChallengeMainActionState.class, getWaitingMainActionState().performAction(MainAction.of(getPlayer(), STEAL, build())).getClass());
    }

    @Test
    public void testPerformAction_Exchange_ReturnsWaitingChallengeMainActionState() {
        assertEquals(WaitingChallengeMainActionState.class, getWaitingMainActionState().performAction(MainAction.of(getPlayer(), EXCHANGE)).getClass());
    }

}
