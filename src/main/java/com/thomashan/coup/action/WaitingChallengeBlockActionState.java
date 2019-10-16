package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public class WaitingChallengeBlockActionState implements ActionState<ChallengeAction> {
    @Override
    public Optional<MainActionType> getMainActionType() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
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
    public WaitingRevealCardState performAction(ChallengeAction action) {
        return null;
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Arrays.asList(ChallengeActionType.values());
    }
}
