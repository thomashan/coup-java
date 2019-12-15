package com.thomashan.coup.turn.state;

import static java.util.Collections.emptyList;

public class WaitingPutBackCardsActionStateTestCase extends TurnStateTestCase {
    protected WaitingPutBackCardsActionState createWaitingPutBackCardsActionState() {
        return WaitingPutBackCardsActionState.of(getPlayers(), getPlayer(), getDeck(), anyMainMethod(), emptyList(), null);
    }
}
