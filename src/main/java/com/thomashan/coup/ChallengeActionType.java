package com.thomashan.coup;

public enum ChallengeActionType implements ActionType {
    CHALLENGE,
    ALLOW;

    @Override
    public boolean isChallengeable() {
        return false;
    }

    @Override
    public boolean isBlockable() {
        return false;
    }
}
