package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeActionType;

import java.util.List;
import java.util.Optional;

public interface ActionState<A extends Action> {
    List<Action> getActionHistory();

    Players getPlayers();

    Player getPlayer();

    List<Player> getActionablePlayers();

    Optional<Player> getTarget();

    // FIXME: do we need this? could be handled by action history
    Optional<ChallengeActionType> getChallengeActionType();

    // FIXME: do we need this? could be handled by action history
    Optional<Player> getMainActionChallengedBy();

    // FIXME: do we need this? could be handled by action history
    Optional<BlockActionType> getBlockAction();

    // FIXME: do we need this? could be handled by action history
    Optional<ChallengeActionType> getBlockChallengeActionType();

    // FIXME: do we need this? could be handled by action history
    Optional<Player> getBlockActionChallengedBy();

    boolean isComplete();

    List<ActionType> getAllowableActionTypes();

    ActionState performAction(A Action);

    default ActionState perform(A action) {
        if (!action.getPlayer().isActive()) {
            throw new IllegalArgumentException("The player is not active");
        }

        getTarget().ifPresent(p -> {
            if (!p.isActive()) {
                throw new IllegalArgumentException("The target player is not active");
            }
        });

        if (isComplete()) {
            throw new IllegalArgumentException("Can't perform any more action");
        }

        if (!getAllowableActionTypes().contains(action.getActionType())) {
            throw new IllegalArgumentException("This action is not allowed");
        }

        return performAction(action);
    }

    static WaitingMainActionState initialState(Players players, Player player) {
        return WaitingMainActionState.of(players, player);
    }
}
