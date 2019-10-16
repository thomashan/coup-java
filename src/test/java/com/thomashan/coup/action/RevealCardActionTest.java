package com.thomashan.coup.action;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.build;
import static com.thomashan.coup.action.RevealCardActionType.ANY_OUT_OF_2;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RevealCardActionTest {
    @Test
    public void testOf() {
        assertNotNull(RevealCardAction.of(build(), ANY_OUT_OF_2));
    }
}
