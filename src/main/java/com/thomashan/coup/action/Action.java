package com.thomashan.coup.action;

import com.thomashan.coup.player.Player;

import java.util.Optional;

public interface Action<A extends ActionType> {
    Player getPlayer();

    A getActionType();

    Optional<Player> getTarget();

    boolean isCheckForActivePlayer();
}
