package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.Actions.ASSASSINATE;
import static com.thomashan.coup.Actions.COUP;
import static com.thomashan.coup.Actions.EXCHANGE;
import static com.thomashan.coup.Actions.INCOME;
import static com.thomashan.coup.Actions.STEAL;
import static com.thomashan.coup.Actions.TAX;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActionsTest {
    @Test
    public void testIsAllowable_ReturnsFalse_AssassinateWithLessThanThreeCoins() {
        assertFalse(ASSASSINATE.isAllowable(2));
    }

    @Test
    public void testIsAllowable_ReturnsTrue_AssassinateWithThreeCoins() {
        assertTrue(ASSASSINATE.isAllowable(3));
    }

    @Test
    public void testIsAllowable_ReturnsTrue_AssassinateWithMoreThanThreeCoinsAndLessThan10Coins() {
        assertTrue(ASSASSINATE.isAllowable(4));
    }

    @Test
    public void testIsAllowable_ReturnsTrue_AssassinateWithMoreThan10Coins() {
        assertFalse(ASSASSINATE.isAllowable(10));
    }

    @Test
    public void testIsAllowable_ReturnsTrue_TaxLessThan10Coins() {
        assertTrue(TAX.isAllowable(9));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_TaxWith10Coins() {
        assertFalse(TAX.isAllowable(10));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_TaxWithMoreThan10Coins() {
        assertFalse(TAX.isAllowable(11));
    }

    @Test
    public void testIsAllowable_ReturnsTrue_StealLessThan10Coins() {
        assertTrue(STEAL.isAllowable(9));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_StealWith10Coins() {
        assertFalse(STEAL.isAllowable(10));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_StealWithMoreThan10Coins() {
        assertFalse(STEAL.isAllowable(11));
    }

    @Test
    public void testIsAllowable_ReturnsTrue_ExchangeLessThan10Coins() {
        assertTrue(EXCHANGE.isAllowable(9));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_ExchangeWith10Coins() {
        assertFalse(EXCHANGE.isAllowable(10));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_ExchangeWithMoreThan10Coins() {
        assertFalse(EXCHANGE.isAllowable(11));
    }

    @Test
    public void testIsAllowable_ReturnsTrue_IncomeLessThan10Coins() {
        assertTrue(INCOME.isAllowable(9));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_IncomeWith10Coins() {
        assertFalse(INCOME.isAllowable(10));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_IncomeWithMoreThan10Coins() {
        assertFalse(INCOME.isAllowable(10));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_CoupeLessThanSevenCoins() {
        assertFalse(COUP.isAllowable(6));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_CoupWithSevenCoins() {
        assertTrue(COUP.isAllowable(7));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_CoupWithMoreThanSevenCoins() {
        assertTrue(COUP.isAllowable(7));
    }
}
