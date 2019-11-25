package com.thomashan.coup.action;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RevealCardActionTypeTest {
    @Test
    public void testIsChallengeable() {
        for (RevealCardActionType revealCardActionType : RevealCardActionType.values()) {
            assertFalse(revealCardActionType.isChallengeable(), revealCardActionType.toString());
        }
    }

    @Test
    public void testIsBlockable() {
        for (RevealCardActionType revealCardActionType : RevealCardActionType.values()) {
            assertFalse(revealCardActionType.isBlockable(), revealCardActionType.toString());
        }
    }
}
