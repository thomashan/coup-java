package com.thomashan.coup.turn;

import com.thomashan.coup.Deck;
import com.thomashan.coup.Player;
import com.thomashan.coup.Players;
import com.thomashan.coup.action.Action;

import java.util.List;

/**
 * Model the allowable action, challenge action, and block action for the current turn.
 */
public interface Turn {
    boolean isComplete();

    Turn newTurn();

    int getTurnNumber();

    Deck getDeck();

    Players getPlayers();

    Player getPlayer();

    List<Player> getActionablePlayers();

    List<Action> getActionHistory();

    List<Action> getAllowableActions();

    Turn perform(Action action);

    static Turn create(Players players) {
        return StandardTurn.create(players);
    }
}
