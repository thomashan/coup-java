package com.thomashan.coup.action;

import com.thomashan.coup.Player;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.build;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainActionTest {
    @Test
    public void testOf_ThrowsException_AssassinateWith2CoinsAndTarget() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(2), ASSASSINATE, build()));
    }

    @Test
    public void testOf_ReturnsMainAction_AssassinateWith3CoinsAndTarget() {
        assertNotNull(MainAction.of(build(3), ASSASSINATE, build()));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(3), ASSASSINATE));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndSelfTarget() {
        Player player = build(3);

        assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, ASSASSINATE, player));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndInactiveTarget() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(3), ASSASSINATE, build(false)));
    }

    @Test
    public void testOf_ReturnsMainAction_StealWithTarget() {
        assertNotNull(MainAction.of(build(), STEAL, build()));
    }

    @Test
    public void testOf_ThrowsException_StealWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(), STEAL));
    }

    @Test
    public void testOf_ThrowsException_StealTarget_TargetHasNoCoins() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(), STEAL, build(0)));
    }

    @Test
    public void testOf_ThrowsException_StealWithInactiveTarget() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(), STEAL, build(false)));
    }

    @Test
    public void testOf_ThrowsException_StealWithoutSelfTarget() {
        Player player = build();

        assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, STEAL, player));
    }

    @Test
    public void testOf_ReturnsMainAction_CoupWith7CoinsTarget() {
        assertNotNull(MainAction.of(build(7), COUP, build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWith6CoinsAndTarget() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(6), COUP, build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(7), COUP));
    }

    @Test
    public void testOf_ThrowsException_CoupWith7CoinsAndTargetIsInactive() {
        assertThrows(IllegalArgumentException.class, () -> MainAction.of(build(7), COUP, build(false)));
    }

    @Test
    public void testOf_ThrowsException_CoupWithSelfTarget() {
        Player player = build(7);

        assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, STEAL, player));
    }
}
