package com.thomashan.coup;

public class PlayerChallengeAction {
    private final Player player;
    private final ChallengeAction challengeAction;

    private PlayerChallengeAction(Player player, ChallengeAction challengeAction) {
        this.player = player;
        this.challengeAction = challengeAction;
    }

    public Player getPlayer() {
        return player;
    }

    public ChallengeAction getChallengeAction() {
        return challengeAction;
    }

    public static PlayerChallengeAction of(Player player, ChallengeAction challengeAction) {
        return new PlayerChallengeAction(player, challengeAction);
    }
}
