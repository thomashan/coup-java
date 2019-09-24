package com.thomashan.coup;

import java.util.ArrayList;
import java.util.List;

public class StandardPlayers implements Players {
    private final List<Player> players;

    private StandardPlayers() {
        this.players = new ArrayList<>();
    }

    private StandardPlayers(List<Player> players, Player player) {
        List playerList = new ArrayList<>(players);
        playerList.add(player);

        this.players = playerList;
    }

    @Override
    public int getNumberOfPlayers() {
        return players.size();
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Players addPlayer(Player player) {
        return new StandardPlayers(players, player);
    }

    public static Players create() {
        return new StandardPlayers();
    }
}
