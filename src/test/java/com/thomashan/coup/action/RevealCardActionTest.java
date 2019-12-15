package com.thomashan.coup.action;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RevealCardActionTest {
    @Test
    public void testOf() {
        assertNotNull(RevealCardAction.of(newBuilder().build(), AMBASSADOR));
    }
}
