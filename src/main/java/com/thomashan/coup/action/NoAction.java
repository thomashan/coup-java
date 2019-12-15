package com.thomashan.coup.action;

import com.thomashan.coup.player.Player;

import java.util.Optional;

public class NoAction implements SingletonAction {
    private static final NoAction INSTANCE = new NoAction();

    private NoAction() {
        // prevent instantiation
    }

    public static NoAction get() {
        return INSTANCE;
    }

    @Override
    public Player getPlayer() {
        throw new UnsupportedOperationException("Can't get player for no action");
    }

    @Override
    public ActionType getActionType() {
        throw new UnsupportedOperationException("Can't get action type for no action");
    }

    @Override
    public Optional<Player> getTarget() {
        throw new UnsupportedOperationException("Can't get target for no action");
    }

    @Override
    public boolean isCheckForActivePlayer() {
        return false;
    }
}
