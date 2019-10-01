package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.MainActionType.ASSASSINATE;
import static com.thomashan.coup.MainActionType.COUP;
import static com.thomashan.coup.MainActionType.EXCHANGE;
import static com.thomashan.coup.MainActionType.FOREIGN_AID;
import static com.thomashan.coup.MainActionType.INCOME;
import static com.thomashan.coup.MainActionType.STEAL;
import static com.thomashan.coup.MainActionType.TAX;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainActionTypeTest {
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
        assertFalse(INCOME.isAllowable(11));
    }

    @Test
    public void testIsAllowable_ReturnsTrue_ForeignAidLessThan10Coins() {
        assertTrue(FOREIGN_AID.isAllowable(9));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_ForeignAidWith10Coins() {
        assertFalse(FOREIGN_AID.isAllowable(10));
    }

    @Test
    public void testIsAllowable_ReturnsFalse_ForeignAidWithMoreThan10Coins() {
        assertFalse(FOREIGN_AID.isAllowable(11));
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
        assertTrue(COUP.isAllowable(8));
    }
}
