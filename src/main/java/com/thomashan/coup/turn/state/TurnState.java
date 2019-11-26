package com.thomashan.coup.turn.state;

import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeActionType;

import java.util.List;
import java.util.Optional;

import static com.thomashan.coup.action.ActionValidatorUtil.checkActionPlayerIsActive;
import static com.thomashan.coup.action.ActionValidatorUtil.checkIfActionTypeIsAllowable;
import static com.thomashan.coup.action.ActionValidatorUtil.checkIfComplete;
import static com.thomashan.coup.action.ActionValidatorUtil.checkTargetPlayerIsActive;

public interface TurnState<A extends Action> {
    List<Action> getActionHistory();

    Deck getDeck();

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

    TurnState performAction(A action);

    default TurnState perform(A action) {
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
