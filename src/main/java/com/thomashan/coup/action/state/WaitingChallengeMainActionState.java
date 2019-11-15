package com.thomashan.coup.action.state;

import com.thomashan.collection.CollectionUtil;
import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionDetector;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeAction;
import com.thomashan.coup.action.ChallengeActionType;
import com.thomashan.coup.action.MainAction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thomashan.coup.action.ChallengeActionType.CHALLENGE;
import static java.util.Optional.empty;

public final class WaitingChallengeMainActionState implements ActionState<ChallengeAction> {
    private final Players players;
    private final Player player;
    private final List<Action> actionHistory;
    private final Optional<Player> target;

    private WaitingChallengeMainActionState(Players players, Player player, List<Action> actionHistory, Player target) {
        this.players = players;
        this.player = player;
        this.actionHistory = actionHistory;

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    public static WaitingChallengeMainActionState of(Players players, Player player, List<Action> actionHistory, Player target) {
        return new WaitingChallengeMainActionState(players, player, actionHistory, target);
    }

    @Override
    public List<Action> getActionHistory() {
        return actionHistory;
    }

    @Override
    public Deck getDeck() {
        return null;
    }

    @Override
    public Players getPlayers() {
        return players;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Player> getActionablePlayers() {
        // FIXME: return all players apart from the player who performed the main action
        return null;
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
        List<Action> newActionHistory = CollectionUtil.add(actionHistory, action);

        if (action.getChallengeActionType() == CHALLENGE) {
            if (ActionDetector.isBluff(((MainAction) actionHistory.get(0)).getActionType(), player.getActiveCards())) {
                return WaitingRevealCardState.of(players, player, newActionHistory, player);
            } else {
                return WaitingRevealCardState.of(players, player, newActionHistory, action.getPlayer());
            }
        }

        if (actionHistory.get(0).getActionType().isBlockable()) {
            return WaitingBlockActionState.of(players, player, newActionHistory);
        }

        return CompletedState.of(players, player, newActionHistory);
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Arrays.asList(ChallengeActionType.values());
    }
}
