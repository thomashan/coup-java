package com.thomashan.coup.turn.state;

import com.thomashan.coup.action.MainAction;
import com.thomashan.coup.card.Deck;
import com.thomashan.coup.card.StandardDeck;
import com.thomashan.coup.player.Player;
import com.thomashan.coup.player.PlayerBuilder;
import com.thomashan.coup.player.Players;
import org.junit.jupiter.api.BeforeEach;

import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.INCOME;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;

public abstract class TurnStateTestCase {
    private Player player;
    private Players players;
    private Deck deck;

    @BeforeEach
    protected void setUpDefaultPlayer() {
        player = newBuilder().build();
        players = Players.create(player);
        deck = StandardDeck.create();
    }

    protected void setUpPlayer(PlayerBuilder playerBuilder) {
        player = playerBuilder.build();
        players = Players.create(player);
    }

    protected Players getPlayers() {
        return players;
    }

    protected Player getPlayer() {
        return player;
    }

    protected Deck getDeck() {
        return deck;
    }

    protected void setPlayers(Players players) {
        this.players = players;
    }

    protected MainAction anyMainMethod() {
        return MainAction.of(player, EXCHANGE);
    }

    protected MainAction anyChallengeableMainAction() {
        return anyMainMethod();
    }

    protected MainAction anyNonChallengeableMainAction() {
        return MainAction.of(player, INCOME);
    }
}
