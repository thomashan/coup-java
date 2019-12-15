package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.card.StandardDeck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TurnStateTest {
    @Mock
    private TurnState<Action> turnState;
    @Mock
    private Action action;

    @Test
    public void testPerform_InvokesPerformAction() {
        when(turnState.perform(any())).thenCallRealMethod();
        when(turnState.isComplete()).thenReturn(false);

        turnState.perform(action);

        verify(turnState).performAction(any());
    }

    @Test
    public void testInitialState() {
        Player player = newBuilder().build();
        Deck deck = StandardDeck.create();

        assertEquals(WaitingMainActionState.class, TurnState.initialState(Players.create(player), player, deck).getClass());
    }
}
