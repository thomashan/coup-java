package com.thomashan.coup.action;

import com.thomashan.coup.player.Player;

import java.util.Optional;

import static java.util.Optional.empty;

public class PerformAction implements Action {
    private final Player player;
    private final ActionType actionType;
    private final Optional<Player> target;

    private PerformAction(Player player, ActionType actionType, Player target) {
        this.player = player;
        this.actionType = actionType;

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    public static PerformAction mainAction(MainAction mainAction) {
        return new PerformAction(mainAction.getPlayer(), mainAction.getActionType(), mainAction.getTarget().orElse(null));
    }

    public static PerformAction takeCoinsForAssassination(Player player) {
        return new PerformAction(player, PerformActionType.TAKE_THREE_COINS, null);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }

    @Override
    public Optional<Player> getTarget() {
        return target;
    }

    @Override
    public boolean isCheckForActivePlayer() {
        return false;
    }

}
