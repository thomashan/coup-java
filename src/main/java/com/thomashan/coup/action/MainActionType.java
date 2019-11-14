package com.thomashan.coup.action;

import com.thomashan.coup.TurnAction;

import java.util.Optional;

import static com.thomashan.coup.TurnAction.BLOCK_ACTION;
import static com.thomashan.coup.TurnAction.CHALLENGE_ACTION;
import static com.thomashan.coup.TurnAction.COMPLETED;
import static com.thomashan.coup.action.BlockActionType.BLOCK_ASSASSINATE;
import static com.thomashan.coup.action.BlockActionType.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.action.BlockActionType.BLOCK_STEAL;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public enum MainActionType implements ActionType {
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
    private final Optional<BlockActionType> blockAction;

    MainActionType(Optional<Integer> minimumCoins,
                   Optional<Integer> maximumCoins,
                   boolean challengeable,
                   Optional<BlockActionType> blockAction) {
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

    public Optional<BlockActionType> getBlockAction() {
        return blockAction;
    }

    public boolean isAllowable(int coins) {
        return isMinimumCoinsFulfilled(coins) && isMaximumCoinsFulfilled(coins);
    }

    private boolean isMinimumCoinsFulfilled(int coins) {
        return minimumCoins.map(c -> coins >= c).orElse(true);
    }

    private boolean isMaximumCoinsFulfilled(int coins) {
        return maximumCoins.map(c -> coins < c).orElse(true);
    }
}
