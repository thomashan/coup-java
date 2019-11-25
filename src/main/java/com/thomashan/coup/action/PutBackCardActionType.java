package com.thomashan.coup.action;

public enum PutBackCardActionType implements ActionType {
    DISTINCT_TWO,
    SAME_TWO;

    @Override
    public boolean isChallengeable() {
        return false;
    }

    @Override
    public boolean isBlockable() {
        return false;
    }
}
