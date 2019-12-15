package com.thomashan.coup.action;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.BlockActionType.BLOCK_ASSASSINATE;
import static com.thomashan.coup.action.BlockActionType.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.action.BlockActionType.BLOCK_STEAL;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.card.Card.ASSASSIN;
import static com.thomashan.coup.card.Card.CAPTAIN;
import static com.thomashan.coup.card.Card.CONTESSA;
import static com.thomashan.coup.card.Card.DUKE;
import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActionDetectorTest {
    @Test
    public void testIsBluff_ReturnsFalse_Assassinate_HasAssassin() {
        assertFalse(ActionDetector.isBluff(ASSASSINATE, singleton(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_Assassinate_DoesNotHaveAssassin() {
        assertTrue(ActionDetector.isBluff(ASSASSINATE, singleton(AMBASSADOR)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_Exchange_HasAmbassador() {
        assertFalse(ActionDetector.isBluff(EXCHANGE, singleton(AMBASSADOR)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_Exchange_DoesNotHaveAmbassador() {
        assertTrue(ActionDetector.isBluff(EXCHANGE, singleton(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_Steal_HasCaptain() {
        assertFalse(ActionDetector.isBluff(STEAL, singleton(CAPTAIN)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_Steal_DoesNotHaveCaptain() {
        assertTrue(ActionDetector.isBluff(STEAL, singleton(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_Tax_HasDuke() {
        assertFalse(ActionDetector.isBluff(TAX, singleton(DUKE)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_Tax_DoesNotHaveDuke() {
        assertTrue(ActionDetector.isBluff(TAX, singleton(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_BlockAssassinate_HasContessa() {
        assertFalse(ActionDetector.isBluff(BLOCK_ASSASSINATE, singleton(CONTESSA)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_BlockAssassinate_DoesNotHaveContessa() {
        assertTrue(ActionDetector.isBluff(BLOCK_ASSASSINATE, singleton(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_BlockSteal_HasCaptain() {
        assertFalse(ActionDetector.isBluff(BLOCK_STEAL, singleton(CAPTAIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_BlockSteal_HasAmbassador() {
        assertFalse(ActionDetector.isBluff(BLOCK_STEAL, singleton(AMBASSADOR)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_BlockAssassinate_DoesNotHaveCaptainOrAmbassador() {
        assertTrue(ActionDetector.isBluff(BLOCK_STEAL, singleton(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_BlockForeignAid_HasDuke() {
        assertFalse(ActionDetector.isBluff(BLOCK_FOREIGN_AID, singleton(DUKE)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_BlockForeignAid_DoesNotHaveDuke() {
        assertTrue(ActionDetector.isBluff(BLOCK_FOREIGN_AID, singleton(ASSASSIN)));
    }
}
