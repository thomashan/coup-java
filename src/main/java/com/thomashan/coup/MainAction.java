package com.thomashan.coup;

import java.util.Optional;

import static com.thomashan.coup.BlockAction.BLOCK_ASSASSINATE;
import static com.thomashan.coup.BlockAction.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.BlockAction.BLOCK_STEAL;
import static com.thomashan.coup.TurnAction.BLOCK_ACTION;
import static com.thomashan.coup.TurnAction.CHALLENGE_ACTION;
import static com.thomashan.coup.TurnAction.COMPLETED;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public enum MainAction implements Action {
    ASSASSINATE(of(3), of(10), true, of(BLOCK_ASSASSINATE)),
    TAX(empty(), of(10), true, empty()),
    STEAL(empty(), of(10), true, of(BLOCK_STEAL)),
    EXCHANGE(empty(), of(10), true, empty()),
    INCOME(empty(), of(10), false, empty()),
    FOREIGN_AID(empty(), of(10), true, of(BLOCK_FOREIGN_AID)),
    COUP(of(7), empty(), false, empty());

    private final Optional<Integer> minimumCoins;
    private final Optional<Integer> maximumCoins;
    private final boolean challengeable;
    private final Optional<BlockAction> blockAction;

    MainAction(Optional<Integer> minimumCoins,
               Optional<Integer> maximumCoins,
               boolean challengeable,
               Optional<BlockAction> blockAction) {
        this.minimumCoins = minimumCoins;
        this.maximumCoins = maximumCoins;
        this.challengeable = challengeable;
        this.blockAction = blockAction;
    }

    public Optional<Integer> getMinimumCoins() {
        return minimumCoins;
    }

    public Optional<Integer> getMaximumCoins() {
        return maximumCoins;
    }

    @Override
    public boolean isChallengeable() {
        return challengeable;
    }

    @Override
    public boolean isBlockable() {
        return blockAction.isPresent();
    }

    public TurnAction getNextTurnAction() {
        if (challengeable) {
            return CHALLENGE_ACTION;
        }

        if (isBlockable()) {
            return BLOCK_ACTION;
        }

        return COMPLETED;
    }

    public Optional<BlockAction> getBlockAction() {
        return blockAction;
    }

    public boolean isAllowable(int coins) {
        return isMinimumCoinsFulfilled(coins) && isMaximumCoinsFulfilled(coins);
    }

    private boolean isMinimumCoinsFulfilled(int coins) {
        return !getMinimumCoins().isPresent() || coins >= getMinimumCoins().get();
    }

    private boolean isMaximumCoinsFulfilled(int coins) {
        return !getMaximumCoins().isPresent() || coins < getMaximumCoins().get();
    }
}
