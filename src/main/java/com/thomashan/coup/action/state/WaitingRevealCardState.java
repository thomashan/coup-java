package com.thomashan.coup.action.state;

import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.ActionType;
import com.thomashan.coup.action.BlockActionType;
import com.thomashan.coup.action.ChallengeActionType;
import com.thomashan.coup.action.RevealCardAction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

public final class WaitingRevealCardState implements ActionState<RevealCardAction> {
    private final Players players;
    private final Player player;
    private final List<Action> actionHistory;
    private final Optional<Player> target;
    private final Player revealer;

    private WaitingRevealCardState(Players players, Player player, List<Action> actionHistory, Player revealer) {
        this.players = players;
        this.player = player;
        this.actionHistory = actionHistory;
        this.revealer = revealer;
        this.target = empty();
    }

    private WaitingRevealCardState(Players players, Player player, List<Action> actionHistory, Player revealer, Player target) {
        this.players = players;
        this.player = player;
        this.actionHistory = actionHistory;
        this.revealer = revealer;

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    static WaitingRevealCardState of(Players players, Player player, List<Action> actionHistory, Player revealer) {
        return new WaitingRevealCardState(players, player, actionHistory, revealer);
    }

    static WaitingRevealCardState of(Players players, Player player, List<Action> actionHistory, Player revealer, Player target) {
        return new WaitingRevealCardState(players, player, actionHistory, revealer, target);
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
        return Collections.singletonList(revealer);
    }

    @Override
    public Optional<Player> getTarget() {
        return target;
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
    public CompletedState performAction(RevealCardAction action) {
        return null;
    }

    @Override
    public List<ActionType> getAllowableActionTypes() {
        return null;
    }
}
