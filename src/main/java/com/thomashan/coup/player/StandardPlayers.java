package com.thomashan.coup.player;

import com.thomashan.collection.immutable.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

public final class StandardPlayers implements Players {
    private final transient ImmutableList<Player> players;

    private StandardPlayers() {
        this.players = ImmutableList.of();
    }

    private StandardPlayers(List<Player> players) {
        this.players = ImmutableList.of(players);
    }

    private StandardPlayers(ImmutableList<Player> players, Player player) {
        this.players = players.plus(player);
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
    public Player get(int index) {
        return players.get(index);
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
        if(oldPlayer.equals(newPlayer)) {
            return this;
        }

        int index = players.indexOf(oldPlayer);
        if (index < 0) {
            throw new IllegalArgumentException("The player " + oldPlayer.toString() + " doesn't exist");
        }
        ImmutableList<Player> newPlayers = players.addOrSet(index, newPlayer);

        return new StandardPlayers(newPlayers);
    }

    @Override
    public Players others(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException("The player to omit doesn't exist");
        }

        return new StandardPlayers(players.minus(player));
    }
}
