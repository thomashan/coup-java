package com.thomashan.coup.game;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;
import com.thomashan.coup.turn.Turn;

import java.util.List;

public interface Game {
    static Game create(int numberOfPlayers) {
        return StandardGame.create(numberOfPlayers);
    }

    boolean isComplete();

    int getNumberOfPlayers();

    List<List<Action>> getActionHistory();

    Turn getTurn();

    Players getPlayers();

    Deck getDeck();

    Game action(Action action);

    Player getCurrentPlayer();

    List<Action> getAllowableActions();
}
