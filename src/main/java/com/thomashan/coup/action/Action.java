package com.thomashan.coup.action;

import com.thomashan.coup.Player;

public interface Action<A extends ActionType> {
    Player getPlayer();

    A getActionType();

    Class<A> getActionTypeClass();
}
