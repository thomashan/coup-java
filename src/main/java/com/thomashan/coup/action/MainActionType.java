package com.thomashan.coup.action;

import java.util.Optional;

import static com.thomashan.coup.action.BlockActionType.BLOCK_ASSASSINATE;
import static com.thomashan.coup.action.BlockActionType.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.action.BlockActionType.BLOCK_STEAL;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public enum MainActionType implements ActionType {
    ASSASSINATE(of(3), of(10), true, of(BLOCK_ASSASSINATE), true),
    TAX(empty(), of(10), true, empty(), false),
    STEAL(empty(), of(10), true, of(BLOCK_STEAL), true),
    EXCHANGE(empty(), of(10), true, empty(), false),
    INCOME(empty(), of(10), false, empty(), false),
    FOREIGN_AID(empty(), of(10), false, of(BLOCK_FOREIGN_AID), false),
    COUP(of(7), empty(), false, empty(), true);

    private final Optional<Integer> minimumCoins;
    private final Optional<Integer> maximumCoins;
    private final boolean challengeable;
    private final Optional<BlockActionType> blockAction;
    private final boolean requiresTarget;

    MainActionType(Optional<Integer> minimumCoins,
                   Optional<Integer> maximumCoins,
                   boolean challengeable,
                   Optional<BlockActionType> blockAction,
                   boolean requiresTarget) {
        this.minimumCoins = minimumCoins;
        this.maximumCoins = maximumCoins;
        this.challengeable = challengeable;
        this.blockAction = blockAction;
        this.requiresTarget = requiresTarget;
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

    public boolean isRequiresTarget() {
        return requiresTarget;
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
