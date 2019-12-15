package com.thomashan.coup.action;

import com.thomashan.collection.immutable.ImmutableList;
import com.thomashan.coup.card.Card;

import java.util.List;

import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.card.Card.CAPTAIN;
import static com.thomashan.coup.card.Card.CONTESSA;
import static com.thomashan.coup.card.Card.DUKE;

public enum BlockActionType implements ActionType {
    BLOCK_ASSASSINATE(CONTESSA),
    BLOCK_STEAL(CAPTAIN, AMBASSADOR),
    BLOCK_FOREIGN_AID(DUKE),
    NO_BLOCK();

    private final List<Card> blockableBy;

    BlockActionType(Card... cards) {
        blockableBy = ImmutableList.of(cards);
    }

    public List<Card> getBlockableBy() {
        return blockableBy;
    }

    @Override
    public boolean isChallengeable() {
        return true;
    }

    @Override
    public boolean isBlockable() {
        return false;
    }
}
