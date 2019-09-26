package com.thomashan.coup;

import java.util.List;

/**
 * Model the allowable action, challenge action, and block action for the current turn.
 */
public interface Turn {
    int getTurnNumber();

    Player getCurrentTurnPlayer();

    List<MainAction> getAllowableActions();

    Turn attemptAction(MainAction mainAction);

    Turn attemptBlockAction(BlockAction blockAction);

    Turn challengeAction(List<PlayerChallengeAction> playerChallengeActions);
}
