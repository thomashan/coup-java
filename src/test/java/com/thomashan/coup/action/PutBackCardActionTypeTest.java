package com.thomashan.coup.action;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PutBackCardActionTypeTest {
    @Test
    public void testIsChallengeable() {
        for (PutBackCardActionType putBackCardActionType : PutBackCardActionType.values()) {
            assertFalse(putBackCardActionType.isChallengeable(), putBackCardActionType.toString());
        }
    }

    @Test
    public void testIsBlockable() {
        for (PutBackCardActionType putBackCardActionType : PutBackCardActionType.values()) {
            assertFalse(putBackCardActionType.isBlockable(), putBackCardActionType.toString());
        }
    }
}
