package com.thomashan.coup.action.state;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static com.thomashan.coup.PlayerBuilder.build;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActionValidatorTest {
    @Mock
    private Action action;

    @Test
    public void testCheckIfComplete_ThrowsException_IfInCompletedState() {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> ActionValidator.checkIfComplete(true));
        assertEquals("Can't perform any more action", throwable.getMessage());
    }

    @Test
    public void testCheckIfComplete_NoException_IfNotInCompletedState() {
        ActionValidator.checkIfComplete(false);
    }

    @Test
    public void testCheckIfActionTypeIsAllowable_ThrowsException_IfActionTypeNotInAllowableActionType() {
        when(action.getActionType()).thenReturn(ActionTypeTestImpl.DEFAULT);

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> ActionValidator.checkIfActionTypeIsAllowable(Collections.emptyList(), action));
        assertEquals("This action is not allowed", throwable.getMessage());
    }

    @Test
    public void testCheckIfActionTypeIsAllowable_NoException_IfActionTypeInAllowableActionType() {
        when(action.getActionType()).thenReturn(ActionTypeTestImpl.DEFAULT);

        ActionValidator.checkIfActionTypeIsAllowable(Collections.singletonList(ActionTypeTestImpl.DEFAULT), action);
    }

    @Test
    public void testCheckActionPlayerIsActive_ThrowsException_IfActionPlayerIsNotActive() {
        when(action.getPlayer()).thenReturn(build(false));

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> ActionValidator.checkActionPlayerIsActive(action));
        assertEquals("The player is not active", throwable.getMessage());
    }

    @Test
    public void testCheckActionPlayerIsActive_NoException_IfActionPlayerIsActive() {
        when(action.getPlayer()).thenReturn(build());

        ActionValidator.checkActionPlayerIsActive(action);
    }

    @Test
    public void testCheckTargetPlayerIsActive_ThrowsException_IfTargetIsNotActive() {
        when(action.getTarget()).thenReturn(Optional.of(build(false)));

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> ActionValidator.checkTargetPlayerIsActive(action));
        assertEquals("The target player is not active", throwable.getMessage());
    }

    @Test
    public void testCheckTargetPlayerIsActive_NoException_IfTargetIsActive() {
        when(action.getTarget()).thenReturn(Optional.of(build()));

        ActionValidator.checkTargetPlayerIsActive(action);
    }

    @Test
    public void testCheckTargetPlayerIsActive_NoException_IfEmptyTarget() {
        when(action.getTarget()).thenReturn(Optional.empty());

        ActionValidator.checkTargetPlayerIsActive(action);
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
