package com.thomashan.coup.action;

import com.thomashan.coup.Player;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.build;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainActionTest {
    @Test
    public void testOf_ThrowsException_AssassinateWith2CoinsAndTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(2), ASSASSINATE, build()));
        assertEquals("The action is not allowed", throwable.getMessage());
    }

    @Test
    public void testOf_ReturnsMainAction_AssassinateWith3CoinsAndTarget() {
        assertNotNull(MainAction.of(build(3), ASSASSINATE, build()));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndWithoutTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(3), ASSASSINATE));
        assertEquals("Action must specify target", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndSelfTarget() {
        Player player = build(3);

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, ASSASSINATE, player));
        assertEquals("The player performing the action is the target?!", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndInactiveTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(3), ASSASSINATE, build(false)));
        assertEquals("Target player is inactive", throwable.getMessage());
    }

    @Test
    public void testOf_ReturnsMainAction_StealWithTarget() {
        assertNotNull(MainAction.of(build(), STEAL, build()));
    }

    @Test
    public void testOf_ThrowsException_StealWithoutTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(), STEAL));
        assertEquals("Action must specify target", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_StealTarget_TargetHasNoCoins() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(), STEAL, build(0)));
        assertEquals("The action is not allowed", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_StealWithInactiveTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(), STEAL, build(false)));
        assertEquals("Target player is inactive", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_StealWithoutSelfTarget() {
        Player player = build();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, STEAL, player));
        assertEquals("The player performing the action is the target?!", throwable.getMessage());
    }

    @Test
    public void testOf_ReturnsMainAction_CoupWith7CoinsTarget() {
        assertNotNull(MainAction.of(build(7), COUP, build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWith6CoinsAndTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(6), COUP, build()));
        assertEquals("The action is not allowed", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_CoupWithoutTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(7), COUP));
        assertEquals("Action must specify target", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_CoupWith7CoinsAndTargetIsInactive() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(7), COUP, build(false)));
        assertEquals("Target player is inactive", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_CoupWithSelfTarget() {
        Player player = build(7);

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, STEAL, player));
        assertEquals("The player performing the action is the target?!", throwable.getMessage());
    }
}
