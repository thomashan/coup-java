package com.thomashan.coup;

public enum ChallengeActionType implements Action {
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
