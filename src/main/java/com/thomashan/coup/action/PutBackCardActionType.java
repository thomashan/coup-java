package com.thomashan.coup.action;

public enum PutBackCardActionType implements ActionType {
    PUT_BACK,
    SELECT;

    @Override
    public boolean isChallengeable() {
        return false;
    }

    @Override
    public boolean isBlockable() {
        return false;
    }
}
