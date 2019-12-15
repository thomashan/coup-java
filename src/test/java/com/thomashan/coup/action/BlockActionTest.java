package com.thomashan.coup.action;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.action.BlockActionType.BLOCK_ASSASSINATE;
import static com.thomashan.coup.card.Card.CONTESSA;
import static com.thomashan.coup.player.PlayerBuilder.newBuilder;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlockActionTest {
    @Test
    public void testOf() {
        assertNotNull(BlockAction.block(newBuilder().build(), BLOCK_ASSASSINATE, CONTESSA));
    }
}
