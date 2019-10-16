package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public class WaitingRevealCardState implements ActionState<RevealCardAction> {
    private final Player player;
    private final MainActionType mainActionType;

    private WaitingRevealCardState(Player player, MainActionType mainActionType) {
        this.player = player;
        this.mainActionType = mainActionType;
    }

    @Override
    public Optional<MainActionType> getMainActionType() {
        return Optional.of(mainActionType);
    }

    public static WaitingRevealCardState of(Player player, MainActionType mainActionType) {
        return new WaitingRevealCardState(player, mainActionType);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Optional<Player> getTarget() {
        return empty();
    }

    @Override
    public Optional<ChallengeActionType> getChallengeActionType() {
        return empty();
    }

    @Override
    public Optional<BlockActionType> getBlockAction() {
        return empty();
    }

    @Override
    public Optional<ChallengeActionType> getBlockChallengeActionType() {
        return empty();
    }


    @Override
    public Optional<Player> getMainActionChallengedBy() {
        return empty();
    }

    @Override
    public Optional<Player> getBlockActionChallengedBy() {
        return empty();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public CompletedState performAction(RevealCardAction action) {
        return null;
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return null;
    }
}
