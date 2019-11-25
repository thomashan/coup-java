package com.thomashan.coup.turn;

import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;
import com.thomashan.coup.turn.state.TurnState;

import java.util.List;

public final class StandardTurn implements Turn {
    private final int turnNumber;
    private final transient Players players;
    private final TurnState turnState;

    private StandardTurn(int turnNumber, Players players, TurnState turnState) {
        this.turnNumber = turnNumber;
        this.players = players;
        this.turnState = turnState;
    }

    public static StandardTurn create(Players players) {
        return new StandardTurn(0, players, TurnState.initialState(players, players.get().get(0)));
    }

    @Override
    public boolean isComplete() {
        return turnState.isComplete();
    }

    @Override
    public Turn newTurn() {
        int newTurnNumber = turnNumber + 1;

        return new StandardTurn(newTurnNumber, players, TurnState.initialState(players, getNextTurnPlayer()));
    }

    @Override
    public int getTurnNumber() {
        return turnNumber;
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
        return players.get().get(turnNumber % players.getNumberOfPlayers());
    }

    @Override
    public List<Player> getActionablePlayers() {
        return turnState.getActionablePlayers();
    }

    @Override
    public List<Action> getActionHistory() {
        return turnState.getActionHistory();
    }

    @Override
    public List<Action> getAllowableActions() {
        return turnState.getAllowableActionTypes();
    }

    @Override
    public Turn perform(Action action) {
        TurnState newTurnState = turnState.perform(action);
        return new StandardTurn(turnNumber, newTurnState.getPlayers(), newTurnState);
    }

    private Player getNextTurnPlayer() {
        return players.get().get((turnNumber + 1) % players.getNumberOfPlayers());
    }
}
