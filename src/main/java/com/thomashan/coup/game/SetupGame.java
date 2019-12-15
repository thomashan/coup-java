package com.thomashan.coup.game;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.card.PlayerCard;
import com.thomashan.coup.card.StandardDeck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;
import com.thomashan.coup.turn.Turn;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class SetupGame extends AbstractGame {
    private SetupGame(Players players, int numberOfPlayers, Deck deck, List<List<Action>> actionHistory, Turn turn) {
        super(players, numberOfPlayers, deck, actionHistory, turn);
    }

    public static SetupGame create(Player... playersToAdd) {
        Deck deck = StandardDeck.create();
        for (Player player : playersToAdd) {
            for (PlayerCard playerCard : player.getPlayerCards().get()) {
                deck = deck.minus(playerCard.getCard());
            }
        }

        Players players = Players.create(playersToAdd);

        return new SetupGame(players, players.getNumberOfPlayers(), deck, singletonList(emptyList()), Turn.create(players, deck));
    }
}
