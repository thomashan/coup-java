package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.Optional;

public final class ChallengeAction implements Action<ChallengeActionType> {
    private final Player player;
    private final ChallengeActionType challengeActionType;

    private ChallengeAction(Player player, ChallengeActionType challengeActionType) {
        this.player = player;
        this.challengeActionType = challengeActionType;
    }

    public static ChallengeAction of(Player player, ChallengeActionType challengeActionType) {
        return new ChallengeAction(player, challengeActionType);
    }

    @Override
    public Class<ChallengeActionType> getActionTypeClass() {
        return ChallengeActionType.class;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public ChallengeActionType getActionType() {
        return challengeActionType;
    }

    @Override
    public Optional<Player> getTarget() {
        return Optional.empty();
    }

    public ChallengeActionType getChallengeActionType() {
        return challengeActionType;
    }
}
