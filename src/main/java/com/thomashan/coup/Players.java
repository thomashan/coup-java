package com.thomashan.coup;

import java.util.List;

public interface Players {
    int getNumberOfPlayers();

    int getNumberOfActivePlayers();

    List<Player> get();

    List<Player> getActivePlayers();

    Players addPlayer(Player player);

    Players updatePlayer(Player oldPlayer, Player newPlayer);

    static Players create(Player... players) {
        Players newPlayers = StandardPlayers.create();

        for (Player player : players) {
            newPlayers = newPlayers.addPlayer(player);
        }

        return newPlayers;
    }
}
