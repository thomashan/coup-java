package com.thomashan.coup;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.turn.Turn;

import java.util.List;

public interface Game {
    boolean isComplete();

    int getNumberOfPlayers();

    List<List<Action>> getActionHistory();

    Turn getTurn();

    Players getPlayers();

    Deck getDeck();

    Game action(Action action);

    Player getCurrentPlayer();

    static Game create(int numberOfPlayers) {
        return StandardGame.create(numberOfPlayers);
    }
}
