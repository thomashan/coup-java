package com.thomashan.coup;

import com.thomashan.collection.immutable.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

public final class StandardPlayers implements Players {
    private final ImmutableList<Player> players;

    private StandardPlayers() {
        this.players = ImmutableList.of();
    }

    private StandardPlayers(List<Player> players) {
        this.players = ImmutableList.of(players);
    }

    private StandardPlayers(ImmutableList<Player> players, Player player) {
        ImmutableList<Player> playerList = players.plus(player);

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
        int index = players.indexOf(oldPlayer);
        ImmutableList<Player> newPlayers = players.addOrSet(index, newPlayer);

        return new StandardPlayers(newPlayers);
    }
}
