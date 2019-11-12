package com.thomashan.coup;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.action.state.ActionState;

import java.util.List;

public class StandardTurn implements Turn {
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
    public int getTurnNumber() {
        return turnNumber;
    }

    @Override
    public Player getPlayer() {
        return actionState.getPlayer();
    }

    @Override
    public List<Player> getActionablePlayers() {
        return actionState.getActionablePlayers();
    }

    @Override
    public List<Action> getAllowableActions() {
        return actionState.getAllowableActionTypes();
    }

    @Override
    public Turn perform(Action action) {
        return new StandardTurn(turnNumber, players, actionState.perform(action));
    }
}
