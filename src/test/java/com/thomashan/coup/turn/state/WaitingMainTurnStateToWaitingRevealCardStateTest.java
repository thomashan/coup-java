package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingMainTurnStateToWaitingRevealCardStateTest extends WaitingMainActionStateTestCase {
    @BeforeEach
    public void setUpDefaultPlayer() {
        setUpPlayer(newBuilder().coins(7));
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
    public void testPerformAction_GivenCoup_ReturnsAllowableActions() {
        Player target = newBuilder().build();
        TurnState turnState = createWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, target));

        List<Action> actions = turnState.getAllowableActions();

        assertEquals(1, turnState.getAllowableActions().size());
    }
}
