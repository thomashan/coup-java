package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.ChallengeActionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WaitingChallengeMainActionStateTest extends WaitingChallengeMainActionStateTestCase {
    @Test
    public void testPerformAction_GivenMainActionIsNotChallengeable_ThrowsException() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                createWaitingChallengeMainActionState(anyNonChallengeableMainAction()).performAction(ChallengeAction.of(getPlayer(), ChallengeActionType.CHALLENGE)));
        assertEquals("Challenging a non-challengeable action", throwable.getMessage());
    }
}
