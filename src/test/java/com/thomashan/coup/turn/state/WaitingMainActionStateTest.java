package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.thomashan.coup.action.MainActionType.COUP;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.FOREIGN_AID;
import static com.thomashan.coup.action.MainActionType.INCOME;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static java.util.Optional.empty;
import static java.util.stream.Stream.concat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WaitingMainActionStateTest extends WaitingMainActionStateTestCase {
    @Test
    public void testGetAllowableActions() {
        WaitingMainActionState waitingMainActionState = createWaitingMainActionState();
        Player player = waitingMainActionState.getPlayer();
        Players otherPlayers = waitingMainActionState.getPlayers().others(player);
        Stream<MainAction> stealActions = otherPlayers.get().stream()
                .map(otherPlayer -> MainAction.of(player, STEAL, otherPlayer));
        Stream<MainAction> exchangeActions = Stream.of(MainAction.of(player, EXCHANGE));
        Stream<MainAction> incomeActions = Stream.of(MainAction.of(player, INCOME));
        Stream<MainAction> foreignAidActions = Stream.of(MainAction.of(player, FOREIGN_AID));
        Stream<MainAction> taxAction = Stream.of(MainAction.of(player, TAX));

        List<MainAction> mainActions = concat(concat(concat(concat(taxAction,
                stealActions),
                exchangeActions),
                incomeActions),
                foreignAidActions)
                .collect(Collectors.toList());

        assertIterableEquals(mainActions, waitingMainActionState.getAllowableActions());
    }

    @Test
    public void testGetActionHistory() {
        assertTrue(createWaitingMainActionState().getActionHistory().isEmpty());
    }

    @Test
    public void testGetTarget() {
        assertFalse(createWaitingMainActionState().getTarget().isPresent());
    }

    @Test
    public void testIsComplete() {
        assertFalse(createWaitingMainActionState().isComplete());
    }

    @Test
    public void testPerformAction_ThrowsException_IfActionPlayerIsDifferentToState() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> createWaitingMainActionState().performAction(MainAction.of(newBuilder().build(), INCOME)));
        assertEquals("Trying to perform action with different player", throwable.getMessage());
    }

    @Test
    public void testPerformAction_ThrowsException_IfActionIsNotAllowed() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> createWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, newBuilder().build())));
        assertEquals("The action COUP is not allowed by player Player(coins=2, playerCards=[{card=AMBASSADOR,revealed=false}{card=AMBASSADOR,revealed=false}])", throwable.getMessage());
    }

    @Test
    public void testPerformAction_GivenCoupActionAndNoTarget_ThrowsException() {
        setUpPlayer(newBuilder().coins(7));

        MainAction coup = mock(MainAction.class);
        when(coup.getActionType()).thenReturn(COUP);
        when(coup.getPlayer()).thenReturn(getPlayer());
        when(coup.getTarget()).thenReturn(empty());

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> createWaitingMainActionState().performAction(coup));
        assertEquals("Coup must specify target", throwable.getMessage());
    }

    @Test
    public void testPerformAction_ReturnsWaitingRevealCardState_IfCoupAction() {
        setUpPlayer(newBuilder().coins(7));
        TurnState turnState = createWaitingMainActionState().performAction(MainAction.of(getPlayer(), COUP, newBuilder().build()));

        assertEquals(WaitingRevealCardState.class, turnState.getClass());
    }

    @Test
    public void testPerformAction_ReturnsWaitingChallengeMainActionState_IfNotCoupOrIncomeAction() {
        assertEquals(WaitingChallengeMainActionState.class, createWaitingMainActionState().performAction(MainAction.of(getPlayer(), STEAL, newBuilder().build())).getClass());
    }
}
