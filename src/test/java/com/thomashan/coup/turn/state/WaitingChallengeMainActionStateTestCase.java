package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.MainAction;

import static java.util.Collections.emptyList;

public class WaitingChallengeMainActionStateTestCase extends TurnStateTestCase {
    protected WaitingChallengeMainActionState createWaitingChallengeMainActionState(MainAction mainAction) {
        return WaitingChallengeMainActionState.of(getPlayers(), getPlayer(), getDeck(), mainAction, emptyList(), null, getPlayers().others(getPlayer()).get());
    }
}
