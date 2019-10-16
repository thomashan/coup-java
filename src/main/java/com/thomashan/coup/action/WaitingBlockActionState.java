package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public class WaitingBlockActionState implements ActionState<BlockAction> {
    private final Player player;
    private final MainActionType mainActionType;

    private WaitingBlockActionState(Player player, MainActionType mainActionType) {
        this.player = player;
        this.mainActionType = mainActionType;
    }

    public static WaitingBlockActionState of(Player player, MainActionType mainActionType) {
        return new WaitingBlockActionState(player, mainActionType);
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
        return false;
    }

    @Override
    public ActionState performAction(BlockAction action) {
        return null;
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return null;
    }
}
