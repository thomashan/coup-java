package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.ChallengeActionType;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WaitingChallengeMainActionStateTest extends WaitingChallengeMainActionStateTestCase {
    @Test
    public void testPerformAction_GivenMainActionIsNotChallengeable_ThrowsException() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                createWaitingChallengeMainActionState(anyNonChallengeableMainAction()).performAction(ChallengeAction.of(newBuilder().build(), ChallengeActionType.CHALLENGE)));
        assertEquals("Challenging a non-challengeable action", throwable.getMessage());
    }

    @Test
    public void testPerformAction_GivenMainActionIsChallengeableTurnPlayerSameAsChallenger_ThrowsException() {
        WaitingChallengeMainActionState waitingChallengeMainActionState = createWaitingChallengeMainActionState(anyChallengeableMainAction());
        ChallengeAction challengeAction = ChallengeAction.of(getPlayer(), ChallengeActionType.CHALLENGE);

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> waitingChallengeMainActionState.performAction(challengeAction));
        assertEquals("You cannot challenge your own action", throwable.getMessage());
    }
}
