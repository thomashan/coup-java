package com.thomashan.coup.action;

import com.thomashan.coup.player.Player;

public final class PlayerChallengeAction {
    private final Player player;
    private final ChallengeActionType challengeActionType;

    private PlayerChallengeAction(Player player, ChallengeActionType challengeActionType) {
        this.player = player;
        this.challengeActionType = challengeActionType;
    }

    public static PlayerChallengeAction of(Player player, ChallengeActionType challengeActionType) {
        return new PlayerChallengeAction(player, challengeActionType);
    }

    public Player getPlayer() {
        return player;
    }

    public ChallengeActionType getChallengeActionType() {
        return challengeActionType;
    }
}
