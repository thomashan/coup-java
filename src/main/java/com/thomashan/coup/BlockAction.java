package com.thomashan.coup;

public enum BlockAction implements Action {
    BLOCK_ASSASSINATE,
    BLOCK_STEAL,
    BLOCK_FOREIGN_AID;

    @Override
    public boolean isChallengeable() {
        return false;
    }

    @Override
    public boolean isBlockable() {
        return false;
    }
}
