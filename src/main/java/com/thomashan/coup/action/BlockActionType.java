package com.thomashan.coup.action;

public enum BlockActionType implements ActionType {
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
