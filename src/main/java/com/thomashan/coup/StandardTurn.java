package com.thomashan.coup;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.state.ActionState;

import java.util.List;

public final class StandardTurn implements Turn {
    private final int turnNumber;
    private final Players players;
    private final ActionState actionState;

    private StandardTurn(int turnNumber, Players players, ActionState actionState) {
        this.turnNumber = turnNumber;
        this.players = players;
        this.actionState = actionState;
    }

    public static StandardTurn create(Players players) {
        return new StandardTurn(0, players, ActionState.initialState(players, players.get().get(0)));
    }

    @Override
    public boolean isComplete() {
        return actionState.isComplete();
    }

    @Override
    public Turn newTurn() {
        int newTurnNumber = turnNumber + 1;

        return new StandardTurn(newTurnNumber, players, ActionState.initialState(players, getNextTurnPlayer()));
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
        return actionState.getActionablePlayers();
    }

    @Override
    public List<Action> getActionHistory() {
        return actionState.getActionHistory();
    }

    @Override
    public List<Action> getAllowableActions() {
        return actionState.getAllowableActionTypes();
    }

    @Override
    public Turn perform(Action action) {
        ActionState newActionState = actionState.perform(action);
        return new StandardTurn(turnNumber, newActionState.getPlayers(), newActionState);
    }

    private Player getNextTurnPlayer() {
        return players.get().get((turnNumber + 1) % players.getNumberOfPlayers());
    }
}
