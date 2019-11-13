package com.thomashan.coup.action.state;

import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeActionType;

import java.util.List;
import java.util.Optional;

import static com.thomashan.coup.action.state.ActionValidator.checkActionPlayerIsActive;
import static com.thomashan.coup.action.state.ActionValidator.checkIfActionTypeIsAllowable;
import static com.thomashan.coup.action.state.ActionValidator.checkIfComplete;
import static com.thomashan.coup.action.state.ActionValidator.checkTargetPlayerIsActive;

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

    // FIXME: replace with getAllowableActions
    List<ActionType> getAllowableActionTypes();

    ActionState performAction(A Action);

    default ActionState perform(A action) {
        checkActionPlayerIsActive(action);
        checkTargetPlayerIsActive(action);
        checkIfActionTypeIsAllowable(getAllowableActionTypes(), action);
        checkIfComplete(isComplete());

        return performAction(action);
    }

    static WaitingMainActionState initialState(Players players, Player player) {
        return WaitingMainActionState.of(players, player);
    }
}
