package com.thomashan.coup.turn;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;

import java.util.List;

/**
 * Model the allowable action, challenge action, and block action for the current turn.
 */
public interface Turn {
    static Turn create(Players players, Deck deck) {
        return StandardTurn.create(players, deck);
    }

    boolean isComplete();

    Turn newTurn();

    int getTurnNumber();

    Deck getDeck();

    Players getPlayers();

    Player getPlayer();

    List<Action> getActionHistory();

    List<Action> getAllowableActions();

    Turn perform(Action action);
}
