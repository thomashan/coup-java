package com.thomashan.coup.turn.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.ChallengeActionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitingChallengeMainActionStateToWaitingRevealCardStateTest extends WaitingChallengeMainActionStateTestCase {
    @Test
    public void testPerformAction_GivenMainActionIsChallengeableWhenChallenged_ReturnsWaitingRevealCardState() {
        assertEquals(WaitingRevealCardState.class,
                createWaitingChallengeMainActionState(anyChallengeableMainAction()).performAction(ChallengeAction.of(getPlayer(), ChallengeActionType.CHALLENGE)).getClass());
    }

    @Test
    public void testPerformAction_GivenMainActionIsChallengeableWhenChallengedAndMainPlayerLost_ReturnsPlayerAsRevealer() {
        Player player = getPlayer();

        assertEquals(WaitingRevealCardState.class,
                createWaitingChallengeMainActionState(anyChallengeableMainAction()).performAction(ChallengeAction.of(getPlayer(), ChallengeActionType.CHALLENGE)).getClass());
    }

    @Test
    public void testPerformAction_GivenMainActionIsChallengeableWhenChallengedAndChallengerLost_ReturnsChallengerAsRevealer() {

    }
}
