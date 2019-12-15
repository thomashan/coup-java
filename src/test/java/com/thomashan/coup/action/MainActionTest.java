package com.thomashan.coup.action;

import com.thomashan.coup.player.Player;
import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.action.MainActionType.INCOME;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainActionTest {
    @Test
    public void testOf_ThrowsException_AssassinateWith2CoinsAndTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().coins(2).build(), ASSASSINATE, newBuilder().build()));
        assertEquals("The action ASSASSINATE is not allowed by player Player(coins=2, playerCards=[{card=AMBASSADOR,revealed=false}{card=AMBASSADOR,revealed=false}])", throwable.getMessage());
    }

    @Test
    public void testOf_ReturnsMainAction_AssassinateWith3CoinsAndTarget() {
        assertNotNull(MainAction.of(newBuilder().coins(3).build(), ASSASSINATE, newBuilder().build()));
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndWithoutTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().coins(3).build(), ASSASSINATE));
        assertEquals("The action requires a target", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndSelfTarget() {
        Player player = newBuilder().coins(3).build();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, ASSASSINATE, player));
        assertEquals("The player performing the action is the target?!", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_AssassinateWith3CoinsAndInactiveTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().coins(3).build(), ASSASSINATE, newBuilder().active(false).build()));
        assertEquals("Target player is inactive", throwable.getMessage());
    }

    @Test
    public void testOf_ReturnsMainAction_StealWithTarget() {
        assertNotNull(MainAction.of(newBuilder().build(), STEAL, newBuilder().build()));
    }

    @Test
    public void testOf_ThrowsException_StealWithoutTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().build(), STEAL));
        assertEquals("The action requires a target", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_StealTarget_TargetHasNoCoins() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().build(), STEAL, newBuilder().coins(0).build()));
        assertEquals("The action is not allowed", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_StealWithInactiveTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().build(), STEAL, newBuilder().active(false).build()));
        assertEquals("Target player is inactive", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_StealWithoutSelfTarget() {
        Player player = newBuilder().build();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, STEAL, player));
        assertEquals("The player performing the action is the target?!", throwable.getMessage());
    }

    @Test
    public void testOf_ReturnsMainAction_CoupWith7CoinsTarget() {
        assertNotNull(MainAction.of(newBuilder().coins(7).build(), COUP, newBuilder().build()));
    }

    @Test
    public void testOf_ThrowsException_CoupWith6CoinsAndTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().coins(6).build(), COUP, newBuilder().build()));
        assertEquals("The action COUP is not allowed by player Player(coins=6, playerCards=[{card=AMBASSADOR,revealed=false}{card=AMBASSADOR,revealed=false}])", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_CoupWithoutTarget() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().coins(7).build(), COUP));
        assertEquals("The action requires a target", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_CoupWith7CoinsAndTargetIsInactive() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(newBuilder().coins(7).build(), COUP, newBuilder().active(false).build()));
        assertEquals("Target player is inactive", throwable.getMessage());
    }

    @Test
    public void testOf_ThrowsException_CoupWithSelfTarget() {
        Player player = newBuilder().coins(7).build();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> MainAction.of(player, STEAL, player));
        assertEquals("The player performing the action is the target?!", throwable.getMessage());
    }

    @Test
    public void testEquals() {
        Player player = newBuilder().build();

        assertEquals(MainAction.of(player, INCOME), MainAction.of(player, INCOME));
    }
}
