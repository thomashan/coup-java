package com.thomashan.coup;

public enum TurnAction {
    MAIN_ACTION("main action"),
    CHALLENGE_ACTION("challenge action"),
    BLOCK_ACTION("block action"),
    CHALLENGE_BLOCK("challenge block"),
    REVEAL_CHALLENGE("reveal challenge"),
    COMPLETED("completed");

    private final String description;

    TurnAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
