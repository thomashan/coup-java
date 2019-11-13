package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.Optional;

public class RevealCardAction implements Action<RevealCardActionType> {
    private final Player player;
    private final RevealCardActionType revealCardActionType;

    private RevealCardAction(Player player, RevealCardActionType revealCardActionType) {
        this.player = player;
        this.revealCardActionType = revealCardActionType;
    }

    public static RevealCardAction of(Player player, RevealCardActionType revealCardActionType) {
        return new RevealCardAction(player, revealCardActionType);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Optional<Player> getTarget() {
        return Optional.empty();
    }

    @Override
    public RevealCardActionType getActionType() {
        return revealCardActionType;
    }

    @Override
    public Class<RevealCardActionType> getActionTypeClass() {
        return RevealCardActionType.class;
    }
}
