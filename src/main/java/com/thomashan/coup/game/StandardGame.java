package com.thomashan.coup.game;

import com.thomashan.coup.action.Action;
import com.thomashan.coup.card.Card;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.card.DrawnCard;
import com.thomashan.coup.card.StandardDeck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.Players;
import com.thomashan.coup.player.StandardPlayers;
import com.thomashan.coup.turn.Turn;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public final class StandardGame extends AbstractGame {
    private StandardGame(Players players, int numberOfPlayers, Deck deck, List<List<Action>> actionHistory, Turn turn) {
        super(players, numberOfPlayers, deck, actionHistory, turn);
    }

    public static StandardGame create(int numberOfPlayers) {
        Deck deck = StandardDeck.create();
        Players players = StandardPlayers.create();

        for (int i = 0; i < numberOfPlayers; i++) {
            DrawnCard drawnCard1 = deck.draw();
            Deck newDeck = drawnCard1.getDeck();
            Card card1 = drawnCard1.getCard();

            DrawnCard drawnCard2 = newDeck.draw();
            newDeck = drawnCard2.getDeck();
            Card card2 = drawnCard2.getCard();

            players = players.addPlayer(Player.of(card1, card2));
            deck = newDeck;
        }

        return new StandardGame(players, numberOfPlayers, deck, singletonList(emptyList()), Turn.create(players, deck));
    }

    static StandardGame of(Players players, int numberOfPlayers, Deck deck, List<List<Action>> actionHistory, Turn turn) {
        return new StandardGame(players, numberOfPlayers, deck, actionHistory, turn);
    }
}
