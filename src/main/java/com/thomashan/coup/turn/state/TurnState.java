package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.List;
import java.util.Optional;

import static com.thomashan.coup.action.ActionValidatorUtil.checkActionPlayerIsActive;
import static com.thomashan.coup.action.ActionValidatorUtil.checkIfActionIsAllowable;
import static com.thomashan.coup.action.ActionValidatorUtil.checkIfComplete;
import static com.thomashan.coup.action.ActionValidatorUtil.checkTargetPlayerIsActive;

public interface TurnState<A extends Action> {
    static WaitingMainActionState initialState(Players players, Player player, Deck deck) {
        return WaitingMainActionState.of(players, player, deck);
    }

    List<Action> getActionHistory();

    Deck getDeck();

    Players getPlayers();

    Player getPlayer();

    Optional<Player> getTarget();

    MainAction getMainAction();

    boolean isComplete();

    boolean isCheckAllowableActions();

    List<A> getAllowableActions();

    TurnState performAction(A action);

    default TurnState perform(A action) {
        checkActionPlayerIsActive(action);
        checkTargetPlayerIsActive(action);
        if (isCheckAllowableActions()) {
            checkIfActionIsAllowable(getAllowableActions(), action);
        }
        checkIfComplete(isComplete());

        return performAction(action);
    }
}
