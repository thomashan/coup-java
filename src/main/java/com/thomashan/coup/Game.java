package com.thomashan.coup;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final int numberOfPlayers;
    private final Deck deck;
    private final List<Action> actionHistory;

    private Game(int numberOfPlayers) {
        Deck initialDeck = StandardDeck.create();
        List<Player> playerList = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++) {
            DrawnCard drawnCard1 = initialDeck.draw();
            initialDeck = drawnCard1.getDeck();
            Card card1 = drawnCard1.getCard();

            DrawnCard drawnCard2 = initialDeck.draw();
            initialDeck = drawnCard2.getDeck();
            Card card2 = drawnCard2.getCard();

            playerList.add(Player.of(PlayerCard.of(card1), PlayerCard.of(card2)));
        }

        this.players = playerList;
        this.numberOfPlayers = numberOfPlayers;
        this.deck = initialDeck;
        this.actionHistory = new ArrayList<>();
    }

    public int getTurn() {
        return actionHistory.size() % numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public static Game create(int numberOfPlayers) {
        return new Game(numberOfPlayers);
    }
}
