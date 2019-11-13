package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static com.thomashan.coup.PlayerBuilder.build;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActionStateTest {
    @Mock
    private ActionState<Action> actionState;
    @Mock
    private Action action;
    @Mock
    private ActionValidator actionValidator;


    @Test
    public void testPerform_InvokesPerformAction() {
        when(action.getPlayer()).thenReturn(build());
        when(action.getActionType()).thenReturn(ActionTypeTestImpl.DEFAULT);
        when(actionState.perform(any())).thenCallRealMethod();
        when(actionState.getAllowableActionTypes()).thenReturn(Collections.singletonList(ActionTypeTestImpl.DEFAULT));
        when(actionState.isComplete()).thenReturn(false);

        actionState.perform(action);

        verify(actionState).performAction(any());
    }

    @Test
    public void testInitialState() {
        Player player = build();
        assertEquals(WaitingMainActionState.class, ActionState.initialState(Players.create(player), player).getClass());
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
