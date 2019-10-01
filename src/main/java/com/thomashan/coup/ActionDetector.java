package com.thomashan.coup;

import java.util.List;

import static com.thomashan.coup.BlockActionType.BLOCK_ASSASSINATE;
import static com.thomashan.coup.BlockActionType.BLOCK_FOREIGN_AID;
import static com.thomashan.coup.BlockActionType.BLOCK_STEAL;
import static com.thomashan.coup.Card.AMBASSADOR;
import static com.thomashan.coup.Card.ASSASSIN;
import static com.thomashan.coup.Card.CAPTAIN;
import static com.thomashan.coup.Card.CONTESSA;
import static com.thomashan.coup.Card.DUKE;
import static com.thomashan.coup.MainActionType.ASSASSINATE;
import static com.thomashan.coup.MainActionType.EXCHANGE;
import static com.thomashan.coup.MainActionType.STEAL;
import static com.thomashan.coup.MainActionType.TAX;

public class ActionDetector {
    private ActionDetector() {
        throw new AssertionError();
    }

    public static boolean isBluff(MainActionType mainActionType, List<Card> cards) {
        if (mainActionType == ASSASSINATE && !cards.contains(ASSASSIN)) {
            return true;
        }

        if (mainActionType == EXCHANGE && !cards.contains(AMBASSADOR)) {
            return true;
        }

        if (mainActionType == STEAL && !cards.contains(CAPTAIN)) {
            return true;
        }

        if (mainActionType == TAX && !cards.contains(DUKE)) {
            return true;
        }

        return false;
    }

    public static boolean isBluff(BlockActionType blockActionType, List<Card> cards) {
        if (blockActionType == BLOCK_ASSASSINATE && !cards.contains(CONTESSA)) {
            return true;
        }

        if (blockActionType == BLOCK_STEAL) {
            if (!cards.contains(AMBASSADOR) && !cards.contains(CAPTAIN)) {
                return true;
            }
        }

        if (blockActionType == BLOCK_FOREIGN_AID && !cards.contains(DUKE)) {
            return true;
        }

        return false;
    }
}
