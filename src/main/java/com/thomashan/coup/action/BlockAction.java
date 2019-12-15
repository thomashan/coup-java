package com.thomashan.coup.action;

import com.thomashan.coup.card.Card;
import com.thomashan.coup.player.Player;
import lombok.EqualsAndHashCode;

import java.util.Optional;

import static com.thomashan.coup.action.BlockActionType.NO_BLOCK;

@EqualsAndHashCode
public class BlockAction implements Action<BlockActionType> {
    private final Player player;
    private final BlockActionType blockActionType;
    private final Card blockAs;

    public BlockAction(Player player, BlockActionType blockActionType, Card blockAs) {
        this.player = player;
        this.blockActionType = blockActionType;
        this.blockAs = blockAs;
    }

    public static BlockAction block(Player player, BlockActionType blockActionType, Card blockAs) {
        checkBlockAs(blockActionType, blockAs);
        return new BlockAction(player, blockActionType, blockAs);
    }

    public static BlockAction noBlock(Player player) {
        return new BlockAction(player, NO_BLOCK, null);
    }

    private static void checkBlockAs(BlockActionType blockActionType, Card blockAs) {
        if (!blockActionType.getBlockableBy().contains(blockAs)) {
            throw new IllegalArgumentException("You cannot perform " + blockActionType + " as " + blockAs);
        }
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
    public boolean isCheckForActivePlayer() {
        return true;
    }

    public Card getBlockAs() {
        return blockAs;
    }
}
