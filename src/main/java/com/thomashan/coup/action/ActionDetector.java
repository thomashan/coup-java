package com.thomashan.coup.action;

import com.thomashan.coup.card.Card;

import java.util.Set;

import static com.thomashan.coup.action.BlockActionType.BLOCK_ASSASSINATE;
import static com.thomashan.coup.action.BlockActionType.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.action.BlockActionType.BLOCK_STEAL;
import static com.thomashan.coup.action.MainActionType.ASSASSINATE;
import static com.thomashan.coup.action.MainActionType.EXCHANGE;
import static com.thomashan.coup.action.MainActionType.STEAL;
import static com.thomashan.coup.action.MainActionType.TAX;
import static com.thomashan.coup.card.Card.AMBASSADOR;
import static com.thomashan.coup.card.Card.ASSASSIN;
import static com.thomashan.coup.card.Card.CAPTAIN;
import static com.thomashan.coup.card.Card.CONTESSA;
import static com.thomashan.coup.card.Card.DUKE;

public final class ActionDetector {
    private ActionDetector() {
        throw new AssertionError();
    }

    public static boolean isBluff(MainActionType mainActionType, Set<Card> cards) {
        if (mainActionType == ASSASSINATE && !cards.contains(ASSASSIN)) {
            return true;
        } else if (mainActionType == EXCHANGE && !cards.contains(AMBASSADOR)) {
            return true;
        }
        if (mainActionType == STEAL && !cards.contains(CAPTAIN)) {
            return true;
        }

        return mainActionType == TAX && !cards.contains(DUKE);
    }

    public static boolean isBluff(BlockActionType blockActionType, Set<Card> cards) {
        if (blockActionType == BLOCK_ASSASSINATE && !cards.contains(CONTESSA)) {
            return true;
        } else if (blockActionType == BLOCK_STEAL) {
            if (!cards.contains(AMBASSADOR) && !cards.contains(CAPTAIN)) {
                return true;
            }
        }

        return blockActionType == BLOCK_FOREIGN_AID && !cards.contains(DUKE);
    }

    public static Card getActionableCard(MainActionType mainActionType) {
        switch (mainActionType) {
            case ASSASSINATE:
                return ASSASSIN;
            case TAX:
                return DUKE;
            case STEAL:
                return CAPTAIN;
            case EXCHANGE:
                return AMBASSADOR;
            default:
                throw new IllegalArgumentException("There main action type is not challengeable");
        }
    }

    public static boolean isNoAction(Action action) {
        return action instanceof NoAction;
    }

    public static boolean isMainAction(Action action) {
        return action instanceof MainAction;
    }

    public static boolean isBlockAction(Action action) {
        return action instanceof BlockAction;
    }

    public static boolean isPerformAction(Action action) {
        return action instanceof PerformAction;
    }
}
