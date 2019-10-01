package com.thomashan.coup;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.BlockActionType.BLOCK_ASSASSINATE;
import static com.thomashan.coup.BlockActionType.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.BlockActionType.BLOCK_STEAL;
import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static com.thomashan.coup.Card.CAPTAIN;
import static com.thomashan.coup.Card.CONTESSA;
import static com.thomashan.coup.Card.DUKE;
import static com.thomashan.coup.MainActionType.ASSASSINATE;
import static com.thomashan.coup.MainActionType.EXCHANGE;
import static com.thomashan.coup.MainActionType.STEAL;
import static com.thomashan.coup.MainActionType.TAX;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainActionDetectorTest {
    @Test
    public void testIsBluff_ReturnsFalse_Assassinate_HasAssassin() {
        assertFalse(ActionDetector.isBluff(ASSASSINATE, singletonList(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_Assassinate_DoesNotHaveAssassin() {
        assertTrue(ActionDetector.isBluff(ASSASSINATE, singletonList(AMBASSADOR)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_Exchange_HasAmbassador() {
        assertFalse(ActionDetector.isBluff(EXCHANGE, singletonList(AMBASSADOR)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_Exchange_DoesNotHaveAmbassador() {
        assertTrue(ActionDetector.isBluff(EXCHANGE, singletonList(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_Steal_HasCaptain() {
        assertFalse(ActionDetector.isBluff(STEAL, singletonList(CAPTAIN)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_Steal_DoesNotHaveCaptain() {
        assertTrue(ActionDetector.isBluff(STEAL, singletonList(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_Tax_HasDuke() {
        assertFalse(ActionDetector.isBluff(TAX, singletonList(DUKE)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_Tax_DoesNotHaveDuke() {
        assertTrue(ActionDetector.isBluff(TAX, singletonList(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_BlockAssassinate_HasContessa() {
        assertFalse(ActionDetector.isBluff(BLOCK_ASSASSINATE, singletonList(CONTESSA)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_BlockAssassinate_DoesNotHaveContessa() {
        assertTrue(ActionDetector.isBluff(BLOCK_ASSASSINATE, singletonList(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_BlockSteal_HasCaptain() {
        assertFalse(ActionDetector.isBluff(BLOCK_STEAL, singletonList(CAPTAIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_BlockSteal_HasAmbassador() {
        assertFalse(ActionDetector.isBluff(BLOCK_STEAL, singletonList(AMBASSADOR)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_BlockAssassinate_DoesNotHaveCaptainOrAmbassador() {
        assertTrue(ActionDetector.isBluff(BLOCK_STEAL, singletonList(ASSASSIN)));
    }

    @Test
    public void testIsBluff_ReturnsFalse_BlockForeignAid_HasDuke() {
        assertFalse(ActionDetector.isBluff(BLOCK_FOREIGN_AID, singletonList(DUKE)));
    }

    @Test
    public void testIsBluff_ReturnsTrue_BlockForeignAid_DoesNotHaveDuke() {
        assertTrue(ActionDetector.isBluff(BLOCK_FOREIGN_AID, singletonList(ASSASSIN)));
    }
}
