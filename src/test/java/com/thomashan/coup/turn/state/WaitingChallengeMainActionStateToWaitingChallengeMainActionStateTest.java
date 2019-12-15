package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.player.Player;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.ChallengeActionType.ALLOW;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitingChallengeMainActionStateToWaitingChallengeMainActionStateTest extends WaitingChallengeMainActionStateTestCase {
    @Test
    public void testPerformAction_GivenMainActionChallengeableAndThereAreMultipleOtherPlayers_WhenFirstPlayerDoesNotChallenge_ReturnsWaitingChallengeMainAction() {
        Player player2 = newBuilder().build();
        Player player3 = newBuilder().build();
        setPlayers(getPlayers()
                .addPlayer(player2)
                .addPlayer(player3)
        );

        assertEquals(WaitingChallengeMainActionState.class, createWaitingChallengeMainActionState(MainAction.of(getPlayer(), STEAL, player2)).performAction(ChallengeAction.of(player2, ALLOW)).getClass());
    }
}
