package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.empty;

public class WaitingMainActionState implements ActionState<MainAction> {
    private final Player player;

    private WaitingMainActionState(Player player) {
        this.player = player;
    }

    @Override
    public Optional<MainActionType> getMainActionType() {
        return empty();
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
    public ActionState performAction(MainAction action) {
        checkActionPlayerIsSameAsStatePlayer(action);
        checkActionAllowable(action);

        MainActionType mainActionType = action.getActionType();

        if (!mainActionType.isChallengeable()) {
            switch (mainActionType) {
                case INCOME:
                    return CompletedState.of(player, mainActionType);
                case COUP:
                    return WaitingRevealCardState.of(player, mainActionType);
                default:
                    throw new IllegalArgumentException("Unexpected non-challengeable acton");
            }
        }

        return WaitingChallengeMainActionState.of(player, mainActionType, action.getTarget().orElse(null));
    }

    private void checkActionAllowable(MainAction action) {
        if (!action.getActionType().isAllowable(player.getCoins())) {
            throw new IllegalArgumentException("This action is not allowable");
        }
    }

    private void checkActionPlayerIsSameAsStatePlayer(MainAction action) {
        if (!player.equals(action.getPlayer())) {
            throw new IllegalArgumentException("Trying to perform action with different player");
        }
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Arrays.stream(MainActionType.values())
                .filter(mainActionType -> mainActionType.isAllowable(player.getCoins()))
                .collect(Collectors.toList());
    }

    public static WaitingMainActionState of(Player player) {
        return new WaitingMainActionState(player);
    }
}
