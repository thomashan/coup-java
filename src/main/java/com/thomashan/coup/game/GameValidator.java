package com.thomashan.coup.game;

public class GameValidator {
    private GameValidator() {
        throw new AssertionError();
    }

    public static void checkMinimumPlayers(int minimumNumberOfPlayers, int numberOfPlayers) {
        if (numberOfPlayers < minimumNumberOfPlayers) {
            throw new IllegalArgumentException("You need at least " + minimumNumberOfPlayers + " players");
        }
    }

    public static void checkMaximumPlayers(int maxNumberOfPlayers, int numberOfPlayers) {
        if (numberOfPlayers > maxNumberOfPlayers) {
            throw new IllegalArgumentException("Maximum of " + maxNumberOfPlayers + " players");
        }
    }
}
