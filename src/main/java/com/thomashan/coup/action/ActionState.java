package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.List;
import java.util.Optional;

public interface ActionState<A extends Action> {
    Player getPlayer();

    Optional<MainActionType> getMainActionType();

    Optional<Player> getTarget();

    Optional<ChallengeActionType> getChallengeActionType();

    Optional<Player> getMainActionChallengedBy();

    Optional<BlockActionType> getBlockAction();

    Optional<ChallengeActionType> getBlockChallengeActionType();

    Optional<Player> getBlockActionChallengedBy();

    boolean isComplete();

    List<ActionType> getAllowableActionTypes();

    ActionState performAction(A Action);

    default ActionState perform(A action) {
        if (isComplete()) {
            throw new IllegalArgumentException("Can't perform any more action");
        }

        if (getAllowableActionTypes().contains(action.getActionTypeClass())) {
            throw new IllegalArgumentException("This action is not allowed");
        }

        return performAction(action);
    }

    static WaitingMainActionState initialState(Player player) {
        return WaitingMainActionState.of(player);
    }
}
