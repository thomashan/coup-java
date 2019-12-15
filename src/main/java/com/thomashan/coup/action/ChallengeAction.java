package com.thomashan.coup.action;

import com.thomashan.coup.player.Player;
import lombok.EqualsAndHashCode;

import java.util.Optional;

@EqualsAndHashCode
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

    @Override
    public boolean isCheckForActivePlayer() {
        return true;
    }

    public ChallengeActionType getChallengeActionType() {
        return challengeActionType;
    }
}
