package com.thomashan.coup.action;

public enum RevealCardActionType implements ActionType {
    REVEAL;

    @Override
    public boolean isChallengeable() {
        return false;
    }

    @Override
    public boolean isBlockable() {
        return false;
    }
}
