package com.thomashan.coup;

import com.thomashan.coup.action.Action;

import java.util.List;

public interface Game {
    boolean isComplete();

    int getNumberOfPlayers();

    List<List<Action>> getActionHistory();

    Turn getTurn();

    Players getPlayers();

    Deck getDeck();

    Game action(Action action);

    static Game create(int numberOfPlayers) {
        return StandardGame.create(numberOfPlayers);
    }
}
