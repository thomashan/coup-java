package com.thomashan.coup;

import static com.thomashan.coup.Card.AMBASSADOR;

public class PlayerBuilder {
    public static Player build(boolean active) {
        Player player = build();
        if (active) {
            return player;
        }

        return revealAllCards(player);
    }

    private static Player revealAllCards(Player playerToRevealCards) {
        Player player = playerToRevealCards;

        for (PlayerCard playerCard : player.getPlayerCards().get()) {
            player = player.revealCard(playerCard);
        }

        return player;
    }

    public static Player build() {
        return build(2);
    }

    public static Player build(int coins) {
        Player player = createPlayerWithNumberOfCoins(coins);

        assert player.getCoins() == coins;

        return player;
    }

    private static Player createPlayerWithNumberOfCoins(int coins) {
        Player player = Player.of(AMBASSADOR, AMBASSADOR);

        if (coins < 2) {
            player = player.stolenToOtherPlayer();
            if (coins == 0) {
                return player;
            }

            return player.income();
        } else {
            for (int i = 0; i < coins - 2; i++) {
                player = player.income();
            }

            return player;
        }
    }
}
