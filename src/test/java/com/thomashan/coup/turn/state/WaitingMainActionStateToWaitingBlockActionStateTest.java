package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.MainActionType.FOREIGN_AID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitingMainActionStateToWaitingBlockActionStateTest extends WaitingMainActionStateTestCases {
    @Test
    public void testPerformAction_ForeignAid_ReturnsWaitingChallengeMainActionState() {
        assertEquals(WaitingBlockActionState.class, getWaitingMainActionState().performAction(MainAction.of(getPlayer(), FOREIGN_AID)).getClass());
    }
}
