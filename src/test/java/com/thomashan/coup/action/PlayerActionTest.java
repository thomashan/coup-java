package com.thomashan.coup.action;

import com.thomashan.coup.Player;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.newBuilder;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerActionTest {
    @Test
    public void testOf_ThrowsException_AssassinateWith2CoinsAndTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().coins(2).build(), ASSASSINATE, newBuilder().build()));
    }

    @Test
    public void testOf_ReturnsPlayerAction_AssassinateWith3CoinsAndTarget() {
        assertNotNull(PlayerAction.of(newBuilder().coins(3).build(), ASSASSINATE, newBuilder().build()));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().coins(3).build(), ASSASSINATE));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndSelfTarget() {
        Player player = newBuilder().coins(3).build();

        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(player, ASSASSINATE, player));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndInactiveTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().coins(3).build(), ASSASSINATE, newBuilder().active(false).build()));
    }

    @Test
    public void testOf_ReturnsPlayerAction_StealWithTarget() {
        assertNotNull(PlayerAction.of(newBuilder().build(), STEAL, newBuilder().build()));
    }

    @Test
    public void testOf_ThrowsException_StealWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().build(), STEAL));
    }

    @Test
    public void testOf_ThrowsException_StealTarget_TargetHasNoCoins() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().build(), STEAL, newBuilder().coins(0).build()));
    }

    @Test
    public void testOf_ThrowsException_StealWithInactiveTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().build(), STEAL, newBuilder().active(false).build()));
    }

    @Test
    public void testOf_ThrowsException_StealWithoutSelfTarget() {
        Player player = newBuilder().build();

        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(player, STEAL, player));
    }

    @Test
    public void testOf_ReturnsPlayerAction_CoupWith7CoinsTarget() {
        assertNotNull(PlayerAction.of(newBuilder().coins(7).build(), COUP, newBuilder().build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWith6CoinsAndTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().coins(6).build(), COUP, newBuilder().build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWithoutTarget() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().coins(7).build(), COUP));
    }

    @Test
    public void testOf_ThrowsException_CoupWith7CoinsAndTargetIsInactive() {
        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(newBuilder().coins(7).build(), COUP, newBuilder().active(false).build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWithSelfTarget() {
        Player player = newBuilder().coins(7).build();

        assertThrows(IllegalArgumentException.class, () -> PlayerAction.of(player, STEAL, player));
    }
}
