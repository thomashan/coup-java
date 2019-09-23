package com.thomashan.coup;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public enum Actions {
    ASSASSINATE(of(3), of(10)),
    TAX(empty(), of(10)),
    STEAL(empty(), of(10)),
    EXCHANGE(empty(), of(10)),
    INCOME(empty(), of(10)),
    COUP(of(7), empty());

    private final Optional<Integer> minimumCoins;
    private final Optional<Integer> maximumCoins;

    Actions(Optional<Integer> minimumCoins, Optional<Integer> maximumCoins) {
        this.minimumCoins = minimumCoins;
        this.maximumCoins = maximumCoins;
    }

    public Optional<Integer> getMinimumCoins() {
        return minimumCoins;
    }

    public Optional<Integer> getMaximumCoins() {
        return maximumCoins;
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
