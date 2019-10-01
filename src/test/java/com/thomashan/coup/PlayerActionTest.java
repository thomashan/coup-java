package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.MainActionType.ASSASSINATE;
import static com.thomashan.coup.MainActionType.COUP;
import static com.thomashan.coup.MainActionType.STEAL;
import static com.thomashan.coup.PlayerBuilder.build;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerActionTest {
    @Test
    public void testOf_ThrowsException_AssassinateWith2CoinsAndTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(2), ASSASSINATE, build()));
    }

    @Test
    public void testOf_ReturnsPlayerAction_AssassinateWith3CoinsAndTarget() {
        assertNotNull(PlayerAction.of(build(3), ASSASSINATE, build()));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(3), ASSASSINATE));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndSelfTarget() {
        Player player = build(3);

        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(player, ASSASSINATE, player));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndInactiveTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(3), ASSASSINATE, build(false)));
    }

    @Test
    public void testOf_ReturnsPlayerAction_StealWithTarget() {
        assertNotNull(PlayerAction.of(build(), STEAL, build()));
    }

    @Test
    public void testOf_ThrowsException_StealWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(), STEAL));
    }

    @Test
    public void testOf_ThrowsException_StealTarget_TargetHasNoCoins() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(), STEAL, build(0)));
    }

    @Test
    public void testOf_ThrowsException_StealWithInactiveTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(), STEAL, build(false)));
    }

    @Test
    public void testOf_ThrowsException_StealWithoutSelfTarget() {
        Player player = build();

        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(player, STEAL, player));
    }

    @Test
    public void testOf_ReturnsPlayerAction_CoupWith7CoinsTarget() {
        assertNotNull(PlayerAction.of(build(7), COUP, build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWith6CoinsAndTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(6), COUP, build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(7), COUP));
    }

    @Test
    public void testOf_ThrowsException_CoupWith7CoinsAndTargetIsInactive() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(build(7), COUP, build(false)));
    }

    @Test
    public void testOf_ThrowsException_CoupWithSelfTarget() {
        Player player = build(7);

        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(player, STEAL, player));
    }
}
