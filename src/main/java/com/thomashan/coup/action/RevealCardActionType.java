package com.thomashan.coup.action;

public enum RevealCardActionType implements ActionType {
    DISTINCT_OUT_OF_2,
    ANY_OUT_OF_2,
    OUT_OF_1;

    @Override
    public boolean isChallengeable() {
        return false;
    }

    @Override
    public boolean isBlockable() {
        return false;
    }
}
