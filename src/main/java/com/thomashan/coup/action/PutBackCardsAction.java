package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.Optional;

public class PutBackCardsAction implements Action<PutBackCardActionType> {
    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public PutBackCardActionType getActionType() {
        return null;
    }

    @Override
    public Optional<Player> getTarget() {
        return Optional.empty();
    }

    @Override
    public Class<PutBackCardActionType> getActionTypeClass() {
        return null;
    }
}
