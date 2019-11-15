package com.thomashan.coup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class StandardPlayers implements Players {
    private final List<Player> players;

    private StandardPlayers() {
        this.players = new ArrayList<>();
    }

    private StandardPlayers(List<Player> players) {
        this.players = players;
    }

    private StandardPlayers(List<Player> players, Player player) {
        List<Player> playerList = new ArrayList<>(players);
        playerList.add(player);

        this.players = playerList;
    }

    public static Players create() {
        return new StandardPlayers();
    }

    @Override
    public int getNumberOfPlayers() {
        return players.size();
    }

    @Override
    public int getNumberOfActivePlayers() {
        return (int) players.stream()
                .filter(p -> p.isActive())
                .count();
    }

    @Override
    public List<Player> get() {
        return players;
    }

    @Override
    public List<Player> getActivePlayers() {
        return players.stream()
                .filter(p -> p.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public Players addPlayer(Player player) {
        return new StandardPlayers(players, player);
    }

    @Override
    public Players updatePlayer(Player oldPlayer, Player newPlayer) {
        List<Player> newPlayers = new ArrayList<>(players);
        int index = newPlayers.indexOf(oldPlayer);
        newPlayers.set(index, newPlayer);

        return new StandardPlayers(newPlayers);
    }
}
