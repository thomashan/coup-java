package com.thomashan.coup;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final int MINIMUM_PLAYERS = 2;
    private static final int MAXIMUM_PLAYERS = 6;
    private final Players players;
    private final int numberOfPlayers;
    private final Deck deck;
    private final List<MainAction> actionHistory;

    private Game(Deck deck, Players players) {
        this.numberOfPlayers = 0;
        this.deck = deck;
        this.players = players;
        this.actionHistory = null;
    }

    private Game(int numberOfPlayers) {
        if (numberOfPlayers < MINIMUM_PLAYERS) {
            throw new IllegalArgumentException("You need at least " + MINIMUM_PLAYERS + " players");
        }

        if (numberOfPlayers > MAXIMUM_PLAYERS) {
            throw new IllegalArgumentException("Maximum of " + MAXIMUM_PLAYERS + " players");
        }

        Deck initialDeck = StandardDeck.create();
        Players players = StandardPlayers.create();
        Game game = new Game(initialDeck, players);

        for (int i = 0; i < numberOfPlayers; i++) {
            game = createPlayer(game.getDeck(), game.getPlayers());
        }

        this.numberOfPlayers = numberOfPlayers;
        this.actionHistory = new ArrayList<>();
        this.players = game.getPlayers();
        this.deck = game.getDeck();
    }

    private Game createPlayer(Deck deck, Players players) {
        DrawnCard drawnCard1 = deck.draw();
        Deck newDeck = drawnCard1.getDeck();
        Card card1 = drawnCard1.getCard();

        DrawnCard drawnCard2 = newDeck.draw();
        newDeck = drawnCard2.getDeck();
        Card card2 = drawnCard2.getCard();

        return new Game(newDeck, players.addPlayer(Player.of(card1, card2)));
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<MainAction> getActionHistory() {
        return actionHistory;
    }

    public int getTurn() {
        return actionHistory.size() % numberOfPlayers;
    }

    public Players getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public static Game create(int numberOfPlayers) {
        return new Game(numberOfPlayers);
    }
}
