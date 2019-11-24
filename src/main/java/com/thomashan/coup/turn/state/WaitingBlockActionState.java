package com.thomashan.coup.turn.state;

import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockAction;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeActionType;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public final class WaitingBlockActionState implements TurnState<BlockAction> {
    private final Players players;
    private final Player player;
    private final List<Action> actionHistory;

    private WaitingBlockActionState(Players players, Player player, List<Action> actionHistory) {
        this.players = players;
        this.player = player;
        this.actionHistory = actionHistory;
    }

    public static WaitingBlockActionState of(Players players, Player player, List<Action> actionHistory) {
        return new WaitingBlockActionState(players, player, actionHistory);
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
        // FIXME: return the player who should perform the block
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
    public TurnState performAction(BlockAction action) {
        return null;
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return null;
    }
}
