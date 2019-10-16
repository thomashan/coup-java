package com.thomashan.coup.action;

import com.thomashan.coup.Player;

public class BlockAction implements Action<BlockActionType> {
    private final Player player;
    private final BlockActionType blockActionType;

    private BlockAction(Player player, BlockActionType blockActionType) {
        this.player = player;
        this.blockActionType = blockActionType;
    }

    public static BlockAction of(Player player, BlockActionType blockActionType) {
        return new BlockAction(player, blockActionType);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public BlockActionType getActionType() {
        return blockActionType;
    }

    @Override
    public Class<BlockActionType> getActionTypeClass() {
        return BlockActionType.class;
    }
}
