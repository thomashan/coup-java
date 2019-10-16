package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public class CompletedState implements ActionState {
    private final Player player;
    private final MainActionType mainActionType;

    private CompletedState(Player player, MainActionType mainActionType) {
        this.player = player;
        this.mainActionType = mainActionType;
    }

    public static CompletedState of(Player player, MainActionType mainActionType) {
        return new CompletedState(player, mainActionType);
    }

    @Override
    public Optional<MainActionType> getMainActionType() {
        return Optional.of(mainActionType);
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
        return true;
    }

    @Override
    public ActionState performAction(Action action) {
        throw new UnsupportedOperationException("Can't perform any more action");
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Collections.emptyList();
    }
}
