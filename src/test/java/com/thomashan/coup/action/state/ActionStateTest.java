package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static com.thomashan.coup.PlayerBuilder.build;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActionStateTest {
    @Mock
    private ActionState<ActionTestImpl> actionState;

    @Test
    public void testPerform_ThrowsException_IfInCompletedState() {
        when(actionState.isComplete()).thenReturn(true);
        when(actionState.perform(any())).thenCallRealMethod();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> actionState.perform(new ActionTestImpl()));
        assertEquals("Can't perform any more action", throwable.getMessage());
    }

    @Test
    public void testPerform_ThrowsException_IfActionNotInAllowableAction() {
        when(actionState.getAllowableActionTypes()).thenReturn(Collections.emptyList());
        when(actionState.perform(any())).thenCallRealMethod();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> actionState.perform(new ActionTestImpl()));
        assertEquals("This action is not allowed", throwable.getMessage());
    }

    @Test
    public void testPerform_ThrowsException_IfActionPlayerIsNotActive() {
        when(actionState.perform(any())).thenCallRealMethod();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> actionState.perform(new ActionTestImpl(false)));
        assertEquals("The player is not active", throwable.getMessage());
    }

    @Test
    public void testPerform_ThrowsException_IfTargetIsNotActive() {
        when(actionState.getTarget()).thenReturn(Optional.of(build(false)));
        when(actionState.perform(any())).thenCallRealMethod();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> actionState.perform(new ActionTestImpl()));
        assertEquals("The target player is not active", throwable.getMessage());
    }

    @Test
    public void testPerform_InvokesPerformAction() {
        when(actionState.getAllowableActionTypes()).thenReturn(Arrays.asList(ActionTypeTestImpl.DEFAULT));
        when(actionState.perform(any())).thenCallRealMethod();

        actionState.perform(new ActionTestImpl());

        verify(actionState).performAction(any());
    }

    @Test
    public void testInitialState() {
        Player player = build();
        assertEquals(WaitingMainActionState.class, ActionState.initialState(Players.create(player), player).getClass());
    }

    private static class ActionTestImpl implements Action<ActionType> {
        private final boolean active;

        public ActionTestImpl() {
            this.active = true;
        }

        public ActionTestImpl(boolean active) {
            this.active = active;
        }

        @Override
        public Player getPlayer() {
            return build(active);
        }

        @Override
        public ActionType getActionType() {
            return ActionTypeTestImpl.DEFAULT;
        }

        @Override
        public Class<ActionType> getActionTypeClass() {
            return null;
        }
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
