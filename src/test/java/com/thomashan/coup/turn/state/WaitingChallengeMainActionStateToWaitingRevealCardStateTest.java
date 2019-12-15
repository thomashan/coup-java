package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.ChallengeActionType;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.player.Player;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitingChallengeMainActionStateToWaitingRevealCardStateTest extends WaitingChallengeMainActionStateTestCase {
    @Test
    public void testPerformAction_GivenMainActionIsChallengeable_WhenChallenged_ReturnsWaitingRevealCardState() {
        assertEquals(WaitingRevealCardState.class,
                createWaitingChallengeMainActionState(anyChallengeableMainAction()).performAction(ChallengeAction.of(newBuilder().build(), ChallengeActionType.CHALLENGE)).getClass());
    }

    @Test
    public void testPerformAction_GivenMainActionIsChallengeable_WhenChallengedAndMainPlayerLost_ReturnsPlayerAsRevealer() {
        setUpPlayer(newBuilder().card1(AMBASSADOR).card2(AMBASSADOR));
        WaitingChallengeMainActionState waitingChallengeMainActionState = createWaitingChallengeMainActionState(MainAction.of(getPlayer(), STEAL, newBuilder().build()));
        ChallengeAction challengeAction = ChallengeAction.of(newBuilder().build(), ChallengeActionType.CHALLENGE);

        WaitingRevealCardState waitingRevealCardState = (WaitingRevealCardState) waitingChallengeMainActionState.performAction(challengeAction);

        assertEquals(getPlayer(), waitingRevealCardState.getRevealer());

    }

    @Test
    public void testPerformAction_GivenMainActionIsChallengeable_WhenChallengedAndChallengerLost_ReturnsChallengerAsRevealer() {
        setUpPlayer(newBuilder().card1(AMBASSADOR).card2(AMBASSADOR));
        WaitingChallengeMainActionState waitingChallengeMainActionState = createWaitingChallengeMainActionState(MainAction.of(getPlayer(), EXCHANGE));
        Player challenger = newBuilder().build();
        ChallengeAction challengeAction = ChallengeAction.of(challenger, ChallengeActionType.CHALLENGE);

        WaitingRevealCardState waitingRevealCardState = (WaitingRevealCardState) waitingChallengeMainActionState.performAction(challengeAction);

        assertEquals(challenger, waitingRevealCardState.getRevealer());
    }
}
