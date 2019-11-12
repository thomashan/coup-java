package com.thomashan.coup;

import com.thomashan.coup.action.Action;

import java.util.ArrayList;
import java.util.List;

public class StandardGame implements Game {
    private static final int MINIMUM_PLAYERS = 2;
    private static final int MAXIMUM_PLAYERS = 6;
    private final Players players;
    private final int numberOfPlayers;
    private final Deck deck;
    private final List<List<Action>> actionHistory;
    private final Turn turn;

    private StandardGame(int numberOfPlayers) {
        if (numberOfPlayers < MINIMUM_PLAYERS) {
            throw new IllegalArgumentException("You need at least " + MINIMUM_PLAYERS + " players");
        }

        if (numberOfPlayers > MAXIMUM_PLAYERS) {
            throw new IllegalArgumentException("Maximum of " + MAXIMUM_PLAYERS + " players");
        }

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

        this.numberOfPlayers = numberOfPlayers;
        this.actionHistory = new ArrayList<>();
        this.players = players;
        this.deck = deck;
        this.turn = Turn.create(players);
    }

    public static StandardGame create(int numberOfPlayers) {
        return new StandardGame(numberOfPlayers);
    }

    @Override
    public boolean isComplete() {
        return players.getNumberOfActivePlayers() == 1;
    }

    @Override
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public List<List<Action>> getActionHistory() {
        return actionHistory;
    }

    @Override
    public Turn getTurn() {
        return turn;
    }

    @Override
    public Players getPlayers() {
        return players;
    }

    @Override
    public Deck getDeck() {
        return deck;
    }

    @Override
    public Game action(Action action) {
        return null;
    }

    public Player getCurrentPlayer() {
        return turn.getPlayer();
    }
}
