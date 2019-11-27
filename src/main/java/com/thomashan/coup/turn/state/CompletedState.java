package com.thomashan.coup.turn.state;

import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeActionType;
import com.thomashan.coup.action.MainAction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public final class CompletedState implements TurnState {
    private final Players players;
    private final Player player;
    private final MainAction mainAction;
    private final List<Action> actionHistory;

    private CompletedState(Players players, Player player, MainAction mainAction, List<Action> actionHistory) {
        this.players = players;
        this.player = player;
        this.mainAction = mainAction;
        this.actionHistory = actionHistory;
    }

    public static CompletedState of(Players players, Player player, MainAction mainAction, List<Action> actionHistory) {
        return new CompletedState(players, player, mainAction, actionHistory);
    }

    @Override
    public MainAction getMainAction() {
        return mainAction;
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
        return Collections.emptyList();
    }

    @Override
    public Optional<Player> getTarget() {
        // FIXME: should carry target from previous states
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
        return true;
    }

    @Override
    public TurnState performAction(Action action) {
        throw new UnsupportedOperationException("Can't perform any more action");
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return Collections.emptyList();
    }


}
