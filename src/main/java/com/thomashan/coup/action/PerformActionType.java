package com.thomashan.coup.action;

public enum PerformActionType implements ActionType {
    TAKE_THREE_COINS;

    @Override
    public boolean isChallengeable() {
        return false;
    }

    @Override
    public boolean isBlockable() {
        return false;
    }
}
