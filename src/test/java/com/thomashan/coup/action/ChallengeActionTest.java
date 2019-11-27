package com.thomashan.coup.action;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.newBuilder;
import static com.thomashan.coup.action.ChallengeActionType.ALLOW;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChallengeActionTest {
    @Test
    public void testOf() {
        assertNotNull(ChallengeAction.of(newBuilder().build(), ALLOW));
    }
}
