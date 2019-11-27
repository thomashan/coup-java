package com.thomashan.coup.turn.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static com.thomashan.coup.PlayerBuilder.newBuilder;
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
        when(action.getPlayer()).thenReturn(newBuilder().build());
        when(action.getActionType()).thenReturn(ActionTypeTestImpl.DEFAULT);
        when(turnState.perform(any())).thenCallRealMethod();
        when(turnState.getAllowableActionTypes()).thenReturn(Collections.singletonList(ActionTypeTestImpl.DEFAULT));
        when(turnState.isComplete()).thenReturn(false);

        turnState.perform(action);

        verify(turnState).performAction(any());
    }

    @Test
    public void testInitialState() {
        Player player = newBuilder().build();
        assertEquals(WaitingMainActionState.class, TurnState.initialState(Players.create(player), player).getClass());
    }

    private enum ActionTypeTestImpl implements ActionType {
        DEFAULT;

        @Override
        public boolean isChallengeable() {
            return false;
        }

        @Override
        public boolean isBlockable() {
            return false;
        }
    }
}
