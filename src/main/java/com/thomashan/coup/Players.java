package com.thomashan.coup;

import java.util.List;

public interface Players {
    int getNumberOfPlayers();

    List<Player> getPlayers();

    Players addPlayer(Player player);
}
