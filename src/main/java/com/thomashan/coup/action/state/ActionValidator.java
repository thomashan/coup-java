package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;

import java.util.List;
import java.util.Optional;

public class ActionValidator {
    private ActionValidator() {
        // prevent instantiation
    }

    public static void checkActionPlayerIsActive(Action action) {
        if (!action.getPlayer().isActive()) {
            throw new IllegalArgumentException("The player is not active");
        }
    }

    public static void checkTargetPlayerIsActive(Action action) {
        Optional<Player> target = action.getTarget();
        target.ifPresent(p -> {
            if (!p.isActive()) {
                throw new IllegalArgumentException("The target player is not active");
            }
        });
    }

    public static void checkIfComplete(boolean isComplete) {
        if (isComplete) {
            throw new IllegalArgumentException("Can't perform any more action");
        }
    }

    public static void checkIfActionTypeIsAllowable(List<ActionType> allowableActionTypes, Action action) {
        if (!allowableActionTypes.contains(action.getActionType())) {
            throw new IllegalArgumentException("This action is not allowed");
        }
    }
}
