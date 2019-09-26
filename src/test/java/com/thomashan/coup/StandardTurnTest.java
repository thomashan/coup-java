package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.MainAction.COUP;
import static com.thomashan.coup.MainAction.INCOME;
import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardTurnTest {
    private Turn turn = StandardTurn.create(StandardPlayers.create().addPlayer(Player.of(AMBASSADOR, AMBASSADOR)).addPlayer(Player.of(ASSASSIN, ASSASSIN)));

    @Test
    public void testAttemptAction_Income_CannotBeChallenged() {
        assertEquals(1, turn.attemptMainAction(INCOME).getTurnNumber());
    }

    @Test
    public void testAttemptAction_Coup_CannotBeChallenged() {
        assertEquals(1, turn.attemptMainAction(COUP).getTurnNumber());
    }
}
