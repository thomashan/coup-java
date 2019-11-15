package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.action.MainAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.build;
import static com.thomashan.coup.action.MainActionType.COUP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaitingMainActionStateToWaitingRevealCardStateTest extends WaitingMainActionStateTestCases {
    @BeforeEach
    public void setUpDefaultPlayer() {
        setUpPlayerCoins(7);
    }

    @Test
    public void testPerformAction_Coup_ReturnsWaitingRevealCardState() {
        assertEquals(WaitingRevealCardState.class, getWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, build())).getClass());
    }

    @Test
    public void testPerformAction_Coup_ReturnsTarget() {
        Player target = build();
        ActionState actionState = getWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, target));

        assertTrue(actionState.getTarget().isPresent());
        assertEquals(target, actionState.getTarget().get());
    }

    @Test
    public void testPerformAction_Coup_ReturnsActionablePlayers() {
        Player target = build();
        ActionState actionState = getWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, target));

        assertEquals(1, actionState.getActionablePlayers().size());
        assertTrue(actionState.getActionablePlayers().contains(target));
    }
}
