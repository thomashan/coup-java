package com.thomashan.coup.player;

import java.util.List;

public interface Players {
    static Players create(Player... players) {
        Players newPlayers = StandardPlayers.create();

        for (Player player : players) {
            newPlayers = newPlayers.addPlayer(player);
        }

        return newPlayers;
    }

    int getNumberOfPlayers();

    int getNumberOfActivePlayers();

    List<Player> get();

    Player get(int index);

    List<Player> getActivePlayers();

    Players addPlayer(Player player);

    Players updatePlayer(Player oldPlayer, Player newPlayer);

    /**
     * Get all players other than the input player
     *
     * @param player player to omit
     * @return Players other than the player
     */
    Players others(Player player);
}
