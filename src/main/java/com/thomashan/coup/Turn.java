package com.thomashan.coup;

import java.util.List;

/**
 * Model the allowable action, challenge action, and block action for the current turn.
 */
public interface Turn {
    int getTurnNumber();

    Player getCurrentTurnPlayer();

    List<MainActionType> getAllowableActions();

    Turn attemptMainAction(MainActionType mainActionType);

    Turn attemptBlock(BlockActionType blockActionType);

    Turn challengeMainAction(List<PlayerChallengeAction> playerChallengeActions);

    Turn challengeBlock(List<PlayerChallengeAction> playerChallengeActions);

    Turn reveal();
}
