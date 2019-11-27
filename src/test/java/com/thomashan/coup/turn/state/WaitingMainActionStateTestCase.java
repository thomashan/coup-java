package com.thomashan.coup.turn.state;

public class WaitingMainActionStateTestCase extends TurnStateTestCase {
    protected WaitingMainActionState createWaitingMainActionState() {
        return WaitingMainActionState.of(getPlayers(), getPlayer());
    }
}
