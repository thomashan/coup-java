package com.thomashan.coup.action;

import com.thomashan.coup.Player;

import java.util.Optional;

public class BlockAction implements Action<BlockActionType> {
    private final Player player;
    private final BlockActionType blockActionType;

    public BlockAction(Player player, BlockActionType blockActionType) {
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
    public Optional<Player> getTarget() {
        return Optional.empty();
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
