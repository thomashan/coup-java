package com.thomashan.coup.game;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;
import com.thomashan.coup.turn.Turn;

import java.util.List;

import static com.thomashan.coup.game.GameValidator.checkMaximumPlayers;
import static com.thomashan.coup.game.GameValidator.checkMinimumPlayers;

abstract class AbstractGame implements Game {
    private static final int MINIMUM_PLAYERS = 2;
    private static final int MAXIMUM_PLAYERS = 6;
    private final Players players;
    private final int numberOfPlayers;
    private final Deck deck;
    private final ImmutableList<List<Action>> actionHistory;
    private final Turn turn;

    protected AbstractGame(Players players, int numberOfPlayers, Deck deck, List<List<Action>> actionHistory, Turn turn) {
        checkMinimumPlayers(MINIMUM_PLAYERS, numberOfPlayers);
        checkMaximumPlayers(MAXIMUM_PLAYERS, numberOfPlayers);

        this.players = players;
        this.numberOfPlayers = numberOfPlayers;
        this.deck = deck;
        this.actionHistory = ImmutableList.of(actionHistory);
        this.turn = turn;
    }

    @Override
    public boolean isComplete() {
        return players.getNumberOfActivePlayers() == 1;
    }

    @Override
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public List<List<Action>> getActionHistory() {
        return actionHistory;
    }

    @Override
    public Turn getTurn() {
        return turn;
    }

    @Override
    public Players getPlayers() {
        return players;
    }

    @Override
    public Deck getDeck() {
        return deck;
    }

    @Override
    public Player getCurrentPlayer() {
        return turn.getPlayer();
    }

    @Override
    public List<Action> getAllowableActions() {
        return turn.getAllowableActions();
    }

    @Override
    public Game action(Action action) {
        Turn newTurn = getTurn().perform(action);
        ImmutableList<List<Action>> newActionHistory = ImmutableList.of(getActionHistory()).addOrSet(newTurn.getTurnNumber(), newTurn.getActionHistory());

        if (newTurn.isComplete()) {
            newTurn = newTurn.newTurn();
            newActionHistory = ImmutableList.of(newActionHistory.addOrSet(newTurn.getTurnNumber(), newTurn.getActionHistory()));
        }

        return StandardGame.of(newTurn.getPlayers(), getNumberOfPlayers(), getDeck(), newActionHistory, newTurn);
    }
}
