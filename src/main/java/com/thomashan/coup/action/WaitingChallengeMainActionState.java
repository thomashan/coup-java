package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thomashan.coup.action.ChallengeActionType.CHALLENGE;
import static java.util.Optional.empty;

public class WaitingChallengeMainActionState implements ActionState<ChallengeAction> {
    private final Player player;
    private final MainActionType mainActionType;
    private final Optional<Player> target;

    private WaitingChallengeMainActionState(Player player, MainActionType mainActionType, Player target) {
        this.player = player;
        this.mainActionType = mainActionType;

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    public static WaitingChallengeMainActionState of(Player player, MainActionType mainActionType, Player target) {
        return new WaitingChallengeMainActionState(player, mainActionType, target);
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
        return target;
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
    public boolean isComplete() {
        return false;
    }

    @Override
    public ActionState performAction(ChallengeAction action) {
        if (action.getChallengeActionType() == CHALLENGE) {
            return WaitingRevealCardState.of(player, mainActionType);
        }

        if (mainActionType.isBlockable()) {
            return WaitingBlockActionState.of(player, mainActionType);
        }

        return CompletedState.of(player, mainActionType);
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Arrays.asList(ChallengeActionType.values());
    }
}
