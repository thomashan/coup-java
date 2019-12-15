package com.thomashan.coup.action;

import com.thomashan.coup.player.Player;

import java.util.List;
import java.util.Optional;

public final class ActionValidatorUtil {
    private ActionValidatorUtil() {
        throw new AssertionError();
    }

    public static void checkActionPlayerIsActive(Action action) {
        if (action.isCheckForActivePlayer() && !action.getPlayer().isActive()) {
            throw new IllegalArgumentException("The player is not active");
        }
    }

    public static void checkTargetPlayerIsActive(Action action) {
        Optional<Player> target = action.getTarget();
        target.ifPresent(p -> {
            if (action.isCheckForActivePlayer() && !p.isActive()) {
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

    public static <A extends Action> void checkIfActionIsAllowable(List<A> allowableActions, Action action) {
        if (!allowableActions.contains(action)) {
            throw new IllegalArgumentException("This action is not allowed");
        }
    }
}
