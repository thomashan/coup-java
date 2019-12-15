package com.thomashan.coup.turn;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;
import com.thomashan.coup.turn.state.TurnState;

import java.util.List;

public final class StandardTurn implements Turn {
    private final int turnNumber;
    private final transient Players players;
    private final TurnState turnState;
    private final Deck deck;

    private StandardTurn(int turnNumber, Players players, Deck deck, TurnState turnState) {
        this.turnNumber = turnNumber;
        this.players = players;
        this.deck = deck;
        this.turnState = turnState;
    }

    public static StandardTurn create(Players players, Deck deck) {
        return new StandardTurn(0, players, deck, TurnState.initialState(players, players.get(0), deck));
    }

    @Override
    public boolean isComplete() {
        return turnState.isComplete();
    }

    @Override
    public Turn newTurn() {
        if (isComplete()) {
            int newTurnNumber = turnNumber + 1;

            return new StandardTurn(newTurnNumber, getPlayers(), getDeck(), TurnState.initialState(players, getNextTurnPlayer(), getDeck()));
        }

        throw new IllegalStateException("Can't generate a new turn when the turn state is not complete");
    }

    @Override
    public int getTurnNumber() {
        return turnNumber;
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
        return players.get(turnNumber % players.getNumberOfPlayers());
    }

    @Override
    public List<Action> getActionHistory() {
        return turnState.getActionHistory();
    }

    @Override
    public List<Action> getAllowableActions() {
        return turnState.getAllowableActions();
    }

    @Override
    public Turn perform(Action action) {
        TurnState newTurnState = turnState.perform(action);
        return new StandardTurn(turnNumber, newTurnState.getPlayers(), newTurnState.getDeck(), newTurnState);
    }

    private Player getNextTurnPlayer() {
        return players.get((turnNumber + 1) % players.getNumberOfPlayers());
    }
}
