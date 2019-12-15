package com.thomashan.coup.player;

import com.thomashan.coup.card.Card;
import com.thomashan.coup.card.PlayerCard;

import static com.thomashan.coup.card.Card.AMBASSADOR;

public class PlayerBuilder {
    private int coins = 2;
    private boolean active = true;
    Card[] cards;
    private Card card1 = AMBASSADOR;
    private Card card2 = AMBASSADOR;

    public static PlayerBuilder newBuilder() {
        return new PlayerBuilder();
    }

    public PlayerBuilder coins(int coins) {
        this.coins = coins;
        return this;
    }

    public PlayerBuilder active(boolean active) {
        this.active = active;
        return this;
    }

    public PlayerBuilder cards(Card... cards) {
        this.cards = cards;
        return this;
    }

    public PlayerBuilder card1(Card card1) {
        this.card1 = card1;
        return this;
    }

    public PlayerBuilder card2(Card card2) {
        this.card2 = card2;
        return this;
    }

    public Player build() {
        Player player;
        if (cards == null) {
            player = Player.of(card1, card2);
        } else {
            player = Player.of(cards[0], cards[1]);

            for (int i = 2; i < cards.length; i++) {
                player = player.plus(cards[i]);
            }
        }

        player = coins(player, coins);
        player = active(player, active);

        return player;
    }

    private Player active(Player playerToRevealCards, boolean active) {
        Player player = playerToRevealCards;

        if (active) {
            return player;
        }

        for (PlayerCard playerCard : player.getPlayerCards().get()) {
            player = player.revealCard(playerCard.getCard());
        }

        return player;
    }

    private Player coins(Player player, int coins) {
        Player createdPlayer = player;

        if (coins < 2) {
            createdPlayer = createdPlayer.stolenToOtherPlayer();
            if (coins == 0) {
                return createdPlayer;
            }

            return createdPlayer.income();
        } else {
            for (int i = 0; i < coins - 2; i++) {
                createdPlayer = createdPlayer.income();
            }

            return createdPlayer;
        }
    }
}
