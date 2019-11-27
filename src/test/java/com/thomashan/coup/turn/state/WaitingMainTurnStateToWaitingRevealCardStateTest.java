package com.thomashan.coup.turn.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.newBuilder;
import static com.thomashan.coup.action.MainActionType.COUP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingMainTurnStateToWaitingRevealCardStateTest extends WaitingMainActionStateTestCase {
    @BeforeEach
    public void setUpDefaultPlayer() {
        setUpPlayerCoins(7);
    }

    @Test
    public void testPerformAction_Coup_ReturnsWaitingRevealCardState() {
        assertEquals(WaitingRevealCardState.class, createWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, newBuilder().build())).getClass());
    }

    @Test
    public void testPerformAction_Coup_ReturnsTarget() {
        Player target = newBuilder().build();
        TurnState turnState = createWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, target));

        assertTrue(turnState.getTarget().isPresent());
        assertEquals(target, turnState.getTarget().get());
    }

    @Test
    public void testPerformAction_Coup_ReturnsActionablePlayers() {
        Player target = newBuilder().build();
        TurnState turnState = createWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, target));

        assertEquals(1, turnState.getActionablePlayers().size());
        assertTrue(turnState.getActionablePlayers().contains(target));
    }
}
