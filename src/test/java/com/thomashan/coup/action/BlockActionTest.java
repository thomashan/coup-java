package com.thomashan.coup.action;

import org.junit.jupiter.api.Test;

import static com.thomashan.coup.PlayerBuilder.build;
import static com.thomashan.coup.action.BlockActionType.BLOCK_ASSASSINATE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlockActionTest {
    @Test
    public void testOf() {
        assertNotNull(BlockAction.of(build(), BLOCK_ASSASSINATE));
    }
}
