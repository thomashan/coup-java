package com.thomashan.coup;

import java.util.List;

/**
 * Model the allowable action, challenge action, and block action for the current turn.
 */
public interface Turn {
    int getTurnNumber();

    Player getCurrentTurnPlayer();

    List<MainAction> getAllowableActions();

    Turn attemptMainAction(MainAction mainAction);

    Turn attemptBlock(BlockAction blockAction);

    Turn challengeMainAction(List<PlayerChallengeAction> playerChallengeActions);

    Turn challengeBlock(List<PlayerChallengeAction> playerChallengeActions);

    Turn reveal();
}
