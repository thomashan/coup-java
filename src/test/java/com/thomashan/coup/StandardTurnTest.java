package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.MainAction.COUP;
import static com.thomashan.coup.MainAction.INCOME;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardTurnTest {
    @Test
    public void testAttemptAction_Income_CannotBeChallenged() {
        assertEquals(1, StandardTurn.create(StandardPlayers.create()
                .addPlayer(PlayerBuilder.build())
                .addPlayer(PlayerBuilder.build()))
                .attemptMainAction(INCOME).getTurnNumber());
    }

    @Test
    public void testAttemptAction_Coup_CannotBeChallenged() {
        assertEquals(1, StandardTurn.create(StandardPlayers.create()
                .addPlayer(PlayerBuilder.build(7))
                .addPlayer(PlayerBuilder.build()))
                .attemptMainAction(COUP).getTurnNumber());
    }
}
