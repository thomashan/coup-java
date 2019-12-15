package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

public final class CompletedState implements TurnState {
    private final Players players;
    private final Player player;
    private final Deck deck;
    private final MainAction mainAction;
    private final List<Action> actionHistory;
    private final Optional<Player> target;

    private CompletedState(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target) {
        this.players = players;
        this.player = player;
        this.deck = deck;
        this.mainAction = mainAction;
        this.actionHistory = actionHistory;

        if (target != null) {
            this.target = Optional.of(target);
        } else {
            this.target = empty();
        }
    }

    public static CompletedState of(Players players, Player player, Deck deck, MainAction mainAction, List<Action> actionHistory, Player target) {
        return new CompletedState(players, player, deck, mainAction, actionHistory, target);
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
        return deck;
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
    public Optional<Player> getTarget() {
        return target;
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public boolean isCheckAllowableActions() {
        return false;
    }

    @Override
    public TurnState performAction(Action action) {
        throw new UnsupportedOperationException("Can't perform any more action");
    }

    @Override
    public List<Action> getAllowableActions() {
        return emptyList();
    }
}
